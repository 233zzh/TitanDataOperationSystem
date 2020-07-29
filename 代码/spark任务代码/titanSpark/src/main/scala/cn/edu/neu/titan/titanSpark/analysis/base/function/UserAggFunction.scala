package cn.edu.neu.titan.titanSpark.analysis.base.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/8 
 * @Time: 11:26
 * @Version: 1.0
 * @Description: 用户聚合的方法
 */
object UserAggFunction {

  def userAgg() = {

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWS_FLW_AGG_S
    val tbTarget = Constants.HIVE_TABLE_DWS_FLW_AGG_U

    // sql语句
    val sql_intsert = s"insert into table $tbTarget partition(dt='$currentDate') " +
      "select guid," +
      " version," +
      " channel," +
      " provinceid," +
      " os," +
      " resolution," +
      " model," +
      " carrier," +
      " network," +
      " count(1) view_num," +
      " sum(duration) duration," +
      " sum(pv_num) pv_num " +
      s" from $tbSource where dt='$currentDate' " +
      "group by guid,version,channel,provinceid,os,resolution,model,carrier,network"

    spark.sql(sql_intsert)
  }

  def main(args: Array[String]): Unit = {
    userAgg()
  }
}
