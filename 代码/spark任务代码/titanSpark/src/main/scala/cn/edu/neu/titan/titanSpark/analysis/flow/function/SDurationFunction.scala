package cn.edu.neu.titan.titanSpark.analysis.flow.function

import cn.edu.neu.titan.titanSpark.analysis.{currentDate, spark}
import cn.edu.neu.titan.titanSpark.common.constant.Constants

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/9 
 * @Time: 9:29
 * @Version: 1.0
 * @Description: Description 
 */
object SDurationFunction {

  def durationCount() = {

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWS_FLW_AGG_S
    val tbTarget = Constants.HIVE_TABLE_ADS_FLW_SDURATION_CUBE

    // sq 语句
    val sql_insert = s"insert into table $tbTarget partition(dt='$currentDate') " +
      "select version, " +
      "channel, " +
      "duration_range, " +
      s"count(*) duration_count from $tbSource where dt='$currentDate' " +
      "group by version, channel, duration_range " +
      "grouping sets((duration_range),(duration_range,version),(duration_range,channel),(duration_range,version,channel))"

    spark.sql(sql_insert).show(100)
  }

  def main(args: Array[String]): Unit = {
    durationCount()
  }

}
