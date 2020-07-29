package cn.edu.neu.titan.titanSpark.common.utils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/12 
 * @Time: 17:25
 * @Version: 1.0
 * @Description:
 */
object ColumnNameUtils {
    def getRetentionDayColumn(day: Int): String = {
        s"${day}_day_after_num"
    }

    def getRetentionWeekColumn(week: Int): String = {
        s"${week}_week_after_num"   //等有数据之后把 + 1 去掉
    }

    def getRetentionMonthColumn(month: Int): String = {
        s"${month}_month_after_num"
    }

    def getActiveColumn(continuous_category: String): String = {
        s"${continuous_category}_day_active_num"
    }
}
