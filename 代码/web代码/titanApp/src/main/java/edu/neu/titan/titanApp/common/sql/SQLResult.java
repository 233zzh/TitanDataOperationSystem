package edu.neu.titan.titanApp.common.sql;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.constant.Constants;
import edu.neu.titan.titanApp.dao.sql.CommonTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 用于返回sql查询结果的类
 * @param <T> 用于接收查询结果的类
 */
public class SQLResult<T> {

    // 记录类型
    private Class<T> tClass;
    // sql 查询语句
    private String sql_select ;
    // sql 分组语句
    private String sql_group ;
    // sql 排序语句
    private String sql_order ;
    // 升序或降序
    private String order_option ;
    // 查询条件实例
    private Condition condition;


    // 构造器
    public SQLResult(Class<T> tClass, String sql_select, String sql_group, String sql_order, String order_option, Condition condition) {
        this.tClass = tClass;
        this.sql_select = sql_select;
        this.sql_group = sql_group;
        this.sql_order = sql_order;
        this.order_option = order_option;
        this.condition = condition;
    }

    /**
     * 将sql中占位符的实际参数加入至source，并返回需要增加的条件sql语句片段
     * @param source 保存占位符名和实际值的source
     * @return 需要增加的条件sql语句片段
     */
    private String addCondition(MapSqlParameterSource source) {
        if (condition==null) {
            return "";
        }
        // 加入占位符实际值
        source.addValue("start_date", condition.getStart_date());
        source.addValue("end_date", condition.getEnd_date());
        StringBuilder sb = new StringBuilder();
        if (condition.getChannel_id()!= Constants.CONDITION_INVALID_VALUE) {  // 是否需要判断渠道
            // 拼接sql语句
            sb.append(CommonTemplate.CHANNEL_CONDITION);
            source.addValue("channel_id", condition.getChannel_id());
        }
        if (condition.getVersion_id()!=Constants.CONDITION_INVALID_VALUE) {   // 是否需要判断版本
            // 拼接sql语句
            sb.append(CommonTemplate.VERSION_CONDITION);
            source.addValue("version_id", condition.getVersion_id());
        }
        return sb.toString();
    }

    /**
     * 产生需要执行的sql语句并将参数添加至传入的Map中
     * @param source 传入的用于储存参数的map
     * @return 产生的sql语句
     */
    private String generateSql(MapSqlParameterSource source) {
        return sql_select +    // select 语句
                addCondition(source) +  // 添加筛选条件并映射
                sql_group+          // group by 语句
                sql_order+          // order by 语句
                order_option;
    }

    /**
     * 返回sql查询结果（多行列表）
     * @param template 需要执行sql的的jdbcTemplate
     * @return 查询结果
     */
    public List<T> getResult(NamedParameterJdbcTemplate template) {
        // 判断字段初始化状态
        if (this.sql_select==null) {
            return null;
        }
        // 占位符映射map
        MapSqlParameterSource source = new MapSqlParameterSource();

        // 产生sql语句
        String sql = generateSql(source);

        // 根据是否为封装类执行不同的查询方法
        if (isPrimitive()) return template.queryForList(sql, source, tClass);
        // 类、查询结果的映射mapper
        RowMapper<T> mapper = BeanPropertyRowMapper.newInstance(tClass);
        // 返回结果
        return template.query(sql, source, mapper);
    }

    /**返回sql查询结果（单行对象）
     * @param template 需要执行sql的的jdbcTemplate
     * @return 查询结果
     */
    public T getResultSingle(NamedParameterJdbcTemplate template) {
        // 判断字段初始化状态
        if (this.sql_select==null) {
            return null;
        }
        // 占位符映射map
        MapSqlParameterSource source = new MapSqlParameterSource();

        // 产生sql语句
        String sql = generateSql(source);

        // 根据是否为封装类执行不同的查询方法
        if (isPrimitive()) return template.queryForObject(sql, source, tClass);
        // 类、查询结果的映射mapper
        RowMapper<T> mapper = BeanPropertyRowMapper.newInstance(tClass);
        // 返回结果
        return template.queryForObject(sql, source, mapper);
    }

    /**
     * 判断传入类型是基本类型的封装类
     * @return 是否为封装类
     */
    private boolean isPrimitive() {
        try {
            return tClass==String.class||((Class<?>) tClass.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
