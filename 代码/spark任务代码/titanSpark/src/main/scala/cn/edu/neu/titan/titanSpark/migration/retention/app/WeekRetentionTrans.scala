package cn.edu.neu.titan.titanSpark.migration.retention.app

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc.WeekRetentionFunction

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/14 
 * @Time: 21:50
 * @Version: 1.0
 * @Description:
 */
object WeekRetentionTrans {
    def main(args: Array[String]): Unit = {

        val wnrtSource = Constants.HIVE_TABLE_ADS_APL_WNRT_REC
        val wnrtTarget = Constants.MYSQL_TABLE_RETENTION_INSTALLATION_WEEK

        val wartSource = Constants.HIVE_TABLE_ADS_APL_WART_REC
        val wartTarget = Constants.MYSQL_TABLE_RETENTION_ACTIVE_WEEK

        WeekRetentionFunction.migrate(wnrtSource, wnrtTarget, "nrt_weeks", "nrt_count")

        WeekRetentionFunction.migrate(wartSource, wartTarget, "art_weeks", "art_count")
    }
}
