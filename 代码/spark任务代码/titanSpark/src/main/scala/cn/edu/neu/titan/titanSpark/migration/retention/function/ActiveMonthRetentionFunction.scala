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
 * @Time: 17:47
 * @Version: 1.0
 * @Description:
 */
object ActiveMonthRetentionFunction {
    def main(args: Array[String]): Unit = {
        val martSource = Constants.HIVE_TABLE_ADS_APL_MART_REC
        val martTarget = Constants.MYSQL_TABLE_RETENTION_ACTIVE_MONTH

        if(DateUtils.isFirstDayOfWeek(today)) {
            MonthRetentionFunction.migrate(martSource, martTarget, "art_months", "art_count")
        }
    }

}
