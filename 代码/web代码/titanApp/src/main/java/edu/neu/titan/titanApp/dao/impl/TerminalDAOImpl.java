package edu.neu.titan.titanApp.dao.impl;

import edu.neu.titan.titanApp.common.beans.CatalogData;
import edu.neu.titan.titanApp.common.beans.CatalogDataActive;
import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.dao.ITerminalDAO;
import edu.neu.titan.titanApp.dao.sql.CommonTemplate;
import edu.neu.titan.titanApp.dao.sql.TerminalTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/18
 * @Time: 17:57
 * @Version: 1.0
 * @Description: ITerminalDAO 实现类
 */
@Repository
public class TerminalDAOImpl implements ITerminalDAO {

    // 所需的jdbcTemplate字段
    NamedParameterJdbcTemplate template;

    @Autowired
    public TerminalDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<CatalogData> getModelData(Condition condition) {
        return SQLResultBuilder.newInstance(CatalogData.class)
                .sql_select(TerminalTemplate.QUERY_MODEL)
                .condition(condition)
                .sql_group(TerminalTemplate.GROUP_BY_ID)
                .sql_order(TerminalTemplate.ORDER_BY_INCREASE)
                .order_option(CommonTemplate.DESC)
                .build().getResult(template);
    }

    @Override
    public List<CatalogData> getResolutionData(Condition condition) {
        return SQLResultBuilder.newInstance(CatalogData.class)
                .sql_select(TerminalTemplate.QUERY_RESOLUTION)
                .condition(condition)
                .sql_group(TerminalTemplate.GROUP_BY_ID)
                .sql_order(TerminalTemplate.ORDER_BY_INCREASE)
                .order_option(CommonTemplate.DESC)
                .build().getResult(template);
    }

    @Override
    public List<CatalogData> getOSData(Condition condition) {
        return SQLResultBuilder.newInstance(CatalogData.class)
                .sql_select(TerminalTemplate.QUERY_OS)
                .condition(condition)
                .sql_group(TerminalTemplate.GROUP_BY_ID)
                .sql_order(TerminalTemplate.ORDER_BY_INCREASE)
                .order_option(CommonTemplate.DESC)
                .build().getResult(template);
    }

    @Override
    public List<CatalogData> getNetWorkData(Condition condition) {
        return SQLResultBuilder.newInstance(CatalogData.class)
                .sql_select(TerminalTemplate.QUERY_NETWORK)
                .condition(condition)
                .sql_group(TerminalTemplate.GROUP_BY_ID)
                .sql_order(TerminalTemplate.ORDER_BY_INCREASE)
                .order_option(CommonTemplate.DESC)
                .build().getResult(template);    }

    @Override
    public List<CatalogDataActive> getProvinceData(Condition condition) {
        return SQLResultBuilder.newInstance(CatalogDataActive.class)
                .sql_select(TerminalTemplate.QUERY_REGION)
                .condition(condition)
                .sql_group(TerminalTemplate.GROUP_BY_ID)
                .sql_order(TerminalTemplate.ORDER_BY_INCREASE)
                .order_option(CommonTemplate.DESC)
                .build().getResult(template);
    }
}
