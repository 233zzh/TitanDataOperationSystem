package edu.neu.titan.titanApp.common.sql;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 接收一行所有列都为共同一种类型的sql查询结果的类的通用接口
 */
public interface IRowMapped<T> {

    /**
     * 得到将接收到的一行的数据转化为数组
     * @return 转化的结果
     */
    T[] getRowData() ;

    /**
     * 得到接收列的数目
     * @return 列数
     */
    int getFieldNum() ;
}
