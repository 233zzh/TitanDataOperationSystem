package cn.edu.neu.titan.titanSpark.migration.retention.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc.DayRetentionFunction.migrate

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/21 
 * @Time: 17:43
 * @Version: 1.0
 * @Description:
 */
object InstallationDayRetentionFunction {
    def main(args: Array[String]): Unit = {
        val dnrtSource = Constants.HIVE_TABLE_ADS_APL_DNRT_REC
        val dnrtTarget = Constants.MYSQL_TABLE_RETENTION_INSTALLATION_DAY

        migrate(dnrtSource, dnrtTarget, "nrt_days", "nrt_count")
    }
}
