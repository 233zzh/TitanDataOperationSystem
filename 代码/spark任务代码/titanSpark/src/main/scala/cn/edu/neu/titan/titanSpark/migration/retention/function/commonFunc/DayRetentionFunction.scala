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
 * @Time: 21:18
 * @Version: 1.0
 * @Description: 每天更新活跃/新增用户留存的 mysql 表，更新规则是：
 *              1. 先把前一天的默认数据(留存天数都为0)插入mysql横表
 *              2.查询hive纵表，找出留存天数发生变化的数据
 *              3.将发生变化的数据更新到mysql横表
 */
object DayRetentionFunction {
    def migrate(tbSource: String, tbTarget: String, dayColumn: String, countColumn: String): Unit = {

        Class.forName(driver)
        val connection: Connection = DriverManager.getConnection(url, connectionProperties)

        //插入前一天的数据，默认值全部都是 0
        val sql_insert_today = InitialInsertSql.getRetentionInitial(currentDate)

        //选出活跃用户留存天数发生变化的数据，即：currentDate - dt = nrt_days 的数据
        val sql_select_changed = s"SELECT dt, cId channel_id, vId version_id, $dayColumn, $countColumn " +
                s"FROM $tbSource rt_rec JOIN $tbCVJoin cv ON rt_rec.version = cv.vname and rt_rec.channel = cv.cname " +
                s"WHERE $dayColumn > 0 AND $dayColumn <= 7 OR $dayColumn = 14 OR $dayColumn = 30 AND datediff('$currentDate', dt) = $dayColumn"

        println("--------------------------------------insert today---------------------------------------")
        println(sql_insert_today)
        spark.sql(sql_insert_today).orderBy("vid").show(10000)

        println(sql_select_changed)

        //1. 先把前一天的默认数据(留存天数都为0)插入mysql横表
        spark.sql(sql_insert_today).toDF("retention_date", "channel_id", "version_id", "1_day_after_num", "2_day_after_num", "3_day_after_num", "4_day_after_num", "5_day_after_num",
            "6_day_after_num", "7_day_after_num", "14_day_after_num", "30_day_after_num")
                .write.
                mode("append").
                jdbc(url, tbTarget, connectionProperties)

        //2.查询hive纵表，找出留存天数发生变化的数据
        val df = spark.sql(sql_select_changed)
        println("---------------------------------changed--------------------------------")
        df.show(300)

        //3.将发生变化的数据更新到mysql横表
        df.rdd.collect.foreach(row => {
            val dt = row.getAs[String]("dt")
            val channel_id = row.getAs[Int]("channel_id")
            val version_id = row.getAs[Int]("version_id")
            val rt_days = row.getAs[Int](dayColumn)
            val rt_count = row.getAs[Int](countColumn)

            val sql_update = s"UPDATE $tbTarget " +
                    s"SET ${ColumnNameUtils.getRetentionDayColumn(rt_days)} = $rt_count " +
                    s"WHERE retention_date = '$dt' AND " +
                    s"version_id = $version_id " +
                    s"AND " +
                    s"channel_id = $channel_id"

            connection.createStatement().execute(sql_update)
        })
    }

}
