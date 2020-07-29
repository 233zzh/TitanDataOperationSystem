package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/9 
 * @Time: 15:48
 * @Version: 1.0
 * @Description: Description 
 */
object UpdateUCARecFunction {

  def update(): Unit = {

    // 源表、目标表和临时表
    val tbDAUSource = Constants.HIVE_TABLE_DWS_APL_DAU_REC
//    val tbDAUSource = "source"
    val tbUcaRec = Constants.HIVE_TABLE_DWS_APL_UCA_REC
      val tbResult = "tbResult"
//    val tbUcaRec = "rec"
    val tbUser = "tbUsers"
    val tbRecNeed = "tbRecNeed"
    val tbJoin = "tbJoin"

    // 一些常数
    val maxDate = Constants.MAX_DATE

    // 获得用户（各渠道、版本）
    val sql_users = "select " +
      "guid, " +
      "case when version is NULL then '' else version end as version," +
      s"case when channel is NULL then '' else channel end as channel from $tbDAUSource  " +
      s"where dt='$currentDate' " +
      "group by guid,version,channel " +
      "grouping sets((guid),(guid,version),(guid,channel),(guid,channel,version))"


    val sql_RecNeed = s"select * from $tbUcaRec where dt='$currentDateBefore' and endDate='$maxDate'"

    // 用户与符合要求的表全连接
//    val sql_join = s"select today.guid tGuid," +
//      "today.version tVersion," +
//      "today.channel tChannel," +
//      "rec.guid rGuid," +
//      "rec.version rVersion," +
//      "rec.channel rChannel," +
//      "rec.startDate startDate " +
//      s"from $tbUser today full join $tbUcaRec rec " +
//      s"on dt=$currentDateBefore and today.guid=rec.guid and today.channel=rec.channel and today.version=rec.version and rec.endDate='$maxDate'"

    val sql_join = "select t.guid tGuid," +
      " t.channel tChannel," +
      " t.version tVersion," +
      " r.guid rGuid," +
      " r.channel rChannel," +
      " r.version rVersion," +
      s" startDate from $tbUser t full join $tbRecNeed r on t.guid=r.guid and t.version=r.version and t.channel=r.channel"

    // 未改变的记录
    val select_unchanged = s"select guid, channel, version, startDate, endDate from $tbUcaRec where dt='$currentDateBefore' and endDate!=$maxDate"

    // 新记录
    val select_new = s"select tGuid guid, " +
      "tChannel channel, " +
      "tVersion version, " +
      s"'$currentDate' startDate," +
      s"'$maxDate' endDate from $tbJoin where rGuid is NULL"

    // 今天未登录的用户
    val select_changed = s"select rGuid guid," +
      "rChannel channel," +
      "rVersion version," +
      "startDate," +
      s"'$currentDateBefore' endDate from $tbJoin where tGuid is NULL"

    // 连续登录的用户（未改变）
    val select_continued = s"select rGuid guid," +
      "rChannel channel," +
      "rVersion version," +
      "startDate," +
      s"'$maxDate' endDate from $tbJoin where (tGuid is not NULL) and (rGuid is not NULL)"

    // 插入语句
    val sql_insert = s"insert into table $tbUcaRec partition(dt='$currentDate') " +
      s" select guid, version, channel, startDate, endDate from $tbResult"

//    集群宕机时测试代码
//    // 创建源表视图
//    spark.read.parquet("file:///D:/data/mockData/rec/*.parquet").createOrReplaceTempView(tbDAUSource)
//
//    spark.read.parquet("file:///D:/data/mockData/UCARec01/*.parquet").createOrReplaceTempView(tbUcaRec)
//    // 创建记录表视图
//    val schemaString = "guid channel version startDate endDate"
//    val fields = schemaString.split(" ").map(field => StructField(field, StringType, nullable = true))
//    val schema = StructType(fields)

//    spark.createDataFrame(sc.emptyRDD[Row], schema).createOrReplaceTempView(tbUcaRec)

    // 创建用户视图
    val users = spark.sql(sql_users)
    users.createOrReplaceTempView(tbUser)

    // 筛选所需的记录
    spark.sql(sql_RecNeed).createOrReplaceTempView(tbRecNeed)

    // 创建join表
    val joins = spark.sql(sql_join)
    joins.createOrReplaceTempView(tbJoin)

    // 将结果创建为临时表
    spark.sql(select_changed).unionAll(spark.sql(select_new)).unionAll(spark.sql(select_unchanged)).unionAll(spark.sql(select_continued)).createOrReplaceTempView(tbResult)
//      spark.sql(s"select count(*) res from $tbResult").select("res").write.format("parquet").save("res.parquet")
      // 执行插入
    spark.sql(sql_insert)

  }

    def main(args: Array[String]): Unit = {
        update()
    }
}
