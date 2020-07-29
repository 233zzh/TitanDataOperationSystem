package edu.neu.titan.titanApp.common.sql;

import edu.neu.titan.titanApp.common.beans.Condition;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 以工厂形式构造SQLResult实例的构建类
 */
public class SQLResultBuilder<T> {

    // 记录类型
    private Class<T> tClass;
    // sql 查询语句
    private String sql_select = null;
    // sql 分组语句，默认值为空字符串
    private String sql_group = "";
    // sql 排序语句，默认值为空字符串
    private String sql_order = "";
    // 升序或降序，默认值为空字符串
    private String order_option = "";
    // 查询条件实例, 默认值为空
    private Condition condition = null;

    // 构造方法
    private SQLResultBuilder(Class<T> tClass) {
        this.tClass = tClass;
    }

    /**
     * 修改产生SQLResult字段的值
     * @param sql_select 需要设置的值
     * @return 本实例
     */
    public SQLResultBuilder<T> sql_select(String sql_select) {
        this.sql_select = sql_select;
        return this;
    }

    /**
     * 修改产生SQLResult字段的值
     * @param sql_group 需要设置的值
     * @return 本实例
     */
    public SQLResultBuilder<T> sql_group(String sql_group) {
        this.sql_group = sql_group;
        return this;
    }

    /**
     * 修改产生SQLResult字段的值
     * @param sql_order 需要设置的值
     * @return 本实例
     */
    public SQLResultBuilder<T> sql_order(String sql_order) {
        this.sql_order = sql_order;
        return this;
    }

    /**
     * 修改产生SQLResult字段的值
     * @param order_option 需要设置的值
     * @return 本实例
     */
    public SQLResultBuilder<T> order_option(String order_option) {
        this.order_option = order_option;
        return this;
    }

    /**
     * 修改产生SQLResult字段的值
     * @param condition 需要设置的值
     * @return 本实例
     */
    public SQLResultBuilder<T> condition(Condition condition) {
        this.condition = condition;
        return this;
    }



    /**
     * 构建SQLResult实例
     * @return SQLResult实例
     */
    public SQLResult<T> build() {
        return new SQLResult<>(tClass, sql_select, sql_group, sql_order, order_option, condition);
    }

    /**
     * 产生新的实例的静态工厂方法
     * @param tClass 指定类型
     * @param <T> 泛型
     * @return 新的SQLResult实例
     */
    public static <T> SQLResultBuilder<T> newInstance(Class<T> tClass) {
        return new SQLResultBuilder<>(tClass);
    }
}
