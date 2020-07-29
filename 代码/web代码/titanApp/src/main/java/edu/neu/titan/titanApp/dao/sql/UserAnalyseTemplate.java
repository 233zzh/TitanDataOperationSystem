package edu.neu.titan.titanApp.dao.sql;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/18
 * @Time: 10:51
 * @Version: 1.0
 * @Description: 用户分析的sql语句模版
 */
public class UserAnalyseTemplate {

    //求指定区间内的平均新增用户数
    public static String QUERY_INSTALLATION_AVG = "select avg(increase_num) as avg_increase_num from base_user_installation_day " +
            "where installation_date >= :start_date and installation_date <= :end_date";


    //求累计用户数
    public static String QUERY_INSTALLATION_SUM = "select sum(increase_num) as sum_increase_num from base_user_installation_day";

    //求指定区间内的平均活跃用户数
    public static String QUERY_ACTIVE_USER_AVG = "select avg(active_num) as avg_active_num from base_user_active_day " +
            "where active_date >= :start_date and active_date <= :end_date";

    //求指定区间内的新增用户总数，加上按日聚合就是每日新增用户数
    public static String QUERY_INSTALLATION_DAY = "select sum(increase_num) as sum_increase_num from base_user_installation_day " +
            "where installation_date >= :start_date and installation_date <= :end_date";


    //求指定区间内的周新增用户数
    public static String QUERY_INSTALLATION_WEEK = "select sum(increase_num) from " +
            "(select date_format(installation_date, '%Y-%u') installation_date, sum(increase_num) increase_num, channel_id, version_id from base_user_installation_day " +  //选出 channel_id 和 increase_id 为了后面做筛选
            "where installation_date >= :start_date and installation_date <= :end_date group by installation_date, version_id, channel_id) as temp " +
            "where true";   //这里写 true 是为了 SQLResult 中 version_id 和 channel_id的拼接


    //求指定区间的月新增用户
    public static String QUERY_INSTALLATION_MONTH = "select sum(increase_num) from " +
            "(select date_format(installation_date, '%Y-%m') installation_date, sum(increase_num) increase_num, channel_id, version_id from base_user_installation_day " +
            "where installation_date >= :start_date and installation_date <= :end_date group by installation_date, version_id, channel_id) as temp " +
            "where true";   //这里写 true 是为了 SQLResult 中 version_id 和 channel_id的拼接


    //求指定区间内的日活跃用户总数，加上按日聚合就是每日活跃用户数
    public static String QUERY_ACTIVE_USER_DAY = "select sum(active_num) as sum_active_num_day from base_user_active_day " +
            "where active_date >= :start_date and active_date <= :end_date";


    //求指定区间内的周活跃用户总数
    public static String QUERY_ACTIVE_USER_WEEK = "select sum(active_num) as sum_active_num_week from base_user_active_week " +
            "where active_date >= :start_date and active_date <= :end_date";


    //求指定区间内的月活跃用户总数
    public static String QUERY_ACTIVE_USER_MONTH = "select sum(active_num) as sum_active_month from base_user_active_month " +
            "where active_date >= :start_date and active_date <= :end_date";


    //求指定区间的启动次总数，加上按日期聚合就是每日启动次数
    public static String QUERY_LAUNCH_DAY = "select sum(start_num) as sum_launch_day from base_user_launch " +
            "where launch_date >= :start_date and launch_date <= :end_date";


    //求指定区间内的周启动次数
    public static String QUERY_LAUNCH_WEEK = "select sum(start_num) as sum_launch_week from " +
            "(select date_format(launch_date, '%Y-%u') launch_date, sum(start_num) start_num, channel_id, version_id from base_user_launch " +
            "where launch_date >= :start_date and launch_date <= :end_date group by launch_date, channel_id, version_id) as temp " +
            "where true";   //这里写 true 是为了 SQLResult 中 version_id 和 channel_id的拼接


