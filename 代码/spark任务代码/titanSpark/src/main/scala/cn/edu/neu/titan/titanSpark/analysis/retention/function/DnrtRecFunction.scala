package cn.edu.neu.titan.titanSpark.analysis.retention.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/9
 * @Time: 17:45
 * @Version: 1.0
 * @Description:
 */
object DnrtRecFunction {
    def insertData(): Unit = {
        val tbSource = Constants.HIVE_TABLE_DWS_APL_HSU_REC
        val tbTarget = Constants.HIVE_TABLE_ADS_APL_DNRT_REC

        //因为历史记录表是记录的增量数据，所以一定要从当天的数据中 select
        val sql1 = s"SELECT guid, version, channel, firstLoginDate dt, datediff(lastLoginDate, firstLoginDate) nrt_days from $tbSource " +
                s"WHERE dt = '$currentDate' AND lastLoginDate = '$currentDate' AND (datediff('$currentDate', firstLoginDate) between 1 and 31)"

        val sql2 = s"INSERT INTO $tbTarget " +
                "(SELECT version, channel, nrt_days, count(distinct guid) nrt_count, dt FROM tmp GROUP BY version, channel, dt, nrt_days)"

        spark.sql(sql1).createOrReplaceTempView("tmp")
        spark.sql(sql2)
    }

    def main(args: Array[String]): Unit = {
        insertData()
    }
}
