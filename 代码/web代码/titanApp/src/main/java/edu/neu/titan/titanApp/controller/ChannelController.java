package edu.neu.titan.titanApp.controller;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.service.IChannelService;
import edu.neu.titan.titanApp.service.IDateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 16:14
 * @Version: 1.0
 * @Description: 渠道分析控制器
 */
@Controller
public class ChannelController {

    //所需的服务
    @Resource
    private IChannelService channelService;
    @Resource
    private IDateService dateService;

    /**
     * 为渠道分析模块中的渠道列表子模块展示准备数据
     *
     * @param condition
     * @return
     */
//    @RequestMapping(value = "/getChannelListData")
//    @ResponseBody
//    public Map<String,Object> getChannelListData(Condition condition){
//        Map<String, Object> map = new HashMap<>();
//        map.put("channels_date", dateService.getDays(condition));
//        // 装载数据
//        // 获取渠道分析模块下渠道列表子模块中的新增用户（Tab）
//        map.put("installation",channelService.getInstallation(condition));
//        // 获取渠道分析模块下渠道列表子模块中的活跃用户（Tab）
//        map.put("activeUser",channelService.getActiveUser(condition));
//        // 获取渠道分析模块下渠道列表子模块中的启动次数（Tab）
//        map.put("launchNum",channelService.getLaunch(condition));
//        return map;
//    }

    /**
     * 为渠道分析模块中的渠道列表子模块展示准备数据
     *
     * @param condition
     * @return
     */

    @RequestMapping(value = "/getTop10ChannelsData")
    @ResponseBody
    public Map<String, Map<String, List>> getTop10ChannelsData(Condition condition) {
        Map<String, Map<String, List>> map = new HashMap<>();

        map.put("commonDate", channelService.getDateCoordinate(condition));
        map.put("installationData", channelService.getInstallationTop10Channels(condition));
        map.put("activeData", channelService.getActiveUserTop10Channels(condition));
        map.put("launchData", channelService.getLaunchTop10Channels(condition));
        map.put("datatable", channelService.getDataTableMap(condition));
        return map;
    }
}
