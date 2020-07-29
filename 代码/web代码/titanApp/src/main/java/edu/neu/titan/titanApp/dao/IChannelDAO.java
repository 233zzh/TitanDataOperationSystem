package edu.neu.titan.titanApp.dao;

import edu.neu.titan.titanApp.common.beans.Condition;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 16:44
 * @Version: 1.0
 * @Description: 访问渠道分析相关数据库的接口
 */
public interface IChannelDAO {

    /**
     * 求新增用户的TOP10渠道（num）
     *
     * @param condition 查询条件
     * @return 新增用户版本分布列表
     */
    List<Integer> getInstallationTop10Channels(Condition condition);

    /**
     * 求活跃用户的TOP10渠道（num）
     *
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */

    List<Integer> getActiveUserTop10Channels(Condition condition);

    /**
     * 求启动次数的TOP10渠道（num）
     *
     * @param condition 查询条件
     * @return 启动次数版本分布列表
     */
    List<Integer> getLaunchTop10Channels(Condition condition);

    /**
     * 求新增用户的TOP10渠道（name）
     *
     * @param condition 查询条件
     * @return 新增用户版本分布列表
     */
    List<String> getInstallationTop10ChannelsName(Condition condition);

    /**
     * 求活跃用户的TOP10渠道（name）
     *
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */

    List<String> getActiveUserTop10ChannelsName(Condition condition);

    /**
     * 求启动次数的TOP10渠道（name）
     *
     * @param condition 查询条件
     * @return 启动次数版本分布列表
     */
    List<String> getLaunchTop10ChannelsName(Condition condition);

    /**
     * 获取所有渠道的名字
     * @return
     */
    List<String> getNameAllChannels();

    /**
     * 获取所有渠道的新增用户
     * @return
     */
    List<Integer> getInstallationAllChannels(Condition condition);

    /**
     * 获取所有渠道的活跃用户
     * @return
     */
    List<Integer> getActiveUserAllChannels(Condition condition);

    /**
     * 求累计新增用户
     *
     * @return
     */
    List<Integer> getSumInstallation();
}
