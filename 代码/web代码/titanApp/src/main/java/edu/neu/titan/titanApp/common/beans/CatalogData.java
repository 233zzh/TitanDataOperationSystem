package edu.neu.titan.titanApp.common.beans;

import edu.neu.titan.titanApp.common.sql.IRowMapped;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/18
 * @Time: 12:16
 * @Version: 1.0
 * @Description: 封装分类查询(渠道、版本、终端属性等)结果的类
 */
public class CatalogData implements IRowMapped<Object>  {

    private static final int FIELD_NUM = 3;

    // 分类名
    protected String name ;
    // 新增用户
    protected Integer increase_num;
    protected Integer start_num;

    // 无参构造器
    public CatalogData() {}

    // get/set 方法

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIncrease_num() {
        return increase_num;
    }

    public void setIncrease_num(Integer increase_num) {
        this.increase_num = increase_num;
    }

    public Integer getStart_num() {
        return start_num;
    }

    public void setStart_num(Integer start_num) {
        this.start_num = start_num;
    }

    @Override
    public Object[] getRowData() {
        // 新建数组
        Object[] rowData = new Object[FIELD_NUM];
        rowData[0] = name;
        rowData[1] = increase_num;
        rowData[2] = start_num;
        return rowData;
    }

    @Override
    public int getFieldNum() {
        return FIELD_NUM;
    }
}
