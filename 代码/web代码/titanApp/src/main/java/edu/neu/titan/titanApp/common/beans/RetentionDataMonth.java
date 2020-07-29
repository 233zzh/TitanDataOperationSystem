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
 * @Description: 用于接收月留存数查询结果的类
 */
public class RetentionDataMonth implements IRowMapped<Integer> {

    // 列数
    public final static int FIELD_NUM = 9;

    // 与查询结果列同名的字段
    private Integer month1_num;
    private Integer month2_num;
    private Integer month3_num;
    private Integer month4_num;
    private Integer month5_num;
    private Integer month6_num;
    private Integer month7_num;
    private Integer month8_num;
    private Integer month9_num;

    // 无参构造器
    public RetentionDataMonth() {}

    public Integer getMonth1_num() {
        return month1_num;
    }

    public void setMonth1_num(Integer month1_num) {
        this.month1_num = month1_num;
    }

    public Integer getMonth2_num() {
        return month2_num;
    }

    public void setMonth2_num(Integer month2_num) {
        this.month2_num = month2_num;
    }

    public Integer getMonth3_num() {
        return month3_num;
    }

    public void setMonth3_num(Integer month3_num) {
        this.month3_num = month3_num;
    }

    public Integer getMonth4_num() {
        return month4_num;
    }

    public void setMonth4_num(Integer month4_num) {
        this.month4_num = month4_num;
    }

    public Integer getMonth5_num() {
        return month5_num;
    }

    public void setMonth5_num(Integer month5_num) {
        this.month5_num = month5_num;
    }

    public Integer getMonth6_num() {
        return month6_num;
    }

    public void setMonth6_num(Integer month6_num) {
        this.month6_num = month6_num;
    }

    public Integer getMonth7_num() {
        return month7_num;
    }

    public void setMonth7_num(Integer month7_num) {
        this.month7_num = month7_num;
    }

    public Integer getMonth8_num() {
        return month8_num;
    }

    public void setMonth8_num(Integer month8_num) {
        this.month8_num = month8_num;
    }

    public Integer getMonth9_num() {
        return month9_num;
    }

    public void setMonth9_num(Integer month9_num) {
        this.month9_num = month9_num;
    }


    @Override
    public Integer[] getRowData() {
        Integer[] rowData = new Integer[FIELD_NUM];
        // 装入数据
        rowData[0] = month1_num;
        rowData[1] = month2_num;
        rowData[2] = month3_num;
        rowData[3] = month4_num;
        rowData[4] = month5_num;
        rowData[5] = month6_num;
        rowData[6] = month7_num;
        rowData[7] = month8_num;
        rowData[8] = month9_num;
        return rowData;
    }

    @Override
    public int getFieldNum() {
        return FIELD_NUM;
    }
}
