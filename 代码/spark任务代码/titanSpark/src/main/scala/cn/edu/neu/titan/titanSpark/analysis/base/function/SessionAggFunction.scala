package cn.edu.neu.titan.titanSpark.analysis.base.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.RangeUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/7 
 * @Time: 12:07
 * @Version: 1.0
 * @Description: session聚合的方法
 */
object SessionAggFunction {


  def sessionAgg()={

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWD_BASE_EVENT_LOG
    val tbTarget = Constants.HIVE_TABLE_DWS_FLW_AGG_S

    val timeRange = ConfigurationManager.config.getString(Constants.RANGE_DURATION_SINGLE).split(",")
    val pageRange = ConfigurationManager.config.getString(Constants.RANGE_PAGE).split(",")

    // 临时表
    val tbTemp = "temp1"

    // 自定义udf
    val pvCnt = "pvCnt"
    val pageCnt = "pageRange"
    val timeCnt = "timeRange"

    // 注册udf
    spark.udf.register(pvCnt, (eventid: String)=>if (eventid==Constants.EVENT_ID_PAGE_VIEW) 1 else 0)
    spark.udf.register(pageCnt,(cnt: Int) => RangeUtils.getRange(cnt,pageRange))
    spark.udf.register(timeCnt,(diff: Int) => RangeUtils.getRange(diff, timeRange))

    // spark sql 语句
    val sql_select = "select guid," +
      " sessionid," +
      " version," +
      " channel," +
      " provinceid," +
      " os," +
      " resolution," +
      " model," +
      " carrier," +
      " network," +
      " min(`timestamp`) start_time," +
      " max(`timestamp`) end_time," +
      s" sum($pvCnt(eventid)) pv_num from $tbSource where dt='$currentDate' " +
      "group by guid,sessionid,version,channel,provinceid,os,resolution,model,carrier,network"

    val eventLog = spark.sql(sql_select)

    eventLog.createOrReplaceTempView(tbTemp)

    val sql_insert =  s"insert into table $tbTarget partition(dt='$currentDate')" +
      " select guid," +
      " sessionid," +
      " version," +
      " channel," +
      " provinceid," +
      " os," +
      " resolution," +
      " model," +
      " carrier," +
      " network," +
      " start_time," +
      " end_time," +
      " end_time-start_time duration," +
      " pv_num," +
      s" $timeCnt(end_time-start_time) duration_range," +
      s" $pageCnt(pv_num) pv_num_range " +
      s" from $tbTemp"

    // 执行插入语句
    spark.sql(sql_insert)
  }

  def main(args: Array[String]): Unit = {
    sessionAgg()
  }

}
