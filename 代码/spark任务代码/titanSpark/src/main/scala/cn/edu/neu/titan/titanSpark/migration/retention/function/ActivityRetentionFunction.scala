package cn.edu.neu.titan.titanSpark.migration.retention.function

import java.sql.{Connection, DriverManager}

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.ColumnNameUtils
import cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc.InitialInsertSql
import cn.edu.neu.titan.titanSpark.migration.{connectionProperties, currentDate, driver, spark, tbCVJoin, url}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/19 
 * @Time: 18:17
 * @Version: 1.0
 * @Description:
 */
object ActivityRetentionFunction {
    def migrate(): Unit = {
        val tbSource = Constants.HIVE_TABLE_ADS_APL_UCA
        val tbTarget = Constants.MYSQL_TABLE_RETENTION_ACTIVE
        Class.forName(driver)
        val connection: Connection = DriverManager.getConnection(url, connectionProperties)

        //插入前一天的数据，默认值全部都是 0
        val sql_insert_today = InitialInsertSql.getActiveInitial(currentDate)

        //选出活跃天数发生变化的数据，即：currentDate - dt = nrt_days 的数据
        val sql_select_changed = s"SELECT dt, continuous_category, count " +
                s"FROM $tbSource uca_rec " +
                s"WHERE dt = '$currentDate'"

        println(sql_insert_today)

        println(sql_select_changed)

        //1. 先把前一天的默认数据(留存天数都为0)插入mysql横表
        spark.sql(sql_insert_today).toDF("activity_date", "1_day_active_num", "2_day_active_num", "3_day_active_num", "4_day_active_num", "5_day_active_num",
            "6_day_active_num", "7_day_active_num", "7p_day_active_num")
                .write.
                mode("append").
                jdbc(url, tbTarget, connectionProperties)

        //2.查询hive纵表，找出留存天数发生变化的数据
        val df = spark.sql(sql_select_changed)

        //3.将发生变化的数据更新到mysql横表
        df.rdd.collect.foreach(row => {
            val dt = row.getAs[String]("dt")
            val continuous_category = row.getAs[String]("continuous_category")
            val rt_count = row.getAs[Int]("count")


            val sql_update = s"UPDATE $tbTarget " +
                    s"SET ${ColumnNameUtils.getActiveColumn(continuous_category)} = $rt_count " +
                    s"WHERE activity_date = '$dt'"

            connection.createStatement().execute(sql_update)
        })
    }

    def main(args: Array[String]): Unit = {
        migrate()
    }
}
