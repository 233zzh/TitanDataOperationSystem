package cn.edu.neu.titan.titanSpark.analysis.base.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/7 
 * @Time: 16:46
 * @Version: 1.0
 * @Description: 过滤出页面访问的记录
 */
object FilterPageFunction {

  def filter(): Unit = {

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWD_BASE_EVENT_LOG
    val tbTarget = Constants.HIVE_TABLE_DWD_BASE_PAGE_LOG

    val idPageView = Constants.EVENT_ID_PAGE_VIEW

    val sql = s"insert into table $tbTarget partition(dt='$currentDate') " +
      "select guid," +
      " event['pgId']," +
      " uid," +
      "imei," +
      " mac," +
      " imsi," +
      " os, " +
      "androidid, " +
      "resolution, " +
      "model," +
      "deviceid," +
      "uuid, " +
      "appid, " +
      "version, " +
      "channel," +
      "promotion_ch," +
      "area_code," +
      "provinceid," +
      "longtitude," +
      "latitude," +
      "carrier," +
      "network," +
      "cid_sn," +
      "ip," +
      "sessionid," +
      "`timestamp` " +
      s" from $tbSource where dt='$currentDate' and eventid='$idPageView'"

    print(sql)
    spark.sql(sql)

  }

  def main(args: Array[String]): Unit = {
    filter()
  }

}
