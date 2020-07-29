package edu.neu.titan.titanApp.dao.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.Distribution;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.dao.IUserAnalyseDAO;
import edu.neu.titan.titanApp.dao.sql.UserAnalyseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/17
 * @Time: 14:19
 * @Version: 1.0
 * @Description: IUserAnalyseDao 接口实现类
 */

@Repository
public class UserAnalyseDAOImpl implements IUserAnalyseDAO {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public UserAnalyseDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * 返回新增用户的平均数
     * @param condition 查询条件
     * @return 新增用户平均数
     */
    @Override
    public Integer getInstallationAvg(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_AVG)
                .condition(condition)
                .build()
                .getResultSingle(template);
    }

    /**
     * 返回新增用户的总数
     * @param condition 查询条件
     * @return 新增用户总数
     */
    @Override
    public Integer getInstallationSum(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_SUM)
                .condition(condition)
                .build()
                .getResultSingle(template);
    }

    /**
     * 返回活跃用户的平均数
     * @param condition 查询条件
     * @return 活跃用户平均数
     */
    @Override
    public Integer getActiveUserAvg(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_USER_AVG)
                .condition(condition)
                .build()
                .getResultSingle(template);
    }

    /**
     * 返回活跃用户的总数
     * @param condition 查询条件
     * @return 活跃用户总数
     */
    @Override
    public Integer getActiveUserSum(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_USER_DAY)
                .condition(condition)
                .build()
                .getResultSingle(template);
    }

    /**
     * 返回天新增用户
     * @param condition 查询条件
     * @return 每天的新增用户列表
     */
    @Override
    public List<Integer> getInstallationDataDay(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_DAY)
                .sql_group(UserAnalyseTemplate.GROUP_BY_INSTALLATION)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 返回周新增用户
     * @param condition 查询条件
     * @return 每周的新增用户列表
     */
    @Override
    public List<Integer> getInstallationDataWeek(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_WEEK)
                .sql_group(UserAnalyseTemplate.GROUP_BY_INSTALLATION)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 返回月新增用户
     * @param condition 查询条件
     * @return 每月的新增用户列表
     */
    @Override
    public List<Integer> getInstallationDataMonth(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_MONTH)
                .sql_group(UserAnalyseTemplate.GROUP_BY_INSTALLATION)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 返回天活跃趋势
     * @param condition 查询条件
     * @return 天活跃用户列表
     */
    @Override
    public List<Integer> getActiveTrendDataDay(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_USER_DAY)
                .sql_group(UserAnalyseTemplate.GROUP_BY_ACTIVE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 返回周活跃趋势
     * @param condition 查询条件
     * @return 周活跃趋势列表
     */
    @Override
    public List<Integer> getActiveTrendDataWeek(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_USER_WEEK)
                .sql_group(UserAnalyseTemplate.GROUP_BY_ACTIVE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 返回月活跃趋势
     * @param condition 查询条件
     * @return 月活跃趋势列表
     */
    @Override
    public List<Integer> getActiveTrendDataMonth(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_USER_MONTH)
                .sql_group(UserAnalyseTemplate.GROUP_BY_ACTIVE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 查询天启动次数
     * @param condition 查询条件
     * @return 天启动次数列表
     */
    @Override
    public List<Integer> getLaunchDataDay(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_LAUNCH_DAY)
                .sql_group(UserAnalyseTemplate.GROUP_BY_LAUNCH)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 查询周启动次数
     * @param condition 查询条件
     * @return 周启动次数列表
     */
    @Override
    public List<Integer> getLaunchDataWeek(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_LAUNCH_WEEK)
                .sql_group(UserAnalyseTemplate.GROUP_BY_LAUNCH)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 查询月启动次数
     * @param condition 查询条件
     * @return 月启动次数列表
     */
    @Override
    public List<Integer> getLaunchDataMonth(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_LAUNCH_MONTH)
                .sql_group(UserAnalyseTemplate.GROUP_BY_LAUNCH)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 查询新增用户按日期聚合的的TOP10版本分布
     * @param condition 查询条件
     * @return 新增用户版本分布列表
     */
    @Override
    public List<Distribution> getDateTopVersionDataInstallation(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_TOP_VERSION)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_INSTALLATION_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_INSTALLATION_VERSION)
                .build()
                .getResult(template);
    }

    /**
     * 查询活跃用户按日期聚合的TOP10版本分布
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */
    @Override
    public List<Distribution> getDateTopVersionDataActive(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_TOP_VERSION)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_ACTIVE_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_ACTIVE_VERSION)
                .build()
                .getResult(template);
    }

    /**
     * 查询启动按次数按日期聚合的TOP10版本分布
     * @param condition 查询条件
     * @return 启动次数版本分布列表
     */
    @Override
    public List<Distribution> getDateTopVersionDataLaunch(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_LAUNCH_TOP_VERSION)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_LAUNCH_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_LAUNCH_VERSION)
                .build()
                .getResult(template);
    }

    /**
     * 查询不按照日期聚合的新增用户TOP10版本分布
     * @param condition 查询条件
     * @return 不按日期聚合的新增用户版本分布列表
     */
    @Override
    public List<Distribution> getTopVersionDataLInstallation(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_TOP_VERSION)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_VERSION)
                .build()
                .getResult(template);
    }

    /**
     * 查询不按照日期聚合的活跃用户版本分布
     * @param condition 查询条件
     * @return 不按日期聚合的活跃用户版本分布列表
     */
    @Override
    public List<Distribution> getTopVersionDataActive(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_TOP_VERSION)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_VERSION)
                .build()
                .getResult(template);
    }

    /**
     * 查询不按照日期聚合的启动次数TOP10版本分布
     * @param condition 查询条件
     * @return 不按日期聚合的启动次数版本分布列表
     */
    @Override
    public List<Distribution> getTopVersionDataLaunch(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_LAUNCH_TOP_VERSION)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_VERSION)
                .build()
                .getResult(template);
    }

    /**
     * 查询不按照日期聚合的新增用户TOP10渠道分布
     * @param condition 查询条件
     * @return 不按日期聚合的新增用户渠道分布列表
     */
    @Override
    public List<Distribution> getTopChannelDataLInstallation(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_TOP_CHANNEL)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_CHANNEL)
                .sql_order(UserAnalyseTemplate.ORDER_BY_CHANNEL)
                .build()
                .getResult(template);
    }

    /**
     * 查询不按照日期聚合的活跃用户TOP10渠道分布
     * @param condition 查询条件
     * @return 不按日期聚合的活跃用户渠道分布列表
     */
    @Override
    public List<Distribution> getTopChannelDataActive(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_TOP_CHANNEL)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_CHANNEL)
                .sql_order(UserAnalyseTemplate.ORDER_BY_CHANNEL)
                .build()
                .getResult(template);
    }

    /**
     * 查询不按照日期聚合的启动次数TOP10渠道分布
     * @param condition 查询条件
     * @return 不按日期聚合的启动次数渠道分布列表
     */
    @Override
    public List<Distribution> getTopChannelDataLaunch(Condition condition) {
        return SQLResultBuilder.newInstance(Distribution.class)
                .sql_select(UserAnalyseTemplate.QUERY_LAUNCH_TOP_CHANNEL)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_CHANNEL)
                .sql_order(UserAnalyseTemplate.ORDER_BY_CHANNEL)
                .build()
                .getResult(template);
    }

    /**
     * 查询所有版本的名称
     * @return 版本名称列表
     */
    @Override
    public List<String> getAllVersionName() {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(UserAnalyseTemplate.QUERY_VERSION_NAME)
                .sql_order(UserAnalyseTemplate.ORDER_BY_ID)
                .build()
                .getResult(template);
    }

    /**
     * 返回所有版本的新增用户分布
     * @param condition 传入的条件
     * @return 版本分布列表
     */
    @Override
    public List<Integer> getAllVersionDataInstallation(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_INSTALLATION_DAY)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_VERSION)
                .build()
                .getResult(template);
    }

    /**
     * 返回所有版本的活跃用户分布
     * @param condition 传入的条件
     * @return 版本分布列表
     */
    @Override
    public List<Integer> getAllVersionDataActive(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_ACTIVE_USER_DAY)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_VERSION)
                .build()
                .getResult(template);
    }

    /**
     * 返回所有版本的启动次数用户分布
     * @param condition 传入的条件
     * @return 版本分布列表
     */
    @Override
    public List<Integer> getAllVersionDataLaunch(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.QUERY_LAUNCH_DAY)
                .condition(condition)
                .sql_group(UserAnalyseTemplate.GROUP_BY_VERSION)
                .sql_order(UserAnalyseTemplate.ORDER_BY_VERSION)
                .build()
                .getResult(template);
    }
}
