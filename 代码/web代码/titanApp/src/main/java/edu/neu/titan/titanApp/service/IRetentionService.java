package edu.neu.titan.titanApp.service;

import edu.neu.titan.titanApp.common.beans.Condition;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 用于处理留存分析业务的service接口
 */
public interface IRetentionService {

    /**
     * 得到天新增用户存留数所需的数据
     * @param condition 封装条件的实例
     * @return 结果数组
     */
    Integer[][] getRetentionDataIncreaseDay(Condition condition) ;

    /**
     * 得到周新增用户存留数所需的数据
     * @param condition 封装条件的实例
     * @return 结果数组
     */
    Integer[][] getRetentionDataIncreaseWeek(Condition condition) ;

    /**
     * 得到月新增用户存留数所需的数据
     * @param condition 封装条件的实例
     * @return 结果数组
     */
    Integer[][] getRetentionDataIncreaseMonth(Condition condition) ;

    /**
     * 得到天活跃用户存留数所需的数据
     * @param condition 封装条件的实例
     * @return 结果数组
     */
    Integer[][] getRetentionDataActiveDay(Condition condition) ;

    /**
     * 得到周活跃用户存留数所需的数据
     * @param condition 封装条件的实例
     * @return 结果数组
     */
    Integer[][] getRetentionDataActiveWeek(Condition condition) ;

    /**
     * 得到月活跃用户存留数所需的数据
     * @param condition 封装条件的实例
     * @return 结果数组
     */
    Integer[][] getRetentionDataActiveMonth(Condition condition) ;

    /**
     * 得到近期一段时间的用户新鲜度数据
     * @return 结果数组
     */
    Pair<String[], Integer[][]> getRetentionDataFreshness() ;

    /**
     * 得到近期一段时间的用户活跃度数据
     * @return 结果数组
     */
    Pair<String[], Integer[][]> getRetentionDataActivity() ;

    /**
     * 得到一段时间以来新增用户留存率数据
     * @param condition 查询条件
     * @return 结果数组
     */
    Double[] getOneDayAfterRetentionRate(Condition condition);

}
