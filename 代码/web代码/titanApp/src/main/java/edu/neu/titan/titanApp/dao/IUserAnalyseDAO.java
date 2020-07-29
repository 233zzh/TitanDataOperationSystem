package edu.neu.titan.titanApp.dao;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.Distribution;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.dao.sql.UserAnalyseTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/18
 * @Time: 9:47
 * @Version: 1.0
 * @Description: 访问用户分析相关数据库的接口
 */
public interface IUserAnalyseDAO {

    //先写几个方法试试看能不能实现功能

    /**
     * 从数据库中查询新增用户的平均数
     * @param condition 查询条件
     * @return  查询结果
     */
    Integer getInstallationAvg(Condition condition);

    /**
     * 从数据库中查询新增用户的总数
     * @param condition 查询条件
     * @return  查询结果
     */
    Integer getInstallationSum(Condition condition);

    /**
     * 从数据库中查询活跃用户的平均数
     * @param condition 查询条件
     * @return  查询结果
     */
    Integer getActiveUserAvg(Condition condition);

    /**
     * 从数据库中查询活跃用户的总数
     * @param condition 查询条件
     * @return  查询结果
     */
    Integer getActiveUserSum(Condition condition);

    /**
     * 从数据库中查询天新增用户
     * @param condition 查询条件
     * @return  查询结果数组
     */
    List<Integer> getInstallationDataDay(Condition condition);

    /**
     * 从数据库中查询周新增用户
     * @param condition 查询条件
     * @return  查询结果数组
     */
    List<Integer> getInstallationDataWeek(Condition condition);

    /**
     * 从数据库中查询月新增用户
     * @param condition 查询条件
     * @return  查询结果数组
     */
    List<Integer> getInstallationDataMonth(Condition condition);

    /**
     * 从数据库中查询天活跃用户趋势
     * @param condition 查询条件
     * @return  查询结果数组
     */
    List<Integer> getActiveTrendDataDay(Condition condition);

    /**
     * 从数据库中查询周活跃用户趋势
     * @param condition 查询条件
     * @return  查询结果数组
     */
    List<Integer> getActiveTrendDataWeek(Condition condition);

    /**
     * 从数据库中查询月活跃用户趋势
     * @param condition 查询条件
     * @return  查询结果数组
     */
    List<Integer> getActiveTrendDataMonth(Condition condition);

    /**
     * 从数据库中查询天启动次数数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Integer> getLaunchDataDay(Condition condition);

    /**
     * 从数据库中查询周启动次数数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Integer> getLaunchDataWeek(Condition condition);

    /**
     * 从数据库中查询月启动次数数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Integer> getLaunchDataMonth(Condition condition);

    /**
     * 从数据库中查询新增用户的按日期聚合的版本分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getDateTopVersionDataInstallation(Condition condition);

    /**
     * 从数据库中查询活跃用户按日期聚合的的版本分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getDateTopVersionDataActive(Condition condition);

    /**
     * 从数据库中启动次数按日期聚合的的版本分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getDateTopVersionDataLaunch(Condition condition);

    /**
     * 从数据库中查询新增用户的只按名称聚合的版本分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getTopVersionDataLInstallation(Condition condition);

    /**
     * 从数据库中查询活跃用户的只按名称聚合的版本分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getTopVersionDataActive(Condition condition);

    /**
     * 从数据库中查询启动次数的只按名称聚合的版本分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getTopVersionDataLaunch(Condition condition);

    /**
     * 从数据库中查询新增用户的只按名称聚合的渠道分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getTopChannelDataLInstallation(Condition condition);

    /**
     * 从数据库中查询活跃用户的只按名称聚合的渠道分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getTopChannelDataActive(Condition condition);

    /**
     * 从数据库中查询启动次数的只按名称聚合的渠道分布数据
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<Distribution> getTopChannelDataLaunch(Condition condition);

    /**
     * 查询所有版本的名称
     * @return 版本名称列表
     */
    List<String> getAllVersionName();

    /**
     * 返回所有版本的新增用户分布
     * @param condition 传入的条件
     * @return 版本分布列表
     */
    List<Integer> getAllVersionDataInstallation(Condition condition);

    /**
     * 返回所有版本的活跃用户分布
     * @param condition 传入的条件
     * @return 版本分布列表
     */
    List<Integer> getAllVersionDataActive(Condition condition);

    /**
     * 返回所有版本的启动次数用户分布
     * @param condition 传入的条件
     * @return 版本分布列表
     */
    List<Integer> getAllVersionDataLaunch(Condition condition);
}
