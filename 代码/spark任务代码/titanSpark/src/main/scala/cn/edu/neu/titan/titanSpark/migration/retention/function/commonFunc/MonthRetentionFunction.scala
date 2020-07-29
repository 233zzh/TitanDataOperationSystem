package cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc

import java.sql.{Connection, DriverManager}

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.ColumnNameUtils
import cn.edu.neu.titan.titanSpark.migration._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/14
 * @Time: 11:23
 * @Version: 1.0
 * @Description: 每月更新活跃/新增用户留存的 mysql 表，更新规则是：
 *              1. 先把上一月的默认数据(留存天数都为0)插入mysql横表
 *              2.查询hive纵表，找出留存月数发生变化的数据
 *              3.将发生变化的数据更新到mysql横表
 */
object MonthRetentionFunction {
    def migrate(tbSource: String, tbTarget: String, monthColumn: String, countColumn: String): Unit = {

        Class.forName(driver)
        val connection: Connection = DriverManager.getConnection(url, connectionProperties)

        //插入上月的数据，默认值全部都是0
        val sql_insert_currentMonth = InitialInsertSql.getRetentionInitial(currentMonth)

        //选出活跃用户留存天数发生变化的数据，即：months_between(currentMonth, dt) = art_months 的数据
        val sql_select_changed = s"SELECT dt, cId channel_id, vId version_id, $monthColumn, $countColumn " +
                s"FROM $tbSource rt_rec JOIN $tbCVJoin cv ON rt_rec.version = cv.vname and rt_rec.channel = cv.cname " +
                s"WHERE months_between('$currentMonth', dt) = $monthColumn"

        println(sql_select_changed)

        //1. 先把上个月的默认数据(留存约束都为0）插入到mysql横表
        spark.sql(sql_insert_currentMonth).toDF("retention_date", "channel_id", "version_id", "1_month_after_num", "2_month_after_num", "3_month_after_num", "4_month_after_num", "5_month_after_num",
            "6_month_after_num", "7_month_after_num", "8_month_after_num", "9_month_after_num")
                .write
                .mode("append")
                .jdbc(url, tbTarget, connectionProperties)

        //2.查询hive纵表，找出留存月数发生变化的数据
        val df = spark.sql(sql_select_changed)

        df.show(1000)

        //3.将发生变化的数据更新到mysql横表
        df.rdd.collect.foreach(row => {
            val dt = row.getAs[String]("dt")
            val channel_id = row.getAs[Int]("channel_id")
            val version_id = row.getAs[Int]("version_id")

            val rt_months = row.getAs[Int](monthColumn)
            val rt_count = row.getAs[Int](countColumn)

            val sql = s"UPDATE $tbTarget " +
                    s"SET ${ColumnNameUtils.getRetentionMonthColumn(rt_months)} = $rt_count " +
                    s"WHERE retention_date = '$dt' AND " +
                    s"version_id = $version_id " +
                    s"AND " +
                    s"channel_id = $channel_id"
            connection.createStatement().execute(sql)
        })
    }

    def main(args: Array[String]): Unit = {

        val mnrtSource = Constants.HIVE_TABLE_ADS_APL_MNRT_REC
        val mnrtTarget = Constants.MYSQL_TABLE_RETENTION_INSTALLATION_MONTH

        val martSource = Constants.HIVE_TABLE_ADS_APL_MART_REC
        val martTarget = Constants.MYSQL_TABLE_RETENTION_ACTIVE_MONTH

        migrate(mnrtSource, mnrtTarget, "nrt_months", "nrt_count")

        migrate(martSource, martTarget, "art_months", "art_count")
    }
}
