package cn.edu.neu.titan.titanSpark.migration.baseAnalysis.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import cn.edu.neu.titan.titanSpark.migration._


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/12
 * @Time: 15:29
 * @Version: 1.0
 * @Description: Description
 */
object UserActiveFunction {

  def migrate() = {

    // 源表与目标表
    val tbDSource = Constants.HIVE_TABLE_ADS_USR_DAU_CUBE
    val tbWSource = Constants.HIVE_TABLE_ADS_USR_WAU_CUBE
    val tbMSource = Constants.HIVE_TABLE_ADS_USR_MAU_CUBE
    val tbDTarget = Constants.MYSQL_TABLE_USER_ACTIVE_DAY
    val tbWTarget = Constants.MYSQL_TABLE_USER_ACTIVE_WEEK
    val tbMTarget = Constants.MYSQL_TABLE_USER_ACTIVE_MONTH
    val tbDActive = "tbDActive"
    val tbMActive = "tbMActive"
    val tbWActive = "tbWActive"
    val colDName = "dau_num"
    val colWName = "wau_num"
    val colMName = "mau_num"

    executeActiveMigrate(tbDSource, tbDTarget, colDName, currentDate, tbDActive)
    if (DateUtils.isFirstDayOfWeek(today)) executeActiveMigrate(tbWSource, tbWTarget, colWName, currentWeek, tbWActive)
    if (DateUtils.isFirstDayOfMonth(today)) executeActiveMigrate(tbMSource, tbMTarget, colMName, currentMonth, tbMActive)
}

  def executeActiveMigrate(tbSource: String, tbTarget: String, colName: String, date: String, tbActive: String) = {
    val sql_select_active = "select " +
      "case when version is NULL then '' else version end as version," +
      "case when channel is NULL then '' else channel end as channel," +
      s"dau_num active_num from $tbSource " +
      s"where dt='$date' and provinceid is NULL and model is NULL and os is NULL and resolution is NULL " +
      "and carrier is NULL and network is NULL"

    // 左外连接并转化name 为 id
    val sql_join = s"select '$date' active_date, " +
      "cid channel_id," +
      "vid version_id," +
      "case when active_num is NULL then 0 else active_num end as active_num " +
      s"from $tbCVJoin left join $tbActive " +
      "on vname=version and cname=channel"

    // 执行sql语句
    spark.sql(sql_select_active).createOrReplaceTempView(tbActive)
    spark.sql(sql_join).write.mode("append").jdbc(url, tbTarget, connectionProperties)
  }

  def main(args: Array[String]): Unit = {
    migrate()
  }

}