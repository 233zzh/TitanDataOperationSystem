package edu.neu.titan.titanApp.controller;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.service.IRetentionService;
import edu.neu.titan.titanApp.service.IUserAnalyseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/17
 * @Time: 14:17
 * @Version: 1.0
 * @Description: 用户分析控制器，为用户分析提供数据
 */

@Controller
public class UserAnalyseController {

    @Resource
    private IUserAnalyseService userAnalyserService;
    @Resource
    private IRetentionService retentionService;

    /**
     * 返回新增用户分析中新增趋势的天、周、月所需的数据
     * @param condition 传入的条件
     * @return K可以是天、周、月或者 “x轴”，V可以是x轴数据、y轴数据
     */
    @RequestMapping(value = "/getInstallationData")
    @ResponseBody
    public Map<String, List> getInstallationData(Condition condition) {
        System.out.println(condition);
        return userAnalyserService.getInstallationData(condition);
    }

    /**
     * 返回新增用户分析中次日留存率所需的数据
     * @param condition 传入的条件
     * @return K是“x轴”或标签，V是x轴数据或y轴数据
     */
    @RequestMapping(value = "/getRetentionDataDay")
    @ResponseBody
    public Map<String, List> getRetentionData(Condition condition) {
        Map<String, List> map = new HashMap<>();
        Double[] retentions = retentionService.getOneDayAfterRetentionRate(condition);
        List<Double> retentionList = Arrays.asList(retentions);

        map.put("retentionData", retentionList);
        map.put("dateDay", Arrays.asList(DateUtils.getDays(condition.getStart_date(), condition.getEnd_date())));
        return map;
    }

    /**
     * 返回活跃用户分析中活跃趋势、活跃构成、活跃粘度天、周、月所需的数据和周活跃率、月活跃率所需的数据
     * @param condition 传入的条件
     * @return 这个 Map 中的数据比较多，K 有x_天，x_周，x_月，活跃用户_天，活跃用户_周，活跃用户_月，活跃构成天、周、月，活跃粘度天、周、月，周活跃率，月活跃率
     *                               V 就是公用的三个 x 轴数据和各个K所需的y轴数据
     */
    @RequestMapping(value = "/getActiveData")
    @ResponseBody
    public Map<String, List> getActiveData(Condition condition) {
        return userAnalyserService.getActiveData(condition);
    }

    /**
     * 返回启动次数分析中所需的天、周、月数据
     * @param condition 传入的条件
     * @return K是x_天，x_周，x_月，V是K对应的x轴和y轴所需的数据
     */
    @RequestMapping(value = "/getLaunchData")
    @ResponseBody
    public Map<String, List> getLaunchData(Condition condition) {
        return userAnalyserService.getLaunchData(condition);
    }

    /**
     * 返回版本分布所需的数据
     * @param condition 传入的条件
     * @return K是标签和“x轴”，V是x轴数据和对应标签的y轴数据
     */
    @RequestMapping(value = "/getVersionData")
    @ResponseBody
    public Map<String, Map<String, List>> getVersionData(Condition condition) {
        return userAnalyserService.getVersionData(condition);
    }
}
