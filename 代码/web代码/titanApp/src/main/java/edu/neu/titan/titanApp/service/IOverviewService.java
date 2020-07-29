package edu.neu.titan.titanApp.service;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.Distribution;
import edu.neu.titan.titanApp.common.beans.Trend;
import edu.neu.titan.titanApp.common.utils.DecimalUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/17
 * @Time: 14:26
 * @Version: 1.0
 * @Description:
 */

public interface IOverviewService {
    /**
     * 为整体趋势中的整体概况(近几日平均、总数、同比)准备数据
     * @return Trend 是 (当前数字，同比）
     */
    Map<String, Trend> getOverTrendData();

    /**
     * 为整体趋势中的折线图准备数据
     * @param condition 传入的条件
     * @return Object可以是 x 轴的数据，也可以是 y 轴的数据
     */
    Map<String, List> getLineTrendData(Condition condition);

    /**
     * 为整体趋势中的Top10版本准备数据
     * @return 因为活跃用户、新增用户这种标签中要展示的版本不同，所以不能共用 x 轴
     */
    Map<String, List> getTopVersionData();


    /**
     * 为整体趋势中的Top10渠道准备数据
     * @return 因为活跃用户、新增用户这种标签中要展示的渠道不同，所以不能共用 x 轴，要使用 Distribution 来指定每个版本对应的数量
     */

    Map<String, List> getTopChannelData();
    /**
     * 获得新增用户的某几日的日平均数、同比
     * @param day 前 day 天
     * @return Trend：平均数，同比
     */
    Trend getOverTrendDataInstallationAvg(int day);

    /**
     * 获得活跃用户的某几日的平均数、同比
     * @param day 前 day 天
     * @return Trend：平均数，同比
     */
    Trend getOverTrendDataActiveUserAvg(int day);

    /**
     * 获得新用户次日留存率的某几日平均数和同比
     * @param day 前 n 天
     * @return Trend：平均数，同比
     */
    Trend getOverTrendDataRetentionAvg(int day);

    /**
     * 获得活跃用户的 7 日平均数
     * @param day 前 day 天
     * @return Trend：总数，同比
     */
    Trend getOverTrendActiveUserSum(int day);

    /**
     * 获得累计用户数
     * @return Trend：总数，同比，同比为 0
     */
    Trend getOverTrendUserSum();

    /**
     * 为折线图中的新增用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 某几天的新增用户数据
     */
    List<Integer> getLineTrendDataInstallation(Condition condition);

    /**
     * 为折线图中的活跃用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 某几天的活跃用户数据
     */
    List<Integer> getLineTrendDataActiveUser(Condition condition);

    /**
     * 为折线图中的启动次数准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 某几天的启动次数数据
     */
    List<Integer> getLineTrendDataLaunch(Condition condition);

    /**
     * 为Top版本的新增用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 新增用户分布的版本
     */
    Map<String, List> getTopVersionDataInstallation(Condition condition);

    /**
     * 为Top版本的活跃用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 活跃用户分布的版本
     */
    Map<String, List> getTopVersionDataActiveUser(Condition condition);

    /**
     * 为Top版本的累计用户准备数据，注：这里的 Cal 代表 Calculation(累计)
     * @param condition 传入的条件，与前端保持一致
     * @return 累计用户分布的版本
     */
    Map<String, List> getTopVersionDataCalUser(Condition condition);

    /**
     * 为Top渠道的新增用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 新增用户分布的渠道
     */
    Map<String, List> getTopChannelDataInstallation(Condition condition);

    /**
     * 为Top渠道的活跃用户准备数据
     * @param condition 传入的条件，与前端保持一致
     * @return 活跃用户分布的渠道
     */
    Map<String, List> getTopChannelDataActiveUser(Condition condition);

    /**
     * 为Top渠道的累计用户准备数据，注：这里的 Cal 代表 Calculation(累计)
     * @param condition 传入的条件，与前端保持一致
     * @return 累计用户分布的渠道
     */
    Map<String, List> getTopChannelDataCalUser(Condition condition);
}
