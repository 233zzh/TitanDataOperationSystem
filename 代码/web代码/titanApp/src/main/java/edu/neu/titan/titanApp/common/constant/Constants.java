package edu.neu.titan.titanApp.common.constant;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 保存应用中的默认值等常数
 */
public class Constants {

    // 留存分析中新鲜度分析和活跃度分析需要展示的数据据今天的天数
    public static final int DEFAULT_RETENTION_DAY_NUM = 9;

    // Condition中用于表示字段不需要的值
    public static final int CONDITION_INVALID_VALUE = -1;

    // 用户新鲜度最久追溯几天前
    public static final int DEFAULT_DAYS_BEFORE = 7;

    // 常用的分析指标：前 7 天
    public static final int DEFAULT_DAYS_OVERVIEW_DAY_1 = 7;

    // 常用的分析指标：前 30 天
    public static final int DEFAULT_DAYS_OVERVIEW_DAY_2 = 30;

    // 常用的分析指标：前 730 天，即两年
    public static final int DEFAULT_DAYS_OVERVIEW_DAY_3 = 730;

    // 系统分析数据最早能够统计到的日期，2000 年的第一个星期一
    public static final String MAX_BEFORE_DATE = "2018-01-03";
}
