package edu.neu.titan.titanApp.common.beans;

import edu.neu.titan.titanApp.common.sql.IRowMapped;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/22
 * @Time: 14:00
 * @Version: 1.0
 * @Description: 版本分布或者渠道分布的类，用来保存名称和数量
 */
public class Distribution implements IRowMapped<Object> {
    private String name;
    private int value;


    public Distribution(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Distribution{" +
                "name='" + name + '\'' +
                ", num=" + value +
                '}';
    }

    @Override
    public Object[] getRowData() {
        return new Object[0];
    }

    @Override
    public int getFieldNum() {
        return 0;
    }
}
