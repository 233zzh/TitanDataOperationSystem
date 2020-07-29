package edu.neu.titan.titanApp.dao.impl;

import edu.neu.titan.titanApp.common.beans.*;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.common.utils.SetConditionUtils;
import edu.neu.titan.titanApp.dao.IRetentionDAO;
import edu.neu.titan.titanApp.dao.sql.RetentionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: IRetentionDAO 接口实现类，用于访问存留分析相关数据库
 */
@Repository
public class RetentionDAOImpl implements IRetentionDAO {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public RetentionDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<RetentionDataDay> getRetentionDataIncreaseDay(Condition condition) {

        return SQLResultBuilder.newInstance(RetentionDataDay.class)
                .sql_select(RetentionTemplate.QUERY_INCREASE_RETENTION_DAY)
                .sql_group(RetentionTemplate.GROUP_BY_DATE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    @Override
    public List<RetentionDataWeek> getRetentionDataIncreaseWeek(Condition condition) {

        return SQLResultBuilder.newInstance(RetentionDataWeek.class)
                .sql_select(RetentionTemplate.QUERY_INCREASE_RETENTION_WEEK)
                .sql_group(RetentionTemplate.GROUP_BY_DATE)
                .condition(SetConditionUtils.weekCondition(condition))
                .build()
                .getResult(template);
    }

    @Override
    public List<RetentionDataMonth> getRetentionDataIncreaseMonth(Condition condition) {

        return SQLResultBuilder.newInstance(RetentionDataMonth.class)
                .sql_select(RetentionTemplate.QUERY_INCREASE_RETENTION_MONTH)
                .sql_group(RetentionTemplate.GROUP_BY_DATE)
                .condition(SetConditionUtils.monthCondition(condition))
                .build()
                .getResult(template);
    }

    @Override
    public List<RetentionDataDay> getRetentionDataActiveDay(Condition condition) {

        return SQLResultBuilder.newInstance(RetentionDataDay.class)
                .sql_select(RetentionTemplate.QUERY_ACTIVE_RETENTION_DAY)
                .sql_group(RetentionTemplate.GROUP_BY_DATE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    @Override
    public List<RetentionDataWeek> getRetentionDataActiveWeek(Condition condition) {

        return SQLResultBuilder.newInstance(RetentionDataWeek.class)
                .sql_select(RetentionTemplate.QUERY_ACTIVE_RETENTION_WEEK)
                .sql_group(RetentionTemplate.GROUP_BY_DATE)
                .condition(SetConditionUtils.weekCondition(condition))
                .build()
                .getResult(template);
    }

    @Override
    public List<RetentionDataMonth> getRetentionDataActiveMonth(Condition condition) {
        return SQLResultBuilder.newInstance(RetentionDataMonth.class)
                .sql_select(RetentionTemplate.QUERY_ACTIVE_RETENTION_MONTH)
                .sql_group(RetentionTemplate.GROUP_BY_DATE)
                .condition(SetConditionUtils.monthCondition(condition))
                .build()
                .getResult(template);
    }

    @Override
    public List<RetentionActivityData> getActivityData(Condition condition) {
        return SQLResultBuilder.newInstance(RetentionActivityData.class)
                .sql_select(RetentionTemplate.QUERY_ACTIVITY_RETENTION)
                .condition(condition)
                .build()
                .getResult(template);
    }

    @Override
    public List<Double> getOneDayAfterRetentionRate(Condition condition) {
        return SQLResultBuilder.newInstance(Double.class)
                .sql_select(RetentionTemplate.QUERY_INCREASE_ONE_DAY_AFTER_RETENTION)
                .condition(condition)
                .sql_group(RetentionTemplate.GROUP_BY_DATE)
                .build()
                .getResult(template);
    }

    @Override
    public double getAvgRetentionRate(Condition condition) {
        return SQLResultBuilder.newInstance(Double.class)
                .sql_select(RetentionTemplate.QUERY_AVG_RETENTION_RATE)
                .condition(condition)
                .build()
                .getResultSingle(template);
    }
}
