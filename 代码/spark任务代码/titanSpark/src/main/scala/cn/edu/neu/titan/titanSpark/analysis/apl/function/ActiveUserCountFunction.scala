package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/9 
 * @Time: 12:08
 * @Version: 1.0
 * @Description: 活跃用户统计
 */
object ActiveUserCountFunction {

  def activeUsercount() = {

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWS_APL_DAU_REC
    val tbDateDim = Constants.HIVE_TABLE_DWD_DIM_DATE

    val dtbTarget = Constants.HIVE_TABLE_ADS_USR_DAU_CUBE
    val wtbTarget = Constants.HIVE_TABLE_ADS_USR_WAU_CUBE
    val mtbTarget = Constants.HIVE_TABLE_ADS_USR_MAU_CUBE

    // sql 语句
    val sql_insert_d = s"insert into table $dtbTarget partition(dt='$currentDate') " +
      "select version, " +
      "channel, " +
      "provinceid, " +
      "os, " +
      "resolution, " +
      "model, " +
      "carrier, " +
      "network, " +
      s"count(distinct guid) dau_num from $tbSource where dt='$currentDate' " +
      "group by version,channel,provinceid,os,resolution,model,carrier,network " +
      "grouping sets((),(version),(channel),(version,channel)," +
      "(provinceid),(provinceid,version),(provinceid,channel),(provinceid,version,channel)," +
      "(os),(os,version),(os,channel),(os,version,channel)," +
      "(resolution),(resolution,version),(resolution,channel),(resolution,version,channel)," +
      "(model),(model,version),(model,channel),(model,version,channel)," +
      "(carrier),(carrier,version),(carrier,channel),(carrier,version,channel)," +
      "(network),(network,version),(network,channel),(network,version,channel))"

    println(sql_insert_d)

    spark.sql(sql_insert_d)

    if (DateUtils.isFirstDayOfMonth(today)) {
      val sql_insert_m = s"insert into table $mtbTarget partition(dt='$currentMonth') " +
        "select version, " +
        "channel, " +
        "provinceid, " +
        "os, " +
        "resolution, " +
        "model, " +
        "carrier, " +
        "network, " +
        s"count(distinct guid) mau_num from $tbSource base join $tbDateDim dim on base.dt=dim.dt and dim.month_dt='$currentMonth' " +
        "group by version,channel,provinceid,os,resolution,model,carrier,network " +
        "grouping sets((),(version),(channel),(version,channel)," +
        "(provinceid),(provinceid,version),(provinceid,channel),(provinceid,version,channel)," +
        "(os),(os,version),(os,channel),(os,version,channel)," +
        "(resolution),(resolution,version),(resolution,channel),(resolution,version,channel)," +
        "(model),(model,version),(model,channel),(model,version,channel)," +
        "(carrier),(carrier,version),(carrier,channel),(carrier,version,channel)," +
        "(network),(network,version),(network,channel),(network,version,channel))"
      println(sql_insert_m)

      spark.sql(sql_insert_m)
    }

    if (DateUtils.isFirstDayOfWeek(today)) {

      val sql_insert_w = s"insert into table $wtbTarget partition(dt='$currentWeek') " +
        "select version, " +
        "channel, " +
        "provinceid, " +
        "os, " +
        "resolution, " +
        "model, " +
        "carrier, " +
        "network, " +
        s"count(distinct guid) wau_num from $tbSource base join $tbDateDim dim on base.dt=dim.dt and dim.week_dt='$currentWeek' " +
        "group by version,channel,provinceid,os,resolution,model,carrier,network " +
        "grouping sets((),(version),(channel),(version,channel)," +
        "(provinceid),(provinceid,version),(provinceid,channel),(provinceid,version,channel)," +
        "(os),(os,version),(os,channel),(os,version,channel)," +
        "(resolution),(resolution,version),(resolution,channel),(resolution,version,channel)," +
        "(model),(model,version),(model,channel),(model,version,channel)," +
        "(carrier),(carrier,version),(carrier,channel),(carrier,version,channel)," +
        "(network),(network,version),(network,channel),(network,version,channel))"

      println(sql_insert_w)
      spark.sql(sql_insert_w)
    }
  }
  def main(args: Array[String]): Unit = {
    activeUsercount()
  }

}
