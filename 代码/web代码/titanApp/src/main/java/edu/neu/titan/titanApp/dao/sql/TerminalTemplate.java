package edu.neu.titan.titanApp.dao.sql;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/19
 * @Time: 9:52
 * @Version: 1.0
 * @Description: Description
 */
public class TerminalTemplate {

    // 查询机型数据的sql片段
    public static final String QUERY_MODEL = "select name, sum(increase_num) increase_num, sum(start_num) start_num from base_terminal_device_model join model on model_id=id where model_date >= :start_date and model_date <= :end_date";
    // 查询分辨率数据的sql片段
    public static final String QUERY_RESOLUTION = "select name, sum(increase_num) increase_num, sum(start_num) start_num from base_terminal_device_resolution join resolution on resolution_id=id where resolution_date >= :start_date and resolution_date <= :end_date";
    // 查询操作系统数据的sql片段
    public static final String QUERY_OS = "select name, sum(increase_num) increase_num, sum(start_num) start_num from base_terminal_device_os join os on os_id=id where os_date >= :start_date and os_date <= :end_date";
    // 查询联网方式数据的sql片段
    public static final String QUERY_NETWORK = "select name, sum(increase_num) increase_num, sum(start_num) start_num from base_terminal_network join network on network_id=id where network_date >= :start_date and network_date <= :end_date";
    // 查询省份数据的sql片段
    public static final String QUERY_REGION = "select name, sum(increase_num) increase_num, sum(start_num) start_num, sum(active_num) active_num from base_terminal_region_province join province on province_id=id where province_date >= :start_date and province_date <= :end_date";

    // 分组语句
    public static final String GROUP_BY_ID = " group by id";

    // 排序语句
    public static final String ORDER_BY_INCREASE = " order by increase_num";
}
