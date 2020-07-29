package cn.edu.neu.titan.titanSpark.migration.retention.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc.DayRetentionFunction.migrate

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/21 
 * @Time: 17:44
 * @Version: 1.0
 * @Description:
 */
object ActiveDayRetentionFunction {
    def main(args: Array[String]): Unit = {
        val dartSource = Constants.HIVE_TABLE_ADS_APL_DART_REC
        val dartTarget = Constants.MYSQL_TABLE_RETENTION_ACTIVE_DAY
        migrate(dartSource, dartTarget, "art_days", "art_count")
    }

}
