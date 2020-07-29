package cn.edu.neu.titan.titanSpark.analysis.retention.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/10
 * @Time: 10:04
 * @Version: 1.0
 * @Description:
 */
object MnrtRecFunction {
    def insertData(): Unit = {
        val tbSource = Constants.HIVE_TABLE_DWS_APL_HSU_REC
        val tbTarget = Constants.HIVE_TABLE_ADS_APL_MNRT_REC

        //因为历史记录表是记录的增量数据，所以一定要从当天的数据中 select
        val sql1 = s"SELECT guid, version, channel,  trunc(firstLoginDate, 'month') dt, cast(months_between(firstLoginDate, lastLoginDate) as int) nrt_months " +
                s"FROM $tbSource hsu " +
                s"WHERE dt = '$currentDate' AND trunc(lastLoginDate, 'month') = '$currentMonth'"

        val sql2 = s"INSERT INTO $tbTarget " +
                "SELECT dt, version, channel, nrt_months, count(distinct guid) nrt_count FROM tmp GROUP BY version, channel, dt, nrt_months"

        println(sql1)
        spark.sql(sql1).createOrReplaceTempView("tmp")
        spark.sql(sql2)
    }

    def main(args: Array[String]): Unit = {
        if(DateUtils.isFirstDayOfMonth(today)) {
            insertData()
        }
    }

}
