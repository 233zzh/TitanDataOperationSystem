package edu.neu.titan.titanApp.dao.sql;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/18
 * @Time: 18:24
 * @Version: 1.0
 * @Description: Description
 */
public class ParticipationTemplate {

    /**
     * 获得用户参与度模块中使用时长子功能中的单次使用时长分布
     */
    public final static String QUERY_DURATION_SINGLE = "select sum(start_num) from base_participation_duration " +
            "where duration_date >= :start_date and duration_date <= :end_date";

    /**
     * 获得用户参与度模块中使用时长子功能中的日使用时长分布
     */
    public final static String QUERY_DURATION_DAY = "select sum(user_num) from base_participation_duration " +
            "where duration_date >= :start_date and duration_date <= :end_date";

    /**
     * 获得用户参与度模块中使用频率子功能中的日启动次数分布
     */
    public final static String QUERY_FREQUENCY_DAY = "select sum(user_num) from base_participation_frequency_day " +
            "where frequency_date >= :start_date and frequency_date <= :end_date";

    /**
     * 获得用户参与度模块中使用时长子功能中的周启动次数分布
     */
    public final static String QUERY_FREQUENCY_WEEK = "select sum(user_num) from base_participation_frequency_week " +
            "where frequency_date >= :start_date and frequency_date <= :end_date";

    /**
     * 获得用户参与度模块中使用频率子功能中的月启动次数分布
     */
    public final static String QUERY_FREQUENCY_MONTH = "select sum(user_num) from base_participation_frequency_month " +
            "where frequency_date >= :start_date and frequency_date <= :end_date";

    /**
     * 获得用户参与度模块中访问页面子功能中的访问页面分布
     */
    public final static String QUERY_PAGE = "select sum(start_num) from base_participation_page " +
            "where page_date >= :start_date and page_date <= :end_date";

    /**
     * 获得用户参与度模块中使用间隔子功能中的使用间隔分布
     */
    public final static String QUERY_INTERVAL = "select sum(start_num) from base_participation_interval " +
            "where interval_date >= :start_date and interval_date <= :end_date";

    /**
     * 获得用户参与度模块中使用时长子功能中的使用时长范围
     */
    public final static String QUERY_DURATION_RANGE = "select name from duration_range ";
    /**
     * 获得用户参与度模块中使用频率子功能中的日启动次数范围
     */
    public final static String QUERY_FREQUENCY_DAY_RANGE = "select name from frequency_range_day ";
    /**
     * 获得用户参与度模块中使用频率子功能中的周启动次数范围
     */
    public final static String QUERY_FREQUENCY_WEEK_RANGE = "select name from frequency_range_week ";
    /**
     * 获得用户参与度模块中使用频率子功能中的月启动次数范围
     */
    public final static String QUERY_FREQUENCY_MONTH_RANGE = "select name from frequency_range_month ";
    /**
     * 获得用户参与度模块中访问页面子功能中的访问页面范围
     */
    public final static String QUERY_PAGE_RANGE = "select name from page_range ";
    /**
     * 获得用户参与度模块中使用间隔子功能中的使用间隔范围
     */
    public final static String QUERY_INTERVAL_RANGE = "select name from interval_range ";

    public final static String GROUP_BY_DURATION_DATE = " group by duration_date";

    public final static String GROUP_BY_FREQUENCY_DATE = " group by frequency_date";

    public final static String GROUP_BY_PAGE_DATE = " group by page_date";

    public final static String GROUP_BY_INTERVAL_DATE = " group by interval_date";

    public final static String GROUP_BY_RANGE_ID = " group by range_id";

    public final static String GROUP_BY_ID = " group by id";


}
