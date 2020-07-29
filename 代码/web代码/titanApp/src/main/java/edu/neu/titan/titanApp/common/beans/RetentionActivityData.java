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
 * @Description: 用于接收用户活跃度查询结果
 */
public class RetentionActivityData implements IRowMapped<Integer> {

    // 列数
    public final static int FIELD_NUM = 8;
    
    // 与查询结果列同名的字段
    private Integer day1_num;
    private Integer day2_num;
    private Integer day3_num;
    private Integer day4_num;
    private Integer day5_num;
    private Integer day6_num;
    private Integer day7_num;
    private Integer day7p_num;

    // 无参构造器
    public RetentionActivityData() {  }

    // get、set方法


    public Integer getDay1_num() {
        return day1_num;
    }

    public void setDay1_num(Integer day1_num) {
        this.day1_num = day1_num;
    }

    public Integer getDay2_num() {
        return day2_num;
    }

    public void setDay2_num(Integer day2_num) {
        this.day2_num = day2_num;
    }

    public Integer getDay3_num() {
        return day3_num;
    }

    public void setDay3_num(Integer day3_num) {
        this.day3_num = day3_num;
    }

    public Integer getDay4_num() {
        return day4_num;
    }

    public void setDay4_num(Integer day4_num) {
        this.day4_num = day4_num;
    }

    public Integer getDay5_num() {
        return day5_num;
    }

    public void setDay5_num(Integer day5_num) {
        this.day5_num = day5_num;
    }

    public Integer getDay6_num() {
        return day6_num;
    }

    public void setDay6_num(Integer day6_num) {
        this.day6_num = day6_num;
    }

    public Integer getDay7_num() {
        return day7_num;
    }

    public void setDay7_num(Integer day7_num) {
        this.day7_num = day7_num;
    }

    public Integer getDay7p_num() {
        return day7p_num;
    }

    public void setDay7p_num(Integer day7_num) {
        this.day7p_num = day7_num;
    }

    @Override
    public Integer[] getRowData() {
        // 新建数组
        Integer[] rowData = new Integer[FIELD_NUM];
        // 装入数据
        rowData[0] = day1_num;
        rowData[1] = day2_num;
        rowData[2] = day3_num;
        rowData[3] = day4_num;
        rowData[4] = day5_num;
        rowData[5] = day6_num;
        rowData[6] = day7_num;
        rowData[7] = day7p_num;
        return rowData;
    }

    @Override
    public int getFieldNum() {
        return FIELD_NUM;
    }
}
