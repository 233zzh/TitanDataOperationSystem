package edu.neu.titan.titanApp.dao.impl;

import edu.neu.titan.titanApp.common.beans.IdName;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.dao.ICommonDAO;
import edu.neu.titan.titanApp.dao.sql.CommonTemplate;
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
 * @Description: ICommonDAO 接口的实现类，用于数据库中公共常量访问。
 */
@Repository
public class CommonDAOImpl implements ICommonDAO {

    // jdbcTemplate 字段
    private NamedParameterJdbcTemplate template;

    @Autowired
    public CommonDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<IdName> getChannels() {
        return SQLResultBuilder.newInstance(IdName.class)
                .sql_select(CommonTemplate.QUERY_ALL_CHANNEL)
                .build()
                .getResult(template);
    }

    @Override
    public List<IdName> getVersions() {
        return SQLResultBuilder.newInstance(IdName.class)
                .sql_select(CommonTemplate.QUERY_ALL_VERSION)
                .build()
                .getResult(template);
    }
}
