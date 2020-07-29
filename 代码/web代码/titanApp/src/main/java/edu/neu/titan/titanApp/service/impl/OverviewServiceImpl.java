package edu.neu.titan.titanApp.service.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.Distribution;
import edu.neu.titan.titanApp.common.beans.Trend;
import edu.neu.titan.titanApp.common.constant.Constants;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.common.utils.DecimalUtils;
import edu.neu.titan.titanApp.common.utils.SetConditionUtils;
import edu.neu.titan.titanApp.dao.IRetentionDAO;
import edu.neu.titan.titanApp.dao.IUserAnalyseDAO;
import edu.neu.titan.titanApp.service.IOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by IntelliJ IDEA
 *
 * @Author: zhaolei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/17
 * @Time: 14:22
 * @Version: 1.0
 * @Description:
 */

@Service
public class OverviewServiceImpl implements IOverviewService {

    private IUserAnalyseDAO userAnalyseDAO;
    private IRetentionDAO retentionDAO;

    @Autowired
    public OverviewServiceImpl(IUserAnalyseDAO userAnalyseDao, IRetentionDAO retentionDAO) {
        this.userAnalyseDAO = userAnalyseDao;
        this.retentionDAO = retentionDAO;
    }

    /**
     * 实现接口中定义的 getOverTrendData，通过调用下面前缀为 getOverTrendData 的方法来准备整体趋势概况所需的数据
     * @return K代表分析指标，V代表该指标所需的数据
     */
    @Override
    public Map<String, Trend> getOverTrendData() {
        Map<String, Trend> map = new HashMap<>();

        map.put("installationAvg", getOverTrendDataInstallationAvg(Constants.DEFAULT_DAYS_OVERVIEW_DAY_1));   //新增用户平均数
        map.put("activeUserAvg", getOverTrendDataActiveUserAvg(Constants.DEFAULT_DAYS_OVERVIEW_DAY_1)); //活跃用户平均数
        map.put("retentionAvg", getOverTrendDataRetentionAvg(Constants.DEFAULT_DAYS_OVERVIEW_DAY_1));   //留存率平均数
        map.put("activeUserSum_1", getOverTrendActiveUserSum(Constants.DEFAULT_DAYS_OVERVIEW_DAY_1));   //7 天活跃用户总数
        map.put("activeUserSum_2", getOverTrendActiveUserSum(Constants.DEFAULT_DAYS_OVERVIEW_DAY_2));   //30 天活跃用户总数
        map.put("userSum", getOverTrendUserSum());  //累计用户数

        return map;
    }

    /**
     * 实现接口中定义的 getLineTrendData，通过调用下面前缀为 getLineTrendData 的方法来准备绘制折线图所需的数据
     * @param condition 传入的条件
     * @return K既可以为分析指标，又可以为 “x”，V可以是y轴所需数据，也可以是x轴所需数据
     */
    @Override
    public Map<String, List> getLineTrendData(Condition condition) {
        Map<String, List> map = new HashMap<>();

        map.put("dateList", Arrays.asList(DateUtils.getDays(condition.getStart_date(), condition.getEnd_date())));
        map.put("installationLine", getLineTrendDataInstallation(condition));
        map.put("activeUserLine", getLineTrendDataActiveUser(condition));
        map.put("launchLine", getLineTrendDataLaunch(condition));

        return map;
    }

    /**
     * 实现接口中定义的 getTopVersionData，通过调用下面前缀为 getTopVersionData 的方法来准备饼图中Top版本所需的数据
     * @return 因为图中对于不同的标签，版本可能不同，所以这里不能用共同的 “x”，而要把每个标签的版本及版本分布分别返回
     */
    @Override
    public Map<String, List> getTopVersionData() {
        Map<String, List> map = new HashMap<>();
        map.putAll(getTopVersionDataInstallation(SetConditionUtils.beforeDaysCondition(Constants.DEFAULT_DAYS_OVERVIEW_DAY_2)));
        map.putAll(getTopVersionDataActiveUser(SetConditionUtils.beforeDaysCondition(Constants.DEFAULT_DAYS_OVERVIEW_DAY_2)));
        map.putAll(getTopVersionDataCalUser(SetConditionUtils.beforeDaysCondition(Constants.DEFAULT_DAYS_OVERVIEW_DAY_3)));

        return map;
    }

