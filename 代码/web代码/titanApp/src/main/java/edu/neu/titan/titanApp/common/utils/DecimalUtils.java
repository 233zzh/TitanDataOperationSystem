package edu.neu.titan.titanApp.common.utils;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/18
 * @Time: 21:29
 * @Version: 1.0
 * @Description: 把数字处理成需要的格式
 */
public class DecimalUtils {

    /**
     * 把Double类型的数字处理成百分数字符串
     * @param num 待处理的数字
     * @return 处理后的百分数
     */
    public static String doubleToPercent(double num) {
        return String.format("%.2f%%", 100 * num);
    }

    /**
     * 截取 Double 的四位小数
     * @param num 待处理的数字
     * @return 处理之后的数字
     */
    public static Double doubleDigits(double num) {
        return Double.parseDouble(String.format("%.4f", num));
    }
}
