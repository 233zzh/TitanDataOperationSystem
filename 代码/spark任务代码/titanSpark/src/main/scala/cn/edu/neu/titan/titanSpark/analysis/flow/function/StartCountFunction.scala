package cn.edu.neu.titan.titanSpark.analysis.flow.function

import cn.edu.neu.titan.titanSpark.analysis.{currentDate, spark}
import cn.edu.neu.titan.titanSpark.common.constant.Constants

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/9 
 * @Time: 10:15
 * @Version: 1.0
 * @Description: Description 
 */
object StartCountFunction {

  def startCount() = {

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWS_FLW_AGG_U
    val tbTarget = Constants.HIVE_TABLE_ADS_USR_START_CUBE

    // sq 语句
    val sql_insert = s"insert into table $tbTarget partition(dt='$currentDate') " +
      "select version, " +
        "channel, " +
        "provinceid, " +
        "os, " +
        "resolution, " +
        "model, " +
        "carrier, " +
        "network, " +
        s"sum(view_num) start_num from $tbSource where dt='$currentDate' " +
        "group by version,channel,provinceid,os,resolution,model,carrier,network " +
        "grouping sets((),(version),(channel),(version,channel)," +
        "(provinceid),(provinceid,version),(provinceid,channel),(provinceid,version,channel)," +
        "(os),(os,version),(os,channel),(os,version,channel)," +
        "(resolution),(resolution,version),(resolution,channel),(resolution,version,channel)," +
        "(model),(model,version),(model,channel),(model,version,channel)," +
        "(carrier),(carrier,version),(carrier,channel),(carrier,version,channel)," +
        "(network),(network,version),(network,channel),(network,version,channel))"

    spark.sql(sql_insert)

  }

  def main(args: Array[String]): Unit = {
    startCount()
  }

}
