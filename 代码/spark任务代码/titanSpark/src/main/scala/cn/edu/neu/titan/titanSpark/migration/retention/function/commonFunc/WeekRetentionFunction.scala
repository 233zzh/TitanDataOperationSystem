package cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc

import java.sql.{Connection, DriverManager}

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.{ColumnNameUtils, DateUtils}
import cn.edu.neu.titan.titanSpark.migration._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/14
 * @Time: 9:05
 * @Version: 1.0
 * @Description: 每周更新活跃/新增用户留存的 mysql 表，更新规则是：
 *              1. 先把上一周的默认数据(留存天数都为0)插入mysql横表
 *              2.查询hive纵表，找出留存周数发生变化的数据
 *              3.将发生变化的数据更新到mysql横表
 */
object WeekRetentionFunction {
    def migrate(tbSource: String, tbTarget: String, weekColumn: String, countColumn: String): Unit = {

        Class.forName(driver)
        val connection: Connection = DriverManager.getConnection(url, connectionProperties)

        //自定义udf，计算两个日期的间隔周数，在 mnrt 中没有自定义函数，是因为官方提供了计算月间隔的函数，而没有提供计算周间隔的函数
        val weeksBetween = "weeksBetween"
        spark.udf.register(weeksBetween, (startDate: String, endDate: String) => DateUtils.weeksBetween(startDate, endDate))

        //插入本周的数据，默认值全部都是0
        val sql_insert_currentWeek = InitialInsertSql.getRetentionInitial(currentWeek)

        //选出活跃用户留存天数发生变化的数据，即：weeksBetween(currentWeek, dt) = art_weeks 的数据
        val sql_select_changed = s"SELECT dt, cId channel_id, vId version_id, $weekColumn, $countColumn " +
                s"FROM $tbSource rt_rec JOIN $tbCVJoin cv ON rt_rec.version = cv.vname and rt_rec.channel = cv.cname " +
                s"WHERE $weeksBetween('$currentWeek', dt) = $weekColumn"

        //1. 先把上一周的默认数据(留存天数都为0)插入mysql横表
        spark.sql(sql_insert_currentWeek).toDF("retention_date", "channel_id", "version_id", "1_week_after_num", "2_week_after_num", "3_week_after_num", "4_week_after_num", "5_week_after_num",
            "6_week_after_num", "7_week_after_num", "8_week_after_num", "9_week_after_num")
                .write
                .mode("append")
                .jdbc(url, tbTarget, connectionProperties)

        //2.查询hive纵表，找出留存周数发生变化的数据
        val df = spark.sql(sql_select_changed)

        //3.将发生变化的数据更新到mysql横表
        df.rdd.collect.foreach(row => {
            val dt = row.getAs[String]("dt")
            val channel_id = row.getAs[Int]("channel_id")
            val version_id = row.getAs[Int]("version_id")
            val art_weeks = row.getAs[Int](weekColumn)
            val art_count = row.getAs[Int](countColumn)

            val sql = s"UPDATE $tbTarget " +
                    s"SET ${ColumnNameUtils.getRetentionWeekColumn(art_weeks)} = $art_count " +
                    s"WHERE retention_date = '$dt' AND " +
                    s"version_id = $version_id " +
                    s"AND " +
                    s"channel_id = $channel_id"
            println(sql)
            connection.createStatement().execute(sql)
        })
    }

    def main(args: Array[String]): Unit = {

        val wnrtSource = Constants.HIVE_TABLE_ADS_APL_WNRT_REC
        val wnrtTarget = Constants.MYSQL_TABLE_RETENTION_INSTALLATION_WEEK

        val wartSource = Constants.HIVE_TABLE_ADS_APL_WART_REC
        val wartTarget = Constants.MYSQL_TABLE_RETENTION_ACTIVE_WEEK

        migrate(wnrtSource, wnrtTarget, "nrt_weeks", "nrt_count")

        migrate(wartSource, wartTarget, "art_weeks", "art_count")
    }
}
