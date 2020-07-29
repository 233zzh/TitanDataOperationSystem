package edu.neu.titan.titanApp.dao.sql;

public class RetentionTemplate {

    // 天新增人数留存数查询语句
    public static String QUERY_INCREASE_RETENTION_DAY = "select sum(1_day_after_num) day1_num, sum(2_day_after_num) day2_num, " +
            "sum(3_day_after_num) day3_num, sum(4_day_after_num) day4_num, sum(5_day_after_num) day5_num, " +
            "sum(6_day_after_num) day6_num, sum(7_day_after_num) day7_num, sum(14_day_after_num) day14_num, " +
            "sum(30_day_after_num) day30_num from base_retention_installation_day where retention_date >= :start_date and retention_date <= :end_date";

    // 周新增人数留存数查询语句
    public static String QUERY_INCREASE_RETENTION_WEEK = "select sum(1_week_after_num) week1_num, sum(2_week_after_num) week2_num, " +
            "sum(3_week_after_num) week3_num, sum(4_week_after_num) week4_num, sum(5_week_after_num) week5_num, " +
            "sum(6_week_after_num) week6_num, sum(7_week_after_num) week7_num, sum(8_week_after_num) week8_num, " +
            "sum(9_week_after_num) week9_num from base_retention_installation_week where retention_date >= :start_date and retention_date <= :end_date";

    // 月新增人数留存数查询语句
    public static String QUERY_INCREASE_RETENTION_MONTH = "select sum(1_month_after_num) month1_num, sum(2_month_after_num) month2_num, " +
            "sum(3_month_after_num) month3_num, sum(4_month_after_num) month4_num, sum(5_month_after_num) month5_num, " +
            "sum(6_month_after_num) month6_num, sum(7_month_after_num) month7_num, sum(8_month_after_num) month8_num, " +
            "sum(9_month_after_num) month9_num from base_retention_installation_month where retention_date >= :start_date and retention_date <= :end_date";

    // 天活跃人数留存数查询语句
    public static String QUERY_ACTIVE_RETENTION_DAY = "select sum(1_day_after_num) day1_num, sum(2_day_after_num) day2_num, " +
            "sum(3_day_after_num) day3_num, sum(4_day_after_num) day4_num, sum(5_day_after_num) day5_num, " +
            "sum(6_day_after_num) day6_num, sum(7_day_after_num) day7_num, sum(14_day_after_num) day14_num, " +
            "sum(30_day_after_num) day30_num from base_retention_active_day where retention_date >= :start_date and retention_date <= :end_date";

    // 周活跃人数留存数查询语句
    public static String QUERY_ACTIVE_RETENTION_WEEK = "select sum(1_week_after_num) week1_num, sum(2_week_after_num) week2_num, " +
            "sum(3_week_after_num) week3_num, sum(4_week_after_num) week4_num, sum(5_week_after_num) week5_num, " +
            "sum(6_week_after_num) week6_num, sum(7_week_after_num) week7_num, sum(8_week_after_num) week8_num, " +
            "sum(9_week_after_num) week9_num from base_retention_active_week where retention_date >= :start_date and retention_date <= :end_date";

    // 月活跃人数留存数查询语句
    public static String QUERY_ACTIVE_RETENTION_MONTH = "select sum(1_month_after_num) month1_num, sum(2_month_after_num) month2_num, " +
            "sum(3_month_after_num) month3_num, sum(4_month_after_num) month4_num, sum(5_month_after_num) month5_num, " +
            "sum(6_month_after_num) month6_num, sum(7_month_after_num) month7_num, sum(8_month_after_num) month8_num, " +
            "sum(9_month_after_num) month9_num from base_retention_active_month where retention_date >= :start_date and retention_date <= :end_date";

    // 用户活跃度查询语句
    public static String QUERY_ACTIVITY_RETENTION = "select 1_day_active_num day1_num, 2_day_active_num day2_num, " +
            "3_day_active_num day3_num, 4_day_active_num day4_num, 5_day_active_num day5_num, " +
            "6_day_active_num day6_num, 7_day_active_num day7_num, 7p_day_active_num day7p_num " +
            "from base_retention_activity where activity_date >= :start_date and activity_date <= :end_date";


    // 新增用户次日留存数
    public static final String QUERY_INCREASE_ONE_DAY_AFTER_RETENTION = "select sum(1_day_after_num)/sum(increase_num) from base_retention_installation_day " +
            "join base_user_installation_day on retention_date=installation_date where retention_date >= :start_date and retention_date <= :end_date";

    // 一段时间内平均留存率
    public static  final String QUERY_AVG_RETENTION_RATE = "select avg(retention_num/all_num) from " +
            "(select sum(1_day_after_num) retention_num, sum(increase_num) all_num from base_retention_installation_day join base_user_installation_day" +
            " on retention_date=installation_date where retention_date >= :start_date and retention_date <= :end_date group by installation_date) T";

    // 分组语句
    public static String GROUP_BY_DATE = " group by retention_date";


    
    
}