    /**
     * 实现接口中定义的 getTopChannelData，通过调用下面前缀为 getTopChannel 的方法来准备饼图中Top渠道所需的数据
     * @return 与上个方法类似
     */
    @Override
    public Map<String, List> getTopChannelData() {
        Map<String, List> map = new HashMap<>();
        map.putAll(getTopChannelDataInstallation(SetConditionUtils.beforeDaysCondition(Constants.DEFAULT_DAYS_OVERVIEW_DAY_2)));
        map.putAll(getTopChannelDataActiveUser(SetConditionUtils.beforeDaysCondition(Constants.DEFAULT_DAYS_OVERVIEW_DAY_2)));
        map.putAll(getTopChannelDataCalUser(SetConditionUtils.beforeDaysCondition(Constants.DEFAULT_DAYS_OVERVIEW_DAY_3)));

        return map;
    }

    /**
     * 获得新增用户的某几日的日平均数、同比
     * @param day 前 day 天
     * @return Trend：平均数，同比
     */
    @Override
    public Trend getOverTrendDataInstallationAvg(int day) {
        Map<String, Condition> conditionMap = getCondition(day);

        int current = userAnalyseDAO.getInstallationAvg(conditionMap.get("todayCondition"));
        int old = userAnalyseDAO.getInstallationAvg(conditionMap.get("beforeCondition"));
        double compare = (double)(current - old) / old;

        //把 int 转成 String，把 double 通过 Util 转成百分数字符串
        return new Trend(String.valueOf(current), DecimalUtils.doubleToPercent(compare));
    }

    /**
     * 获得活跃用户的某几日的平均数、同比
     * @param day 前 day 天
     * @return Trend：平均数，同比
     */
    @Override
    public Trend getOverTrendDataActiveUserAvg(int day) {
        Map<String, Condition> conditionMap = getCondition(day);

        int current = userAnalyseDAO.getActiveUserAvg(conditionMap.get("todayCondition"));
        int old = userAnalyseDAO.getActiveUserAvg(conditionMap.get("beforeCondition"));
        double compare = (double)(current - old) / old;

        //把 int 转成 String，把 double 通过 Util 转成百分数字符串
        return new Trend(String.valueOf(current), DecimalUtils.doubleToPercent(compare));
    }

    /**
     * 获得新用户次日留存率的某几日平均数和同比
     * @param day 前 n 天
     * @return Trend：平均数，同比
     */
    @Override
    public Trend getOverTrendDataRetentionAvg(int day) {
        Map<String, Condition> conditionMap = getCondition(day);

        double current = retentionDAO.getAvgRetentionRate(conditionMap.get("todayCondition"));
        double old = retentionDAO.getAvgRetentionRate(conditionMap.get("beforeCondition"));
        double compare = (current - old) / old;

        //把 double 转成 String，把 double 通过 Util 转成百分数字符串
        return new Trend(DecimalUtils.doubleToPercent(current), DecimalUtils.doubleToPercent(compare));
    }

    /**
     * 获得活跃用户的 7 日平均数
     * @param day 前 day 天
     * @return Trend：总数，同比
     */
    @Override
    public Trend getOverTrendActiveUserSum(int day) {
        Map<String, Condition> conditionMap = getCondition(day);

        int current = userAnalyseDAO.getActiveUserSum(conditionMap.get("todayCondition"));
        int old = userAnalyseDAO.getActiveUserSum(conditionMap.get("beforeCondition"));
        double compare = (double)(current - old) / old;

        //把 int 转成 String，把 double 通过 Util 转成百分数字符串
        return new Trend(String.valueOf(current), DecimalUtils.doubleToPercent(compare));
    }

    /**
     * 获得累计用户数
     * @return Trend：总数，同比，同比为 0
     */
    @Override
    public Trend getOverTrendUserSum() {

        int sum = userAnalyseDAO.getInstallationSum(null);  //因为该方法返回的是总的用户数，所以不需要加 Condition

        return new Trend(String.valueOf(sum), "0");
    }

    /**
     * 为折线图中的新增用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 某几天的新增用户数据
     */
    @Override
    public List<Integer> getLineTrendDataInstallation(Condition condition) {
        return userAnalyseDAO.getInstallationDataDay(condition);
    }

    /**
     * 为折线图中的活跃用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 某几天的活跃用户数据
     */
    @Override
    public List<Integer> getLineTrendDataActiveUser(Condition condition) {
        return userAnalyseDAO.getActiveTrendDataDay(condition);
    }

