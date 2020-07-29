package cn.edu.neu.titan.titanSpark.migration.retention.function.commonFunc

import cn.edu.neu.titan.titanSpark.migration.tbCVJoin

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/13
 * @Time: 16:08
 * @Version: 1.0
 * @Description: 返回初始化留存表中每一天/周/月的 sql 语句，所谓初始化就是把留存数都设置为 0
 */
object InitialInsertSql {
    def getRetentionInitial(date: String): String = {
        val sql = s"SELECT * FROM " +
                s"(SELECT '$date' dt) dt, " +
                s"(SELECT cid, vid FROM $tbCVJoin) cvId, " +    //只需要版本id和渠道id，不用name
                s"(SELECT 0 as rt_1, 0 as rt_2, 0 as rt_3, 0 as rt_4, 0 as rt_5, 0 as rt_6, 0 as rt_7, 0 as rt_8, 0 as rt_9) rt"

        sql
    }

    def getActiveInitial(date: String): String = {
        val sql = s"SELECT '$date' as activity_date, 0 as 1_day_active_num, 0 as 2_day_active_num, 0 as 3_day_active_num, " +
                s"0 as 4_day_active_num, 0 as 5_day_active_num, 0 as 6_day_active_num, 0 as 7_day_active_num,  0 as 7p_day_active_num"

        sql
    }
}
