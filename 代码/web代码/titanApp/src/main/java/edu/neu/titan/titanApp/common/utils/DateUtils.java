package edu.neu.titan.titanApp.common.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 处理日期相关的工具类
 */
public class DateUtils {

    // 字符串与日期转换的格式
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");

    /**
     * 将日期范围转换为日期字符串数组
     * @param start_date 开始日期的字符串
     * @param end_date  结束日期的字符串
     * @return 日期字符串数据
     */
    public static String[] getDays(String start_date, String end_date) {
        // 字符串转化为DateTime
        DateTime start = getDateTime(start_date);
        DateTime end = getDateTime(end_date);
        // 获取间隔时间
        int num = Days.daysBetween(start, end).getDays();
        // 结果数组
        String[] dates = new String[num+1];
        // 加入起止日期
        dates[0] = start.toString(DATE_FORMAT);
        dates[num] = end.toString(DATE_FORMAT);
        // 循环加入中间日期
        for (int i = 1; i < num; i++) {
            start = start.plusDays(1);
            dates[i] = start.toString(DATE_FORMAT);
        }
        return dates;
    }

    /**
     * 将日期范围转换为周范围字符串数组
     * @param start_date 开始日期的字符串
     * @param end_date  结束日期的字符串
     * @return 日期字符串数据
     */
    public static String[] getWeeks(String start_date, String end_date) {
        // 字符串转化为DateTime
        LocalDate start = new LocalDate(start_date);
        LocalDate end = new LocalDate(end_date);  //这里减一是因为要的是上周的最后一天

        // 结果列表
        List<String> weeks = new ArrayList<>();

        // 找到范围内每周第一天
        LocalDate curWeek = start.withDayOfWeek(DateTimeConstants.MONDAY);

        //如果结束日期在当前周，则要把周减一
        if(isCurrentWeek(end)) {
            end = end.minusWeeks(1);
        }

        // 当早于结束时间时将此周范围加入list
        while (curWeek.compareTo(end)<0) {
            // 日期范围字符
            String res = curWeek.toString()+"~"+curWeek.withDayOfWeek(DateTimeConstants.SUNDAY);
            weeks.add(res);
            curWeek = curWeek.plusWeeks(1);
        }
        // 返回结果
        return weeks.toArray(new String[0]);
    }

    /**
     * 将日期范围转换为月范围字符串数组
     * @param start_date 开始日期的字符串
     * @param end_date  结束日期的字符串
     * @return 日期字符串数据
     */
    public static String[] getMonths(String start_date, String end_date) {
        // 字符串转化为DateTime
        LocalDate start = new LocalDate(start_date);
        LocalDate end = new LocalDate(end_date);
        // 结果列表
        List<String> months = new ArrayList<>();

        // 找到范围内每月第一天
        LocalDate curMonth = start.dayOfMonth().withMinimumValue();

        //如果结束日期在当前月，则要把月份减一
        if(isCurrentMonth(end)) {
            end = end.minusMonths(1);
        }

        // 当早于结束时间时将此周范围加入list
        while (curMonth.compareTo(end)<0) {
            // 日期范围字符
            String res = curMonth.toString()+"~"+curMonth.dayOfMonth().withMaximumValue();
            months.add(res);
            curMonth = curMonth.plusMonths(1);
        }
        // 返回结果
        return months.toArray(new String[0]);
    }

    /**
     * 将字符串日期转换为 DateTime类型
     * @param dateStr 日期字符串
     * @return DATeTime日期
     */
    public static DateTime getDateTime(String dateStr) {
        return new DateTime(dateStr);
    }

    /**
     * 得到据今天指定天数的日期字符串数组
     * @param defaultInterval 指定的天数间隔
     * @return 结果数组
     */
    public static String[] getDaysFromToday(int defaultInterval) {
        // 今天
        LocalDate today = new LocalDate();
        // 返回数组
        return getDays(today.minusDays(defaultInterval).toString(),today.toString());
    }

