package edu.neu.titan.titanApp.common.utils;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.constant.Constants;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/20
 * @Time: 16:09
 * @Version: 1.0
 * @Description: 对周查询和月查询的 Condition 做更改
 */
public class SetConditionUtils {

    /**
     * 对周的日期做更改
     * @param oldCondition 之前的 condition
     * @return 更改之后的 oldCondition
     */
    public static Condition weekCondition(Condition oldCondition) {
        String newStart = DateUtils.convertWeekDateStart(oldCondition.getStart_date());
        String newEnd = DateUtils.convertWeekDateEnd(oldCondition.getEnd_date());

        return generateCondition(newStart, newEnd, oldCondition);
    }

    /**
     * 对月的日期做更改
     * @param oldCondition 之前的 condition
     * @return 更改之后的 oldCondition
     */
    public static Condition monthCondition(Condition oldCondition) {
        String newStart = DateUtils.convertMonthDateStart(oldCondition.getStart_date());
        String newEnd = DateUtils.convertMonthDateEnd(oldCondition.getEnd_date());

        return generateCondition(newStart, newEnd, oldCondition);
    }

    /**
     * 获得一个 start_date 为 oldCondition 的 start_date 前 days 天的 oldCondition
     * @param days 前 days 天
     * @param oldCondition 原始的 condition
     * @return 新的condition
     */
    public static Condition beforeDaysCondition(int days, Condition oldCondition) {

        //获得起始日期的过去 day 天的日期 beforeDate
        String beforeDate = DateUtils.minusDays(oldCondition.getStart_date(), days);

        return generateCondition(beforeDate, oldCondition.getEnd_date(), oldCondition);
    }

    public static Condition beforeDaysCondition(int days) {
        String[] start_end = DateUtils.getDateFromToday(days);

        return Condition.instance(start_end[0], start_end[1]);
    }

    /**
     * 返回一个起始日期为 2000-01-01，结束日期为 oldCondition.endDate 的 condition
     * @param oldCondition 原始的 condition
     * @return 新的 condition
     */
    public static Condition maxBeforeCondition(Condition oldCondition) {
        return generateCondition(Constants.MAX_BEFORE_DATE, oldCondition.getEnd_date(), oldCondition);
    }
    /**
     * 创建一个新的 Condition
     * @param start 新的 start
     * @param end 新的 end
     * @param oldCondition 之前的 condition
     * @return 新的 oldCondition
     */
    public static Condition generateCondition(String start, String end, Condition oldCondition) {
        Condition newCondition = new Condition();
        newCondition.setStart_date(start);
        newCondition.setEnd_date(end);
        newCondition.setChannel_id(oldCondition.getChannel_id());
        newCondition.setVersion_id(oldCondition.getVersion_id());

        return newCondition;
    }

}
