package edu.neu.titan.titanApp.dao.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.RetentionDataDay;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.dao.IChannelDAO;
import edu.neu.titan.titanApp.dao.sql.ChannelTemplate;
import edu.neu.titan.titanApp.dao.sql.RetentionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 16:45
 * @Version: 1.0
 * @Description: 接口实现类，用于访问渠道分析相关数据库
 */
@Repository
public class ChannelDAOImpl implements IChannelDAO {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public ChannelDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * 求新增用户的TOP10渠道
     *
     * @param condition 查询条件
     * @return 新增用户版本分布列表
     */
    @Override
    public List<Integer> getInstallationTop10Channels(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ChannelTemplate.QUERY_INSTALLATION_TOP10_CHANNELS)
                .sql_group(ChannelTemplate.GROUP_BY_INSTALLATION_DATE_AND_CHANNEL)
//                .sql_order(ChannelTemplate.ORDER_BY_CHANNEL_AND_INSTALLATION_DATE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 求活跃用户的TOP10渠道
     *
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */
    @Override
    public List<Integer> getActiveUserTop10Channels(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ChannelTemplate.QUERY_ACTIVE_TOP10_CHANNELS)
                .sql_group(ChannelTemplate.GROUP_BY_ACTIVE_DATE_AND_CHANNEL)
//                .sql_order(ChannelTemplate.ORDER_BY_CHANNEL_AND_ACTIVE_DATE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 求启动次数的TOP10渠道
     *
     * @param condition 查询条件
     * @return 启动次数版本分布列表
     */
    @Override
    public List<Integer> getLaunchTop10Channels(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ChannelTemplate.QUERY_LAUNCH_TOP10_CHANNELS)
                .sql_group(ChannelTemplate.GROUP_BY_LAUNCH_DATE_AND_CHANNEL)
//                .sql_order(ChannelTemplate.ORDER_BY_CHANNEL_AND_LAUNCH_DATE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 求新增用户的TOP10渠道
     *
     * @param condition 查询条件
     * @return 新增用户版本分布列表
     */
    @Override
    public List<String> getInstallationTop10ChannelsName(Condition condition) {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ChannelTemplate.QUERY_INSTALLATION_TOP10_CHANNELS_NAME)
                .sql_group(ChannelTemplate.GROUP_BY_CHANNEL_ID)
//                .sql_order(ChannelTemplate.ORDER_BY_CHANNEL_AND_INSTALLATION_DATE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 求活跃用户的TOP10渠道
     *
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */
    @Override
    public List<String> getActiveUserTop10ChannelsName(Condition condition) {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ChannelTemplate.QUERY_ACTIVE_TOP10_CHANNELS_NAME)
                .sql_group(ChannelTemplate.GROUP_BY_CHANNEL_ID)
//                .sql_order(ChannelTemplate.ORDER_BY_CHANNEL_AND_ACTIVE_DATE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 求启动次数的TOP10渠道
     *
     * @param condition 查询条件
     * @return 启动次数版本分布列表
     */
    @Override
    public List<String> getLaunchTop10ChannelsName(Condition condition) {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ChannelTemplate.QUERY_LAUNCH_TOP10_CHANNELS_NAME)
                .sql_group(ChannelTemplate.GROUP_BY_CHANNEL_ID)
//                .sql_order(ChannelTemplate.ORDER_BY_CHANNEL_AND_LAUNCH_DATE)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 获取所有渠道的名字
     *
     * @return
     */
    @Override
    public List<String> getNameAllChannels() {
        return SQLResultBuilder.newInstance(String.class)
                .sql_select(ChannelTemplate.QUERY_NAME_ALL_CHANNELS)
                .build()
                .getResult(template);
    }

    /**
     * 获取所有渠道的新增用户
     *
     * @return
     */
    @Override
    public List<Integer> getInstallationAllChannels(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ChannelTemplate.QUERY_INSTALLATION_ALL_CHANNELS)
                .sql_group(ChannelTemplate.GROUP_BY_CHANNEL_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }

    /**
     * 获取所有渠道的活跃用户
     *
     * @return
     */
    @Override
    public List<Integer> getActiveUserAllChannels(Condition condition) {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ChannelTemplate.QUERY_ACTIVE_TABLE_ALL_CHANNELS)
                .sql_group(ChannelTemplate.GROUP_BY_CHANNEL_ID)
                .condition(condition)
                .build()
                .getResult(template);
    }


    /**
     * 求所有渠道的累计新增用户
     *
     * @return
     */
    @Override
    public List<Integer> getSumInstallation() {
        return SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ChannelTemplate.QUERY_SUM_INSTALLATION)
                .sql_group(ChannelTemplate.GROUP_BY_CHANNEL_ID)
                .build()
                .getResult(template);
    }


}
