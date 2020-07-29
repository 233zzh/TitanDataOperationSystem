package cn.edu.neu.titan.titanSpark.migration.retention.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc.WeekRetentionFunction
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
object ActiveWeekRetentionFunction {
    def main(args: Array[String]): Unit = {
        val wartSource = Constants.HIVE_TABLE_ADS_APL_WART_REC
        val wartTarget = Constants.MYSQL_TABLE_RETENTION_ACTIVE_WEEK

        if(DateUtils.isFirstDayOfWeek(today)) {
            WeekRetentionFunction.migrate(wartSource, wartTarget, "art_weeks", "art_count")
        }
    }
}
