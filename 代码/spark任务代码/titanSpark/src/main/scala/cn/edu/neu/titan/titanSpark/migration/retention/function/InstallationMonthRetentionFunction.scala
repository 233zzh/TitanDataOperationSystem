package cn.edu.neu.titan.titanSpark.migration.retention.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc.MonthRetentionFunction
import cn.edu.neu.titan.titanSpark.migration.today

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/21 
 * @Time: 17:46
 * @Version: 1.0
 * @Description:
 */
object InstallationMonthRetentionFunction {
    def main(args: Array[String]): Unit = {
        val mnrtSource = Constants.HIVE_TABLE_ADS_APL_MNRT_REC
        val mnrtTarget = Constants.MYSQL_TABLE_RETENTION_INSTALLATION_MONTH

        if(DateUtils.isFirstDayOfMonth(today)) {
            MonthRetentionFunction.migrate(mnrtSource, mnrtTarget, "nrt_months", "nrt_count")
        }
    }

}
