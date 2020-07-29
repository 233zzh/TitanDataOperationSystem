package cn.edu.neu.titan.titanSpark.analysis.base.function

import cn.edu.neu.titan.titanSpark.common.utils.{FileUtils, JsonUtils}
import org.apache.commons.lang3.StringUtils
import org.apache.spark.graphx.{Edge, Graph, VertexId, VertexRDD}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, Row}
import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.constant.Constants.HIVE_TABLE_ODS_EVENT_LOG
import org.apache.spark.sql.types.{DataTypes, StructType}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/6
 * @Time: 18:07
 * @Version: 1.0
 * @Description: 生成第一天的id-map
 */
object IdMapFunction {

    def idMap:Dataset[String] = {

        // 读表
        val tbSource: String = HIVE_TABLE_ODS_EVENT_LOG

        import spark.implicits._
        val sql_select: String = s"select line from $tbSource where dt='$currentDate'";

        // 查询今天日志并转换为DS
        val appLog: Dataset[String] = spark.sql(sql_select).as[String]
//        val appLog = spark.read.textFile("file:///D:/data/mockData/doit.mall.access_2020-07-01.log")
        integration(appLog)
        // 返回读取的DataSet和创建的idMap
        appLog
    }

    def integration(appLog: Dataset[String]): Unit = {

        // 读取路径
        val root: String = ConfigurationManager.config.getString(Constants.PATH_ID_MAP)
        val preDayPath: String = root+currentDateBefore+"/*.parquet"
        val todayPath: String = root+currentDate


        // 二、提取每一类数据中每一行的标识字段
        val app_ids: RDD[Array[String]] = extractIds(appLog)

        val ids: RDD[Array[String]] = app_ids

        // 三、构造图计算中的vertex集合
        val vertices: RDD[(Long, String)] = ids.flatMap(arr => {
            for (biaoshi <- arr) yield (biaoshi.hashCode.toLong, biaoshi)
        })

        // 四、构造图计算中的Edge集合
        val edges: RDD[Edge[String]] = ids.flatMap(arr => {
            // 用双层for循环，来对一个数组中所有的标识进行两两组合成边
            // [a,b,c,d] ==>   a-b  a-c  a-d  b-c  b-d  c-d
            for (i <- 0 to arr.length - 2; j <- i + 1 until arr.length) yield Edge(arr(i).hashCode.toLong, arr(j).hashCode.toLong, "")
        })
          // 将边变成 （边,1)来计算一个边出现的次数
          .map(edge => (edge, 1))
          .reduceByKey(_ + _)
          // 过滤掉出现次数小于经验阈值的边
          .filter(tp => tp._2 > 2)
          .map(tp => tp._1)

        if (!FileUtils.pathIsExist(spark, preDayPath)) {
            val graph = Graph(vertices,edges)

            // VertexRDD[VertexId] ==>  RDD[(点id-Long,组中的最小值)]
            val res_tuples: VertexRDD[VertexId] = graph.connectedComponents().vertices

            // 可以直接用图计算所产生的结果中的组最小值，作为这一组的guid（当然，也可以自己另外生成一个UUID来作为GUID）
            import spark.implicits._
            // 保存结果
            res_tuples.toDF("biaoshi_hashcode","guid").write.parquet(todayPath)

            return
        }

        // 五、将上一日的idmp映射字典，解析成点、边集合
        val schema = new StructType()
          .add("biaoshi_hashcode",DataTypes.LongType)
          .add("guid",DataTypes.LongType)

        val preDayIdmp = spark.read.schema(schema).parquet(preDayPath)

        // 构造点集合
        val preDayIdmpVertices = preDayIdmp.rdd.map({
            case Row(idFlag: VertexId, guid: VertexId) =>
                (idFlag, "")
        })

        // 构造边集合
        val preDayEdges = preDayIdmp.rdd.map(row => {
            val idFlag = row.getAs[VertexId]("biaoshi_hashcode")
            val guid = row.getAs[VertexId]("guid")
            Edge(idFlag, guid, "")
        })

        // 将当日的点集合union上日的点集合，当日的边集合union上日的边集合，构造图，并调用连通子图算法
        val graph = Graph(vertices.union(preDayIdmpVertices), edges.union(preDayEdges))

        // VertexRDD[VertexId] ==>  RDD[(点id-Long,组中的最小值)]
        val res_tuples: VertexRDD[VertexId] = graph.connectedComponents().vertices

        // 八、将结果跟上日的映射字典做对比，调整guid
        // 1.将上日的idmp映射结果字典收集到driver端，并广播
        val preIdMap = preDayIdmp.rdd.map(row => {
            val idFlag = row.getAs[VertexId]("biaoshi_hashcode")
            val guid = row.getAs[VertexId]("guid")
            (idFlag, guid)
        }).collectAsMap()
        val bc = sc.broadcast(preIdMap)

        // 2.将今日的图计算结果按照guid分组,然后去跟上日的映射字典进行对比
        val todayIdmpResult: RDD[(VertexId, VertexId)] = res_tuples.map(tp => (tp._2, tp._1))
          .groupByKey()
          .mapPartitions(iter=>{
              // 从广播变量中取出上日的idmp映射字典
              val idmpMap = bc.value
              iter.map(tp => {
                  // 当日的guid计算结果
                  var todayGuid = tp._1
                  // 这一组中的所有id标识
                  val ids = tp._2

                  // 遍历这一组id，挨个去上日的idmp映射字典中查找
                  var find = false
                  for (elem <- ids if !find) {
                      val maybeGuid: Option[Long] = idmpMap.get(elem)
                      // 如果这个id在昨天的映射字典中找到了，那么就用昨天的guid替换掉今天这一组的guid
                      if (maybeGuid.isDefined) {
                          todayGuid = maybeGuid.get
                          find = true
                      }
                  }
                  (todayGuid,ids)
              })
          })
          .flatMap(tp=>{
              val ids = tp._2
              val guid = tp._1
              for (elem <- ids) yield (elem,guid)
          })

        // 可以直接用图计算所产生的结果中的组最小值，作为这一组的guid（当然，也可以自己另外生成一个UUID来作为GUID）
        import spark.implicits._
        // 保存结果
        todayIdmpResult.coalesce(1).toDF("biaoshi_hashcode", "guid").write.parquet(todayPath)

    }

    /**
     * 从一个日志ds中提取各类标识id
     *
     * @param logDs
     * @return
     */
    def extractIds(logDs: Dataset[String]): RDD[Array[String]] = {

        logDs.rdd.map(line => {

            // 将一行数据解析成json对象
            val jsonObj = JsonUtils.getJSON(line)

            // 从json对象中取user对象
            val userObj = jsonObj.getJSONObject("user")
            val uid = userObj.getString("uid")

            // 从user对象中取phone对象
            val phoneObj = userObj.getJSONObject("phone")
            val imei = phoneObj.getString("imei")
            val mac = phoneObj.getString("mac")
            val imsi = phoneObj.getString("imsi")
            val androidId = phoneObj.getString("androidId")
            val uuid = phoneObj.getString("uuid")

            Array(uid, imei, mac, imsi, androidId, uuid).filter(StringUtils.isNotBlank(_))
        })
    }
}