    /**
     * 将字符串日期转换为 SQL中可以识别的Date类型
     * @param dateStr 日期字符串
     * @return DATeTime日期
     */
    public static Date getDateSQL(String dateStr) {
        return new Date(new DateTime(dateStr).getMillis());
    }

    /**
     * 将开始日期转换成所在周周一对应的日期
     * @param start 待转换的开始日期
     * @return 新的日期
     */
    public static String convertWeekDateStart(String start) {
        return new LocalDate(start).withDayOfWeek(DateTimeConstants.MONDAY).toString(); //开始日期的所在周的周一
    }

    /**
     * 将结束日期转换成对应的周，如果日期在当前周，则返回上一周周日对应的日期，否则返该日期所在周的周日对应的日期
     * @param end 待转换的结束日期
     * @return 新的日期
     */
    public static String convertWeekDateEnd(String end) {
        LocalDate newEnd = new LocalDate(end); //新的 end，会根据当前 end 是不是当天所在周来返回不同的值

        if(isCurrentWeek(newEnd)) {     //如果在当前周，就减一周
            newEnd = newEnd.minusWeeks(1);
        }

        return newEnd.dayOfWeek().withMaximumValue().toString();    //返回周日的日期
    }

    /**
     * 将开始日期转换成所在月月末对应的日期
     * @param start 待转换的开始日期
     * @return 新的日期
     */
    public static String convertMonthDateStart(String start) {
        return new LocalDate(start).dayOfMonth().withMinimumValue().toString(); //开始日期的所在周的周一
    }

    /**
     * 将结束日期转换成对应的月的日期，如果日期在当前月，则返回上一月月末对应的日期，否则返该日期所在月的月末对应的日期
     * @param end 待转换的结束日期
     * @return 新的日期
     */
    public static String convertMonthDateEnd(String end) {
        LocalDate newEnd = new LocalDate(end); //新的 end，会根据当前 end 是不是当天所在周来返回不同的值

        if(isCurrentMonth(newEnd)) {     //如果在当前月，就减一个月
            newEnd = newEnd.minusMonths(1);
        }

        return newEnd.dayOfMonth().withMaximumValue().toString();    //返回月末的日期
    }


    /**
     * 验证日期是不是在当前周
     * @param date 待验证日期
     * @return 验证结果
     */
    private static boolean isCurrentWeek(LocalDate date) {
        LocalDate today = new LocalDate();
        return date.getWeekOfWeekyear() == today.getWeekOfWeekyear();
    }

    /**
     * 验证日期是不是在当前月
     * @param date 待验证日期
     * @return 验证结果
     */
    private static boolean isCurrentMonth(LocalDate date) {
        LocalDate today = new LocalDate();
        return date.getMonthOfYear() == today.getMonthOfYear();
    }

    /**
     * 将日期字符串转化为增加指定天数之后的日期字符串
     * @param str 日期字符串
     * @param num 指定天数
     * @return 结果字符
     */
    public static String plusDays(String str, int num) {
        return new LocalDate(str).plusDays(num).toString();
    }

    /**
     * 将日期字符串转化为减少指定天数之后的日期字符串
     * @param str 日期字符串
     * @param num 指定天数
     * @return 结果字符
     */
    public static String minusDays(String str, int num) {
        return new LocalDate(str).minusDays(num).toString();
    }

    /**
     * 返回今天的日期和今天的前指定天数的所在日期
     * @param num 指定天数
     * @return 结果日期
     */
    public static String[] getDateFromToday(int num) {
        LocalDate today = new LocalDate();

        return new String[]{today.minusDays(num).toString(), today.toString()};
    }

    /**
     * 返回两个日期之间的天数
     * @param start_date 开始日期
     * @param end_date 结束日期
     * @return 两个日期之间的天数
     */
    public static int getDateInterval(String start_date, String end_date) {
        // 字符串转化为DateTime
        DateTime start = getDateTime(start_date);
        DateTime end = getDateTime(end_date);
        // 获取间隔时间
        int num = Days.daysBetween(start, end).getDays();

        return num;
    }
}
