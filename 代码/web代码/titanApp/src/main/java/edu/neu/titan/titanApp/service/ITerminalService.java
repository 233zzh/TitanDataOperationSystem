package edu.neu.titan.titanApp.service;

import edu.neu.titan.titanApp.common.beans.Condition;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/18
 * @Time: 18:03
 * @Version: 1.0
 * @Description: 用于处理终端属性业务的service接口
 */
public interface ITerminalService {

    /**
     * 从数据库中按条件查询各种机型的新增用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    Object[][] getModelData(Condition condition);

    /**
     * 从数据库中按条件查询各种分辨率的新增用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    Object[][] getResolutionData(Condition condition);

    /**
     * 从数据库中按条件查询各种操作系统的新增用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    Object[][] getOSData(Condition condition);

    /**
     * 从数据库中按条件查询各种联网方式的新增用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    Object[][] getNetWorkData(Condition condition);

    /**
     * 从数据库中按条件查询各个省份的新增用户数、活跃用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    Object[][] getProvinceData(Condition condition);
}
