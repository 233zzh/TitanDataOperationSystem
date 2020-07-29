package edu.neu.titan.titanApp.service;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.utils.DateUtils;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/19
 * @Time: 13:53
 * @Version: 1.0
 * @Description: 提供日期转化服务的接口
 */
public interface IDateService {

    /**
     * 将条件的日期范围转换为日期字符串数组
     * @param condition 封装条件的Condition类实例
     * @return 日期字符串数据
     */
    public String[] getDays(Condition condition) ;

    /**
     * 将日期范围转换为周范围字符串数组
     * @param condition 封装条件的Condition类实例
     * @return 日期字符串数据
     */
    public String[] getWeeks(Condition condition);

    /**
     * 将日期范围转换为月范围字符串数组
     * @param condition 封装条件的Condition类实例
     * @return 日期字符串数据
     */
    public String[] getMonths(Condition condition);

}
