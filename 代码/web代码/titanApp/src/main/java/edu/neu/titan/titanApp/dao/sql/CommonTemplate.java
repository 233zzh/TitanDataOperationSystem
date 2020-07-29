package edu.neu.titan.titanApp.dao.sql;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 保存数据库中公共常量访问所需的sql语句
 */
public class CommonTemplate {

    // 查找所有渠道的sql语句
    public static final String QUERY_ALL_CHANNEL = "select * from channel order by id";

    // 查找所有渠道的sql语句
    public static final String QUERY_ALL_VERSION = "select * from version order by id";

    // 根据版本筛选的sql语句片段
    public static String VERSION_CONDITION = " and version_id = :version_id";

    // 根据渠道筛选的sql语句片段
    public static String CHANNEL_CONDITION = " and channel_id = :channel_id";

    // 升序字段
    public static String ASC = " asc";

    // 降序字段
    public static String DESC = " desc";

}
