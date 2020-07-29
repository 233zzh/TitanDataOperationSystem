package edu.neu.titan.titanApp.common.beans;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/18
 * @Time: 16:51
 * @Version: 1.0
 * @Description: CatalogData 的子类，增加了一列
 */
public class CatalogDataActive extends CatalogData {

    // 所需转化的字段数
    private static final int FIELD_NUM = 4;

    // 活跃数
    private Integer active_num;

    // 无参构造器
    public CatalogDataActive() { }

    // get/set 方法
    public Integer getActive_num() {
        return active_num;
    }

    public void setActive_num(Integer active_num) {
        this.active_num = active_num;
    }

    @Override
    public Object[] getRowData() {
        // 新建数组
        Object[] rowData = new Object[FIELD_NUM];
        rowData[0] = name;
        rowData[1] = increase_num;
        rowData[2] = start_num;
        rowData[3] = active_num;
        return rowData;
    }

    @Override
    public int getFieldNum() {
        return FIELD_NUM;
    }
}
