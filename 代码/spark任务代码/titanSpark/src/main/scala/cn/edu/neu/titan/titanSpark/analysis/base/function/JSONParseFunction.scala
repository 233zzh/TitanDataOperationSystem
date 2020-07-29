package cn.edu.neu.titan.titanSpark.analysis.base.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.analysis.base.bean.EventLogBean
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.constant.Constants._
import cn.edu.neu.titan.titanSpark.common.utils.{IdMapUtils, JsonUtils}
import org.apache.spark.sql.{DataFrame, Dataset, SaveMode}
/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/5 
 * @Time: 11:15
 * @Version: 1.0
 * @Description: 转化字符串为json，并做id的方法
 */
object JSONParseFunction {

  def jsonParse(eventLog: Dataset[String]):DataFrame={

    // 目标表
    val tbTarget = HIVE_TABLE_DWD_BASE_EVENT_LOG
    val tempViewName = "events"

    // 写入的sql语句
    val sql_insert = s"insert into table $tbTarget partition(dt='$currentDate') select * from $tempViewName"

    import spark.implicits._

    // 读取路径
    val root = ConfigurationManager.config.getString(Constants.PATH_ID_MAP)
    val path = root+currentDate

    // 读取idmap并广播
    val idMap = IdMapUtils.loadMapDict(spark, path)
    val mapBroadcast = sc.broadcast(idMap)

      val eventLogParsed = eventLog.map(line => IdMapUtils.idMap(line, mapBroadcast))             // 进行idMap和json解析
        .filter(_._1!=0L)               // 过滤全id为空的记录
        .map { case(guid, line) =>      // 转化为封装类
        val json = JsonUtils.getJSON(line)
        val user = json.getJSONObject("user")
        val phone = user.getJSONObject("phone")
        val app = user.getJSONObject("app")
        val loc = user.getJSONObject("loc")
          val event = json.getString("event")
        EventLogBean(guid,
          json.getString("eventid"),
          JsonUtils.toMap(event),
          user.getString("uid"),
          phone.getString("imei"),
          phone.getString("mac"),
          phone.getString("imsi"),
          phone.getString("osName") +" "+ phone.getString("osVer"),
          phone.getString("androidId"),
          phone.getString("resolution"),
          phone.getString("deviceType"),
          phone.getString("deviceId"),
          phone.getString("uuid"),
          app.getString("appid"),
          app.getString("appVer"),
          app.getString("release_ch"),
          app.getString("promotion_ch"),
          loc.getString("areacode"),
          loc.getString("areacode").substring(0,2),
          loc.getString("longtitude").toDouble,
          loc.getString("latitude").toDouble,
          loc.getString("carrier"),
          loc.getString("netType"),
          loc.getString("cid_sn"),
          loc.getString("ip"),
          user.getString("sessionId"),
          json.getLong("timestamp"))
      }.toDF()

      // 创建视图
      eventLogParsed.createOrReplaceTempView("events")
//           保存至hive表内
      spark.sql(sql_insert)
//    eventLogParsed.write.mode(SaveMode.Append).partitionBy("dt").insertInto(tbTarget)
      eventLogParsed
  }

  def main(args: Array[String]): Unit = {
    // 1. 进行id-map更新
    val eventLog = IdMapFunction.idMap
    // 2. 进行数据预处理（json解析、分配guid、过滤数据）
    JSONParseFunction.jsonParse(eventLog)
  }
}
