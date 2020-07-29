package edu.neu.titan.titanApp.dao.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.dao.IParticipationDAO;
import edu.neu.titan.titanApp.dao.sql.ChannelTemplate;
import edu.neu.titan.titanApp.dao.sql.ParticipationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 17:23
 * @Version: 1.0
 * @Description: Description
 */
@Repository
public class ParticipationDAOImpl implements IParticipationDAO {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public ParticipationDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * 获得用户参与度模块中使用时长子功能中的单次使用时长分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getDurationDataSingle(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ParticipationTemplate.QUERY_DURATION_SINGLE)
                .sql_group(ParticipationTemplate.GROUP_BY_RANGE_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中使用时长子功能中的日使用时长分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getDurationDataDay(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ParticipationTemplate.QUERY_DURATION_DAY)
                .sql_group(ParticipationTemplate.GROUP_BY_RANGE_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的日启动次数分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getFrequencyDataDay(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ParticipationTemplate.QUERY_FREQUENCY_DAY)
                .sql_group(ParticipationTemplate.GROUP_BY_RANGE_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的周启动次数分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getFrequencyDataWeek(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ParticipationTemplate.QUERY_FREQUENCY_WEEK)
                .sql_group(ParticipationTemplate.GROUP_BY_RANGE_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的月启动次数分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getFrequencyDataMonth(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ParticipationTemplate.QUERY_FREQUENCY_MONTH)
                .sql_group(ParticipationTemplate.GROUP_BY_RANGE_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中访问页面子功能中的访问页面分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getPageData(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ParticipationTemplate.QUERY_PAGE)
                .sql_group(ParticipationTemplate.GROUP_BY_RANGE_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中使用间隔子功能中的使用间隔分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getIntervalData(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ParticipationTemplate.QUERY_INTERVAL)
                .sql_group(ParticipationTemplate.GROUP_BY_RANGE_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }


    /**
     * 获得用户参与度模块中使用时长子功能中的使用时长范围
     *
     * @return
     */
    @Override
    public List<String> getDurationRange() {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ParticipationTemplate.QUERY_DURATION_RANGE)
                .sql_group(ParticipationTemplate.GROUP_BY_ID)
                .build()
                .getResult(template);
    }


    /**
     * 获得用户参与度模块中使用频率子功能中的日启动次数范围
     *
     * @return
     */
    @Override
    public List<String> getFrequencyDayRange() {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ParticipationTemplate.QUERY_FREQUENCY_DAY_RANGE)
                .sql_group(ParticipationTemplate.GROUP_BY_ID)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的周启动次数分布
     *
     * @return
     */
    @Override
    public List<String> getFrequencyWeekRange() {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ParticipationTemplate.QUERY_FREQUENCY_WEEK_RANGE)
                .sql_group(ParticipationTemplate.GROUP_BY_ID)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的月启动次数分布
     *
     * @return
     */
    @Override
    public List<String> getFrequencyMonthRange() {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ParticipationTemplate.QUERY_FREQUENCY_MONTH_RANGE)
                .sql_group(ParticipationTemplate.GROUP_BY_ID)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中访问页面子功能中的访问页面范围
     *
     * @return
     */
    @Override
    public List<String> getPageRange() {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ParticipationTemplate.QUERY_PAGE_RANGE)
                .sql_group(ParticipationTemplate.GROUP_BY_ID)
                .build()
                .getResult(template);
    }

    /**
     * 获得用户参与度模块中使用间隔子功能中的使用间隔范围
     *
     * @return
     */
    @Override
    public List<String> getIntervalRange() {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ParticipationTemplate.QUERY_INTERVAL_RANGE)
                .sql_group(ParticipationTemplate.GROUP_BY_ID)
                .build()
                .getResult(template);
    }
}
