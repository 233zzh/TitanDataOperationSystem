package edu.neu.titan.titanApp.common.beans;

import edu.neu.titan.titanApp.common.sql.IRowMapped;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 用于接收周留存数查询结果的类
 */
public class RetentionDataWeek implements IRowMapped<Integer> {

    // 列数
    public final static int FIELD_NUM = 9;

    // 与查询结果列同名的字段
    private Integer week1_num;
    private Integer week2_num;
    private Integer week3_num;
    private Integer week4_num;
    private Integer week5_num;
    private Integer week6_num;
    private Integer week7_num;
    private Integer week8_num;
    private Integer week9_num;

    // 无参构造器
    public RetentionDataWeek() {}

    public Integer getWeek1_num() {
        return week1_num;
    }

    public void setWeek1_num(Integer week1_num) {
        this.week1_num = week1_num;
    }

    public Integer getWeek2_num() {
        return week2_num;
    }

    public void setWeek2_num(Integer week2_num) {
        this.week2_num = week2_num;
    }

    public Integer getWeek3_num() {
        return week3_num;
    }

    public void setWeek3_num(Integer week3_num) {
        this.week3_num = week3_num;
    }

    public Integer getWeek4_num() {
        return week4_num;
    }

    public void setWeek4_num(Integer week4_num) {
        this.week4_num = week4_num;
    }

    public Integer getWeek5_num() {
        return week5_num;
    }

    public void setWeek5_num(Integer week5_num) {
        this.week5_num = week5_num;
    }

    public Integer getWeek6_num() {
        return week6_num;
    }

    public void setWeek6_num(Integer week6_num) {
        this.week6_num = week6_num;
    }

    public Integer getWeek7_num() {
        return week7_num;
    }

    public void setWeek7_num(Integer week7_num) {
        this.week7_num = week7_num;
    }

    public Integer getWeek8_num() {
        return week8_num;
    }

    public void setWeek8_num(Integer week8_num) {
        this.week8_num = week8_num;
    }

    public Integer getWeek9_num() {
        return week9_num;
    }

    public void setWeek9_num(Integer week9_num) {
        this.week9_num = week9_num;
    }

    @Override
    public Integer[] getRowData() {
        Integer[] rowData = new Integer[FIELD_NUM];
        // 装入数据
        rowData[0] = week1_num;
        rowData[1] = week2_num;
        rowData[2] = week3_num;
        rowData[3] = week4_num;
        rowData[4] = week5_num;
        rowData[5] = week6_num;
        rowData[6] = week7_num;
        rowData[7] = week8_num;
        rowData[8] = week9_num;
        return rowData;
    }

    @Override
    public int getFieldNum() {
        return FIELD_NUM;
    }
}
