package edu.neu.titan.titanApp.service;

import edu.neu.titan.titanApp.common.beans.Condition;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 16:23
 * @Version: 1.0
 * @Description: 用于处理渠道分析业务的service接口
 */
public interface IChannelService {

    /**
     * 求新增用户的TOP10渠道
     *
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */
    Map<String, List> getInstallationTop10Channels(Condition condition);

    /**
     * 求活跃用户的TOP10渠道
     *
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */
    Map<String, List> getActiveUserTop10Channels(Condition condition);

    /**
     * 求启动次数的TOP10渠道
     *
     * @param condition 查询条件
     * @return 启动次数版本分布列表
     */
    Map<String, List> getLaunchTop10Channels(Condition condition);

    /**
     * 返回指定区间内的天、周、月横轴
     *
     * @param condition 传入的条件
     * @return 封装好的横轴
     */
    Map<String, List> getDateCoordinate(Condition condition);

    /**
     * 返回渠道明细数据表格（datatable）的map
     * @param condition
     * @return
     */
    Map<String, List> getDataTableMap(Condition condition);
}
