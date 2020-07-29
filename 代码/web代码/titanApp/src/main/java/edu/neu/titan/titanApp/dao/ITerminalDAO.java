package edu.neu.titan.titanApp.dao;

import edu.neu.titan.titanApp.common.beans.CatalogData;
import edu.neu.titan.titanApp.common.beans.CatalogDataActive;
import edu.neu.titan.titanApp.common.beans.Condition;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/18
 * @Time: 17:50
 * @Version: 1.0
 * @Description: 访问终端属性功能相关数据库表的接口
 */
public interface ITerminalDAO {

    /**
     * 从数据库中按条件查询各种机型的新增用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<CatalogData> getModelData(Condition condition);

    /**
     * 从数据库中按条件查询各种分辨率的新增用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<CatalogData> getResolutionData(Condition condition);

    /**
     * 从数据库中按条件查询各种操作系统的新增用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<CatalogData> getOSData(Condition condition);

    /**
     * 从数据库中按条件查询各种联网方式的新增用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<CatalogData> getNetWorkData(Condition condition);

    /**
     * 从数据库中按条件查询各个省份的新增用户数、活跃用户数和启动数
     * @param condition 查询条件
     * @return 查询结果数组
     */
    List<CatalogDataActive> getProvinceData(Condition condition);

}
