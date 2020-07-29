package edu.neu.titan.titanApp.controller;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.Distribution;
import edu.neu.titan.titanApp.common.beans.Trend;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.service.IOverviewService;
import edu.neu.titan.titanApp.service.IUserAnalyseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/17
 * @Time: 14:15
 * @Version: 1.0
 * @Description: 整体趋势控制器，为整体趋势提供数据
 */

@Controller
public class OverviewController {

    //所需的服务
    @Resource
    private IOverviewService overviewService;

    @Resource
    private IUserAnalyseService userAnalyseService;

    /**
     * 为整体趋势中的整体概况(近几日平均、总数、同比)准备数据
     * @return Trend 是 (当前数字，同比）
     */
    @RequestMapping(value = "/getOverTrendData")
    @ResponseBody
    public Map<String, Trend> getOverTrendData()
    {
        return overviewService.getOverTrendData();
    }

    /**
     * 为整体趋势中的折线图准备数据
     * @param condition 传入的条件
     * @return Object可以是 x 轴的数据，也可以是 y 轴的数据
     */
    @RequestMapping(value = "/getLineTrendData")
    @ResponseBody
    public Map<String, List> getLineTrendData(Condition condition) {
        System.out.println(condition);
        Map<String, List> map = new HashMap<>();
        //横轴
        map.put("dateDay", Arrays.asList(DateUtils.getDays(condition.getStart_date(), condition.getEnd_date())));
        //纵轴
        map.put("installationData", userAnalyseService.getInstallationDataDay(condition));
        map.put("activeUserData", userAnalyseService.getActiveTrendDataDay(condition));
        map.put("launchData", userAnalyseService.getLaunchDataDay(condition));
        map.put("activeFormData", userAnalyseService.getActiveFormDataDay(condition));

        return map;
    }

    /**
     * 为整体趋势中的Top10版本准备数据
     * @return 因为活跃用户、新增用户这种标签中要展示的版本不同，所以不能共用 x 轴
     */
    @RequestMapping(value = "/getTopVersionData")
    @ResponseBody
    public Map<String, List> getTopVersionData() {
        return overviewService.getTopVersionData();
    }


    /**
     * 为整体趋势中的Top10渠道准备数据
     * @return 因为活跃用户、新增用户这种标签中要展示的渠道不同，所以不能共用 x 轴，要使用 Distribution 来指定每个版本对应的数量
     */
    @RequestMapping(value = "/getTopChannelData")
    @ResponseBody
    public Map<String, List> getTopChannelData() {
        return overviewService.getTopChannelData();
    }
}
