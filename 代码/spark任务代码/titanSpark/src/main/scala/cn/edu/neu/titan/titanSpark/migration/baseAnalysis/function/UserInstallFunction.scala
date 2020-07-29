package cn.edu.neu.titan.titanSpark.migration.baseAnalysis.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.{connectionProperties, currentDate, spark, tbCVJoin, url}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/13 
 * @Time: 14:33
 * @Version: 1.0
 * @Description: Description 
 */
object UserInstallFunction {

  def migrate(): Unit = {

    // 源表与目标表
    val tbSource = Constants.HIVE_TABLE_ADS_USR_DNU_CUBE
    val tbTarget = Constants.MYSQL_TABLE_USER_INSTALLATION_DAY
    val tbNew = "tbNew"

    // 筛选表
    val sql_select_start = "select " +
      "case when version is NULL then '' else version end as version," +
      "case when channel is NULL then '' else channel end as channel," +
      s"dnu_num increase_num from $tbSource " +
      s"where dt='$currentDate' and provinceid is NULL and model is NULL and os is NULL and resolution is NULL " +
      "and carrier is NULL and network is NULL"

    // 左外连接并转化name 为 id
    val sql_join = s"select '$currentDate' installation_date, " +
      "cid channel_id," +
      "vid version_id," +
      "case when increase_num is NULL then 0 else increase_num end as increase_num " +
      s"from $tbCVJoin left join $tbNew " +
      "on vname=version and cname=channel"

    // 执行sql语句
    spark.sql(sql_select_start).createOrReplaceTempView(tbNew)
    spark.sql(sql_join).write.mode("append").jdbc(url, tbTarget, connectionProperties)

  }

  def main(args: Array[String]): Unit = {
    migrate()
  }

}