    //求指定区间内的月启动次数
    public static String QUERY_LAUNCH_MONTH = "select sum(start_num) as sum_launch_month from " +
            "(select date_format(launch_date, '%Y-%m') launch_date, sum(start_num) start_num, channel_id, version_id from base_user_launch " +
            "where launch_date >= :start_date and launch_date <= :end_date group by launch_date, channel_id, version_id) as temp " +
            "where true";   //这里写 true 是为了 SQLResult 中 version_id 和 channel_id的拼接

    public static String QUERY_VERSION_NAME = "select name from version ";

    //求新增用户的TOP10版本分布
    public static String QUERY_INSTALLATION_TOP_VERSION = "select name,sum(increase_num) as value from base_user_installation_day left join version on version_id = id where version_id in " +
            "(select version_id from(select version_id from base_user_installation_day group by version_id  order by sum(increase_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and installation_date >= :start_date and installation_date <= :end_date ";

    //求活跃用户的TOP10版本分布
    public static String QUERY_ACTIVE_TOP_VERSION = "select name,sum(active_num) as value from base_user_active_day left join version on version_id = id where version_id in " +
            "(select version_id from(select version_id from base_user_active_day group by version_id  order by sum(active_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and active_date >= :start_date and active_date <= :end_date ";


    //求启动次数的TOP10版本分布
    public static String QUERY_LAUNCH_TOP_VERSION = "select name,sum(start_num) as value from base_user_launch left join version on version_id = id where version_id in " +
            "(select version_id from(select version_id from base_user_launch group by version_id  order by sum(start_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and launch_date >= :start_date and launch_date <= :end_date ";

    //求新增用户的TOP10版本分布
    public static String QUERY_INSTALLATION_TOP_CHANNEL = "select name,sum(increase_num) as value from base_user_installation_day left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_installation_day group by channel_id  order by sum(increase_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and installation_date >= :start_date and installation_date <= :end_date ";

    //求活跃用户的TOP10版本分布
    public static String QUERY_ACTIVE_TOP_CHANNEL = "select name,sum(active_num) as value from base_user_active_day left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_active_day group by channel_id  order by sum(active_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and active_date >= :start_date and active_date <= :end_date ";


    //求启动次数的TOP10版本分布
    public static String QUERY_LAUNCH_TOP_CHANNEL = "select name,sum(start_num) as value from base_user_launch left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_launch group by channel_id  order by sum(start_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and launch_date >= :start_date and launch_date <= :end_date ";

    //新增用户按日聚合
    public static String GROUP_BY_INSTALLATION = " group by installation_date";


    //活跃用户按日聚合
    public static String GROUP_BY_ACTIVE = " group by active_date";


    //启动次数按日聚合
    public static String GROUP_BY_LAUNCH = " group by launch_date";

    //版本聚合
    public static String GROUP_BY_VERSION = " group by version_id";

    //版本排序
    public static String ORDER_BY_VERSION = " order by version_id";

    //渠道聚合
    public static String GROUP_BY_CHANNEL = " group by channel_id";

    //渠道排序
    public static String ORDER_BY_CHANNEL = " order by channel_id";

    //id 排序，针对 id 没有前缀的表
    public static String ORDER_BY_ID = " order by id";

    //新增用户按日期，版本聚合
    public static String GROUP_BY_INSTALLATION_VERSION = " group by installation_date, version_id";

    //新增用户按日期，版本排序
    public static String ORDER_BY_INSTALLATION_VERSION = " order by version_id, installation_date";

    //活跃用户按日期，版本聚合
    public static String GROUP_BY_ACTIVE_VERSION = " group by active_date, version_id";

    //活跃用户按日期，版本排序
    public static String ORDER_BY_ACTIVE_VERSION = " order by version_id, active_date";

    //启动次数按日期，版本聚合
    public static String GROUP_BY_LAUNCH_VERSION = " group by launch_date, version_id";

    //启动次数按日期，版本排序
    public static String ORDER_BY_LAUNCH_VERSION = " order by version_id, launch_date";

}
