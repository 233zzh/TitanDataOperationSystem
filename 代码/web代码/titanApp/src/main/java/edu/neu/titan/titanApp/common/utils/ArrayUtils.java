package edu.neu.titan.titanApp.common.utils;

import edu.neu.titan.titanApp.common.sql.IRowMapped;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 用于处理数组的工具类
 */
public class ArrayUtils {

    /**
     * 将二维数组的行列互换
     * @param list 原始的二维数组
     * @param tClass 数组元素类型
     * @param <T> 泛型占位符
     * @return 转化后的数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][] transpose(Class<T> tClass, T[][] list) {

        // 判断是否为空
        if (list==null||list.length==0||list[0].length==0) {
            return null;
        }
        int l1 = list.length;
        int l2 = list[0].length;

        T[][] result = (T[][]) Array.newInstance(tClass, l2, l1);

        for (int i = 0; i < l2; i++) {
            for (int j = 0; j < l1; j++) {
                result[i][j] = list[j][i];
            }
        }
        return result;
    }

    /**
     * 将接收类实例集合的字段转化为二维数组
     * @param tClass 类的字段类型
     * @param list 实例集合
     * @param <T>   字段泛型
     * @param <E>   类泛型，抽象类IRowMapped 子类
     * @return 转化后的二维数组
     */
    @SuppressWarnings("unchecked")
    public static <T,E extends IRowMapped<T>> T[][] transform(Class<T> tClass, List<E> list) {
        if (list==null||list.size()==0) return null;
        T[][] result = (T[][]) Array.newInstance(tClass, list.size(),list.get(0).getFieldNum());
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getRowData();
        }
        return result;
    }

    /**
     * 将接收类实例集合的字段转化为二维数组并转置
     * @param tClass 类的字段类型
     * @param list 实例集合
     * @param <T>   字段泛型
     * @param <E>   类泛型，抽象类IRowMapped 子类
     * @return 转化后的二维数组
     */
    public static <T,E extends IRowMapped<T>> T[][] transformAndTranspose(Class<T> tClass, List<E> list) {
        return transpose(tClass, transform(tClass, list));
    }


    /**
     * 错位堆叠，将一维数组转化为二维数组
     * @param list 原一维数组
     * @param length 指定转化为二维数组的中第二维度的长度
     * @param distance 错位距离
     * @param <T> 数组类型
     * @return 结果数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][] stackShift(Class<T> tClass, T[] list, int length, int distance) {
        int size = list.length;
        // 判断是否可以转化
        if ( size<=length||length<=0||distance<=0 ) return null;
        if ( (size-length)%distance!=0 ) return null;
        // 计算结果数组中的第一维度
        int m = (size-length)/distance + 1;
        // 声明数组
        T[][] result = (T[][]) Array.newInstance(tClass, m, length);
        for (int i = 0; i < m; i++) {
            System.arraycopy(list, i * distance, result[i], 0, length);
        }
        return result;
    }

    /**
     * 一维数组求和
     * @param list 输入数组
     * @return 和
     */
    public static Integer sum(Integer[] list) {
        return Arrays.stream(list).mapToInt(Integer::valueOf).sum();
    }

    /**
     * 二维数组按第二个维度求和
     * @param list 输入数组
     * @return 和数组
     */
    public static Integer[] sumByRow(Integer[][] list) {
        return Arrays.stream(list).map(ArrayUtils::sum).toArray(Integer[]::new);
    }

    /**
     * 一维数组对应位置元素相减
     * @param left 被减数组
     * @param right 需要减掉的数组
     * @return 结果数组
     */
    public static Integer[] minus(Integer[] left, Integer[] right) {
        // 判断是否长度一致
        if (left.length!=right.length) return null;
        // 相减
        Integer[] result = (Integer[]) Array.newInstance(Integer.class, left.length);
        for (int i = 0; i < left.length; i++) {
            result[i] = left[i]-right[i];
        }
        return result;
    }


    /**
     * 将一维数组添置二维数组尾部
     * @param tClass 类型
     * @param origin 原二维数组
     * @param row 需要添加的一维数组
     * @param <T> 泛型
     * @return 结果数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][] stackByRow(Class<T> tClass,T[][] origin, T[] row) {
        // 判断长度一致
        int m = origin.length;
        if (m!=0&&origin[0].length!=row.length) return null;
        // 新建数组
        T[][] result = (T[][]) Array.newInstance(tClass, m + 1, row.length);
        System.arraycopy(origin, 0, result, 0, m);
        result[m] = row;
        return result;
    }
}
