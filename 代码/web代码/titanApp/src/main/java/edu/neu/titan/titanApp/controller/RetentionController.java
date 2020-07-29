package edu.neu.titan.titanApp.controller;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.service.IDateService;
import edu.neu.titan.titanApp.service.IRetentionService;
import edu.neu.titan.titanApp.service.IUserAnalyseService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 留存分析控制器
 */
@Controller
public class RetentionController {

    // 所需的服务
    @Resource
    private IRetentionService retentionService;
    @Resource
    private IDateService dateService;
    @Resource
    public IUserAnalyseService userAnalyseService;

    /**
     * 为留存分析中留存用户页面准备数据
     * @param condition 接收前台的参数
     * @return 返回前台的包含所需数据的 Map
     */
    @RequestMapping(value = "/getRetentionData")
    @ResponseBody
    public Map<String, Object> getRetentionData(Condition condition) {
        Map<String, Object> map = new HashMap<>();
        // 装载数据
        map.put("increase",userAnalyseService.getInstallationData(condition));
        map.put("active",userAnalyseService.getActiveData(condition));
        map.put("dataIncreaseDay",retentionService.getRetentionDataIncreaseDay(condition));
        map.put("dataIncreaseWeek",retentionService.getRetentionDataIncreaseWeek(condition));
        map.put("dataIncreaseMonth",retentionService.getRetentionDataIncreaseMonth(condition));
        map.put("dataActiveDay",retentionService.getRetentionDataActiveDay(condition));
        map.put("dataActiveWeek",retentionService.getRetentionDataActiveWeek(condition));
        map.put("dataActiveMonth",retentionService.getRetentionDataActiveMonth(condition));
        return map;
    }

    /**
     * 为留存分析中用户活跃度页面准备数据
     * @return 返回前台的包含所需数据的 Map
     */
    @RequestMapping(value = "/getFreshnessData")
    @ResponseBody
    public Map<String, Object> getFreshnessData() {
        Map<String, Object> map = new HashMap<>();
        // 获得结果
        Pair<String[], Integer[][]> res = retentionService.getRetentionDataFreshness();
        // 装载数据
        map.put("days",res.getFirst());
        map.put("data",res.getSecond());
        return map;
    }

    /**
     * 为留存分析中用户活跃度页面准备数据
     * @return 返回前台的包含所需数据的 Map
     */
    @RequestMapping(value = "/getActivityData")
    @ResponseBody
    public Map<String, Object> getActivityData() {
        Map<String, Object> map = new HashMap<>();
        // 获得结果
        Pair<String[], Integer[][]> res = retentionService.getRetentionDataActivity();
        // 装载数据
        map.put("days",res.getFirst());
        map.put("data",res.getSecond());
        return map;
    }
}
