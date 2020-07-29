package edu.neu.titan.titanApp.dao.sql;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/18
 * @Time: 15:50
 * @Version: 1.0
 * @Description: 渠道分析的sql语句模版
 */
public class ChannelTemplate {
    /**
     * 获取所有渠道的名字
     *
     * @return
     */
    public final static String QUERY_NAME_ALL_CHANNELS = "select name from channel ";
    /**
     * 获取所有渠道的新增用户
     *
     * @return
     */
    public final static String QUERY_INSTALLATION_ALL_CHANNELS = "select sum(increase_num) from base_user_installation_day where installation_date=:end_date ";
    /**
     * 获取所有渠道的活跃用户
     *
     * @return
     */
    public final static String QUERY_ACTIVE_TABLE_ALL_CHANNELS = "select sum(active_num) as sum from base_user_active_day where active_date=:end_date ";
    /**
     * 求所有渠道的累计新增用户
     *
     * @return
     */
    public final static String QUERY_SUM_INSTALLATION = "select sum(increase_num)  from base_user_installation_day ";
    /**
     * 求新增用户的TOP10渠道
     */
    public final static String QUERY_INSTALLATION_TOP10_CHANNELS = "select sum(increase_num) as num from base_user_installation_day left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_installation_day group by channel_id  order by sum(increase_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and installation_date >= :start_date and installation_date <= :end_date ";
    /**
     * 求活跃用户的TOP10渠道
     */
    public final static String QUERY_ACTIVE_TOP10_CHANNELS = "select sum(active_num) as num from base_user_active_day left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_active_day group by channel_id  order by sum(active_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and active_date >= :start_date and active_date <= :end_date ";
    /**
     * 求启动次数的TOP10渠道
     */
    public final static String QUERY_LAUNCH_TOP10_CHANNELS = "select sum(start_num) as num from base_user_launch left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_launch group by channel_id  order by sum(start_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and launch_date >= :start_date and launch_date <= :end_date ";
    /**
     * 求新增用户的TOP10渠道名称
     */
    public final static String QUERY_INSTALLATION_TOP10_CHANNELS_NAME = "select name  from base_user_installation_day left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_installation_day group by channel_id  order by sum(increase_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and installation_date >= :start_date and installation_date <= :end_date ";
    /**
     * 求活跃用户的TOP10渠道名称
     */
    public final static String QUERY_ACTIVE_TOP10_CHANNELS_NAME = "select name  from base_user_active_day left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_active_day group by channel_id  order by sum(active_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and active_date >= :start_date and active_date <= :end_date ";
    /**
     * 求启动次数的TOP10渠道名称
     */
    public final static String QUERY_LAUNCH_TOP10_CHANNELS_NAME = "select name from base_user_launch left join channel on channel_id = id where channel_id in " +
            "(select channel_id from(select channel_id from base_user_launch group by channel_id  order by sum(start_num) desc limit 10)as top10) " +      //这里使用了两个select是因为sql不支持在in里使用limit
            "and launch_date >= :start_date and launch_date <= :end_date ";
    public final static String GROUP_BY_INSTALLATION_DATE = " group by installation_date";

    public final static String GROUP_BY_ACTIVE_DATE = " group by active_date";

    public final static String GROUP_BY_LAUNCH_DATE = " group by launch_date";

    public final static String GROUP_BY_INSTALLATION_DATE_AND_CHANNEL = " group by installation_date, channel_id";

    public final static String GROUP_BY_ACTIVE_DATE_AND_CHANNEL = " group by active_date, channel_id";

    public final static String GROUP_BY_LAUNCH_DATE_AND_CHANNEL = " group by launch_date, channel_id";

    public final static String GROUP_BY_CHANNEL_ID = " group by channel_id";

    public final static String ORDER_BY_CHANNEL_AND_INSTALLATION_DATE = " order by channel_id,installation_date";

    public final static String ORDER_BY_CHANNEL_AND_ACTIVE_DATE = " order by channel_id,active_date";

    public final static String ORDER_BY_CHANNEL_AND_LAUNCH_DATE = " order by channel_id,launch_date";

}
