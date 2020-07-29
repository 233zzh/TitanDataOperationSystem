package cn.edu.neu.titan.titanSpark.analysis.retention.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.analysis.retention.function.WartRecFunction.insertData
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/9
 * @Time: 18:17
 * @Version: 1.0
 * @Description:
 */
object WnrtRecFunction {
    def insertData(): Unit = {

        val tbSource = Constants.HIVE_TABLE_DWS_APL_HSU_REC
        val tbTarget = Constants.HIVE_TABLE_ADS_APL_WNRT_REC

        //自定义udf，计算两个日期的间隔周数，在 mnrt 中没有自定义函数，是因为官方提供了计算月间隔的函数，而没有提供计算周间隔的函数
        val weeksBetween = "weeksBetween"
        spark.udf.register(weeksBetween, (startDate: String, endDate: String) => DateUtils.weeksBetween(startDate, endDate))

        //因为历史记录表是记录的增量数据，所以一定要从当天的数据中 select
        val sql1 = s"SELECT guid, version, channel, trunc(firstLoginDate, 'week') dt, $weeksBetween(firstLoginDate, lastLoginDate) nrt_weeks " +
                s"FROM $tbSource " +
                s"WHERE dt = '$currentDate' AND trunc(lastLoginDate, 'week') = '$currentWeek'"

        val sql2 = s"INSERT INTO $tbTarget " +
                "SELECT dt, version, channel, nrt_weeks, count(distinct guid) nrt_count FROM tmp GROUP BY version, channel, dt, nrt_weeks"

        println(sql1)
        spark.sql(sql1).createOrReplaceTempView("tmp")
        spark.sql(sql2)
    }

    def main(args: Array[String]): Unit = {
        if(DateUtils.isFirstDayOfWeek(today)) {
            insertData()
        }
    }
}
