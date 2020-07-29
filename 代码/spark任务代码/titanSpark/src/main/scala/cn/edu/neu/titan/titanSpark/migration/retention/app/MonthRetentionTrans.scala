package cn.edu.neu.titan.titanSpark.migration.retention.app

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc.MonthRetentionFunction

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
object MonthRetentionTrans {
    def main(args: Array[String]): Unit = {

        val mnrtSource = Constants.HIVE_TABLE_ADS_APL_MNRT_REC
        val mnrtTarget = Constants.MYSQL_TABLE_RETENTION_INSTALLATION_MONTH

        val martSource = Constants.HIVE_TABLE_ADS_APL_MART_REC
        val martTarget = Constants.MYSQL_TABLE_RETENTION_ACTIVE_MONTH

        MonthRetentionFunction.migrate(mnrtSource, mnrtTarget, "nrt_months", "nrt_count")

        MonthRetentionFunction.migrate(martSource, martTarget, "art_months", "art_count")
    }

}
