package edu.neu.titan.titanApp.service.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.common.utils.SetConditionUtils;
import edu.neu.titan.titanApp.dao.IChannelDAO;
import edu.neu.titan.titanApp.dao.ICommonDAO;
import edu.neu.titan.titanApp.dao.IUserAnalyseDAO;
import edu.neu.titan.titanApp.service.IChannelService;
import edu.neu.titan.titanApp.service.IDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 16:27
 * @Version: 1.0
 * @Description: 接口实现类，用于实现渠道分析部分具体业务逻辑
 */
@Service
public class ChannelServiceImpl implements IChannelService {

    // 相关DAO字段
    private IChannelDAO channelDAO;
    private ICommonDAO commonDAO;

    //所需的服务
    @Resource
    private IDateService dateService;

    /**
     *
     * @param channelDAO
     */
    @Autowired
    public void setChannelDAO(IChannelDAO channelDAO){
        this.channelDAO=channelDAO;
    }


    /**
     * 求新增用户的TOP10渠道
     *
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */
    @Override
    public Map<String, List> getInstallationTop10Channels(Condition condition) {
        List<String> top10ChannelsName=channelDAO.getInstallationTop10ChannelsName(condition);
        System.out.println(top10ChannelsName.toString());
        List<Integer> top10ChannelsNum=channelDAO.getInstallationTop10Channels(condition);

        return getChannelsMap(condition,top10ChannelsName,top10ChannelsNum);
    }

    /**
     * 求活跃用户的TOP10渠道
     *
     * @param condition 查询条件
     * @return 活跃用户版本分布列表
     */
    @Override
    public Map<String, List> getActiveUserTop10Channels(Condition condition) {
        List<String> top10ChannelsName=channelDAO.getActiveUserTop10ChannelsName(condition);
        List<Integer> top10ChannelsNum=channelDAO.getActiveUserTop10Channels(condition);

        return getChannelsMap(condition,top10ChannelsName,top10ChannelsNum);
    }

    /**
     * 求启动次数的TOP10渠道
     *
     * @param condition 查询条件
     * @return 启动次数版本分布列表
     */
    @Override
    public Map<String, List> getLaunchTop10Channels(Condition condition) {
        List<String> top10ChannelsName=channelDAO.getLaunchTop10ChannelsName(condition);
        List<Integer> top10ChannelsNum=channelDAO.getLaunchTop10Channels(condition);

        return getChannelsMap(condition,top10ChannelsName,top10ChannelsNum);
    }

    private Map<String, List> getChannelsMap(Condition condition,List<String> nameList,List<Integer> numList){
        int dateLength=dateService.getDays(condition).length;
        System.out.println(dateLength);
        System.out.println(numList.size());
        Map<String, List> map = new HashMap<>();    //map 中存储 “name"：List<name>，"topK"：List<Integer>，一共有 10 个K，即会存储10个List<Integer>
        for (int i=0;i<=9;i++){
            List<Integer> temp=numList.subList(dateLength*i,dateLength*(i+1));
            map.put("top"+(i+1),temp);
        }
        map.put("nameList",nameList);
        return map;
    }

    /**
     * 返回指定区间内的天、周、月横轴
     * @param condition 传入的条件
     * @return 封装好的横轴
     */
    @Override
    public Map<String, List> getDateCoordinate(Condition condition) {
        Map<String, List> map = new HashMap<>();
        //横轴
        map.put("dateDay", Arrays.asList(DateUtils.getDays(condition.getStart_date(), condition.getEnd_date())));
        map.put("dateWeek", Arrays.asList(DateUtils.getWeeks(condition.getStart_date(), condition.getEnd_date())));
        map.put("dateMonth", Arrays.asList(DateUtils.getMonths(condition.getStart_date(), condition.getEnd_date())));

        return map;
    }

    @Override
    public Map<String, List> getDataTableMap(Condition condition){
        Map<String, List> map = new HashMap<>();
        map.put("nameTable",channelDAO.getNameAllChannels());
        map.put("installationTable",channelDAO.getInstallationAllChannels(condition));
        map.put("activeTable",channelDAO.getActiveUserAllChannels(condition));
        map.put("sumUserTable",channelDAO.getSumInstallation());
        return map;
    }

}