    /**
     * 为折线图中的启动次数准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 某几天的启动次数数据
     */
    @Override
    public List<Integer> getLineTrendDataLaunch(Condition condition) {
        return userAnalyseDAO.getLaunchDataDay(condition);
    }

    /**
     * 为Top版本的新增用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 新增用户分布的版本
     */
    @Override
    public Map<String, List> getTopVersionDataInstallation(Condition condition) {
        List<Distribution> distributionList = userAnalyseDAO.getTopVersionDataLInstallation(condition);
        Map<String, List> map = new HashMap<>();
        map.put("installationNameList", getDistributionName(distributionList));
        map.put("installationDistributionList", distributionList);

        return map;
    }

    /**
     * 为Top版本的活跃用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 活跃用户分布的版本
     */
    @Override
    public Map<String, List> getTopVersionDataActiveUser(Condition condition) {
        List<Distribution> distributionList = userAnalyseDAO.getTopVersionDataActive(condition);
        Map<String, List> map = new HashMap<>();
        map.put("activeUserNameList", getDistributionName(distributionList));
        map.put("activeUserDistributionList", distributionList);

        return map;
    }

    /**
     * 为Top版本的累计用户准备数据，注：这里的 Cal 代表 Calculation(累计)
     * @param condition 传入的条件，与前端保持一致
     * @return 累计用户分布的版本
     */
    @Override
    public Map<String, List> getTopVersionDataCalUser(Condition condition) {
        List<Distribution> distributionList = userAnalyseDAO.getTopVersionDataLaunch(condition);
        Map<String, List> map = new HashMap<>();
        map.put("calUserNameList", getDistributionName(distributionList));
        map.put("calUserDistributionList", distributionList);

        return map;
    }

    /**
     * 为Top渠道的新增用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 新增用户分布的渠道
     */
    @Override
    public Map<String, List> getTopChannelDataInstallation(Condition condition) {
        List<Distribution> distributionList = userAnalyseDAO.getTopChannelDataLInstallation(condition);
        Map<String, List> map = new HashMap<>();
        map.put("installationNameList", getDistributionName(distributionList));
        map.put("installationDistributionList", distributionList);

        return map;
    }

    /**
     * 为Top渠道的活跃用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 活跃用户分布的渠道
     */
    @Override
    public Map<String, List> getTopChannelDataActiveUser(Condition condition) {
        List<Distribution> distributionList = userAnalyseDAO.getTopChannelDataActive(condition);
        Map<String, List> map = new HashMap<>();
        map.put("activeUserNameList", getDistributionName(distributionList));
        map.put("activeUserDistributionList", distributionList);

        return map;
    }

    /**
     * 为Top渠道的累计用户准备数据，注：这里的 Cal 代表 Calculation(累计)
     * @param condition 传入的条件，与前端保持一致
     * @return 累计用户分布的渠道
     */
    @Override
    public Map<String, List> getTopChannelDataCalUser(Condition condition) {
        List<Distribution> distributionList = userAnalyseDAO.getTopChannelDataLaunch(condition);
        Map<String, List> map = new HashMap<>();
        map.put("calUserNameList", getDistributionName(distributionList));
        map.put("calUserDistributionList", distributionList);

        return map;
    }

    /**
     * 返回一个 Condition Map，分别是 Condition(today, old=today-days)，Condition(old, beforeOld=old-days)
     * @param day 前day天
     * @return Map
     */
    private Map<String, Condition> getCondition(int day) {
        String[] todayAndBefore = DateUtils.getDateFromToday(day);  //今天和前 day 天的日期
        String multiBefore = DateUtils.minusDays(todayAndBefore[0], day);     //前 day 天所在日期的前 day 天

        Condition todayCondition = Condition.instance(todayAndBefore[0], todayAndBefore[1]);    //当前 condition
        Condition beforeCondition = Condition.instance(multiBefore, todayAndBefore[0]); //上一个周期的 conditoin

        Map<String, Condition> map = new HashMap<>();
        map.put("todayCondition", todayCondition);
        map.put("beforeCondition", beforeCondition);

        return map;
    }

    /**
     * 获取 distributionList 中的每个元素的 name
     * @param distributionList 带获取列表
     * @return nameList
     */
    private List<String> getDistributionName(List<Distribution> distributionList) {
        List<String> nameList = new ArrayList<>(distributionList.size());

        for (Distribution distribution : distributionList) {
            nameList.add(distribution.getName());
        }

        return nameList;
    }
}
