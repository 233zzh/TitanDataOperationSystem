package edu.neu.titan.titanApp.controller;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.dao.IParticipationDAO;
import edu.neu.titan.titanApp.service.IChannelService;
import edu.neu.titan.titanApp.service.IDateService;
import edu.neu.titan.titanApp.service.IParticipationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 17:39
 * @Version: 1.0
 * @Description: 用户参与度控制器
 */
@Controller
public class ParticipationController {
    //一次启动的使用时长范围
    private String[] durationRange = {"1秒-3秒", "4秒-10秒", "11秒-30秒", "31秒-1分", "1分-3分", "3分-10分", "10分-30分", "30分+"};
    //用户一天内启动应用的次数范围
    private String[] dayFrequencyRange = {"1-2", "3-5", "6-9", "10-19", "20-49", "50+"};
    //用户一周内启动应用的次数范围
    private String[] weekFrequencyRange = {"1-2", "3-5", "6-9", "10-19", "20-49", "50-99", "100+"};
    //用户一月内启动应用的次数范围
    private String[] monthFrequencyRange = {"1-2", "3-5", "6-9", "10-19", "20-49", "50-99", "100-199", "200-299", "300+"};
    //用户一次启动内访问的页面数范围。
    private String[] pageRange = {"1-2", "3-5", "6-9"};
    //同一用户相邻两次启动间隔的时间长度范围
    private String[] intervalRange = {"首次", "0-24h", "1天", "2天", "3天", "4天", "5天", "6天", "7天", "8-14天", "15-30天"};

    //所需的服务
    @Resource
    private IParticipationService participationService;
    @Resource
    private IDateService dateService;

    /**
     * 为用户参与度的使用时长子模块展示准备数据
     *
     * @param condition
     * @return
     */
    @RequestMapping(value = "/getDurationData")
    @ResponseBody
    public Map<String, Object> getDurationData(Condition condition) {
        Map<String, Object> map = new HashMap<>();
        map.put("durationRange", participationService.getDurationRange());
        // 装载数据
        // 获得用户参与度模块中使用时长子功能中的单次使用时长分布
        map.put("singleDurationData", participationService.getDurationDataSingle(condition));
        //获得用户参与度模块中使用时长子功能中的日使用时长分布
        map.put("dayurationData", participationService.getDurationDataDay(condition));
        return map;
    }

    /**
     * 为用户参与度的使用频率子模块展示准备数据
     *
     * @param condition
     * @return
     */
    @RequestMapping(value = "/getFrequencyData")
    @ResponseBody
    public Map<String, Object> getFrequencyData(Condition condition) {
        Map<String, Object> map = new HashMap<>();
        map.put("dayFrequencyRange", participationService.getFrequencyDayRange());
        map.put("weekFrequencyRange", participationService.getFrequencyWeekRange());
        map.put("monthFrequencyRange", participationService.getFrequencyMonthRange());
        // 装载数据
        // 获得用户参与度模块中使用频率子功能中的日启动次数分布
        map.put("dayFrequencyData", participationService.getFrequencyDataDay(condition));
        // 获得用户参与度模块中使用时长子功能中的周启动次数分布
        map.put("weekFrequencyData", participationService.getFrequencyDataWeek(condition));
        // 获得用户参与度模块中使用时长子功能中的月启动次数分布
        map.put("monthFrequencyData", participationService.getFrequencyDataMonth(condition));
        return map;
    }

    /**
     * 为用户参与度的访问页面子模块展示准备数据
     *
     * @param condition
     * @return
     */
    @RequestMapping(value = "/getPageData")
    @ResponseBody
    public Map<String, Object> getPageData(Condition condition) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageRange", participationService.getPageRange());
        // 装载数据
        // 获得用户参与度模块中访问页面子功能中的访问页面分布
        map.put("pageData", participationService.getPageData(condition));
        return map;
    }

    /**
     * 为用户参与度的使用间隔子模块展示准备数据
     *
     * @param condition
     * @return
     */
    @RequestMapping(value = "/getIntervalData")
    @ResponseBody
    public Map<String, Object> getIntervalData(Condition condition) {
        Map<String, Object> map = new HashMap<>();
        map.put("intervalRange", participationService.getIntervalRange());
        // 装载数据
        // 获得用户参与度模块中使用间隔子功能中的使用间隔分布
        map.put("intervalData", participationService.getIntervalData(condition));
        return map;
    }
}
