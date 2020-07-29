package edu.neu.titan.titanApp.dao;

import edu.neu.titan.titanApp.common.beans.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 访问留存分析相关数据库的接口
 */
public interface IRetentionDAO {

    /**
     * 从数据库中按条件查询天新增用户留存数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<RetentionDataDay> getRetentionDataIncreaseDay(Condition condition) ;

    /**
     * 从数据库中按条件查询周新增用户留存数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<RetentionDataWeek> getRetentionDataIncreaseWeek(Condition condition) ;

    /**
     * 从数据库中按条件查询月新增用户留存数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<RetentionDataMonth> getRetentionDataIncreaseMonth(Condition condition) ;

    /**
     * 从数据库中按条件查询天活跃用户留存数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<RetentionDataDay> getRetentionDataActiveDay(Condition condition) ;

    /**
     * 从数据库中按条件查询周活跃用户留存数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<RetentionDataWeek> getRetentionDataActiveWeek(Condition condition) ;

    /**
     * 从数据库中按条件查询月活跃用户留存数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<RetentionDataMonth> getRetentionDataActiveMonth(Condition condition) ;

    /**
     * 从数据库中查询一段时间以来用户活跃度数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<RetentionActivityData> getActivityData(Condition condition) ;


    /**
     * 从数据库中查询一段时间以来新增用户留存率数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Double> getOneDayAfterRetentionRate(Condition condition);

    /**
     * 从数据库中查询一段时间以来的新增用户次日留存率
     * @param condition 查询条件
     * @return 查询结果
     */
    double getAvgRetentionRate(Condition condition);
}
