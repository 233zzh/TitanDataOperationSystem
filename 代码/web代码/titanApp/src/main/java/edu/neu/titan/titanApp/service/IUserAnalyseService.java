package edu.neu.titan.titanApp.service;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.Distribution;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.common.utils.SetConditionUtils;
import edu.neu.titan.titanApp.dao.sql.UserAnalyseTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/17
 * @Time: 16:21
 * @Version: 1.0
 * @Description: 定义提供用户分析所需数据的方法，这里只做大体方法定义，具体准备哪些数据在实现类中准备
 */
public interface IUserAnalyseService {
    /**
     * 返回新增用户分析中新增趋势的天、周、月所需的数据
     * @param condition 传入的条件
     * @return K可以是天、周、月或者 “x轴”，V可以是x轴数据、y轴数据
     */
    Map<String, List> getInstallationData(Condition condition);

    /**
     * 返回活跃用户分析中活跃趋势、活跃构成、活跃粘度天、周、月所需的数据和周活跃率、月活跃率所需的数据
     * @param condition 传入的条件
     * @return 这个 Map 中的数据比较多，K 有x_天，x_周，x_月，活跃用户_天，活跃用户_周，活跃用户_月，活跃构成天、周、月，活跃粘度天、周、月，周活跃率，月活跃率
     *                               V 就是公用的三个 x 轴数据和各个K所需的y轴数据
     */
    Map<String, List> getActiveData(Condition condition);

    /**
     * 返回启动次数分析中所需的天、周、月数据
     * @param condition 传入的条件
     * @return K是x_天，x_周，x_月，V是K对应的x轴和y轴所需的数据
     */
    Map<String, List> getLaunchData(Condition condition);

    /**
     * 返回版本分布所需的数据
     * @param condition 传入的条件
     * @return K是标签和“x轴”，V是x轴数据和对应标签的y轴数据
     */
    Map<String, Map<String, List>> getVersionData(Condition condition);
    /**
     * 获得以天为间隔的新增用户数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getInstallationDataDay(Condition condition);

    /**
     * 获得以周为间隔的新增用户数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getInstallationDataWeek(Condition condition);

    /**
     * 获得以月为间隔的新增用户数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getInstallationDataMonth(Condition condition);

    /**
     * 获得以天为间隔的活跃趋势数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getActiveTrendDataDay(Condition condition);

    /**
     * 获得以周为间隔的活跃趋势数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getActiveTrendDataWeek(Condition condition);

    /**
     * 获得以月为间隔的活跃趋势数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getActiveTrendDataMonth(Condition condition);

    /**
     * 获得以天为间隔的活跃用户构成数据，先查询新增用户和活跃用户，在做除法
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Double> getActiveFormDataDay(Condition condition);

    /**
     * 获得以周为间隔的活跃用户构成数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Double> getActiveFormDataWeek(Condition condition);

    /**
     * 获得以月为间隔的活跃用户构成数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Double> getActiveFormDataMonth(Condition condition);

    /**
     * 获得每个日期前最近day天活跃粘度数据 DAU/最近day天活跃用户，DAU/最近30天活跃用户
     * @param condition 传入的条件
     * @param days 前 days 天
     * @return 某段时间的数据
     */
     List<Double> getActiveViscosityData(int days, Condition condition);

    /**
     * 获得周活跃率
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Double> getActiveRateWeekData(Condition condition);

    /**
     * 获得月活跃率
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Double> getActiveRateMonthData(Condition condition);

    /**
     * 获得以天为间隔的启动次数数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getLaunchDataDay(Condition condition);

    /**
     * 获得以周为间隔的启动次数数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getLaunchDataWeek(Condition condition);

    /**
     * 获得以月为间隔的启动次数数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     List<Integer> getLaunchDataMonth(Condition condition);

    /**
     * 获得新增用户的版本分布数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     Map<String, List> getTopVersionDataInstallation(Condition condition);

    /**
     * 获得活跃用户的版本分布数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     Map<String, List> getTopVersionDataActive(Condition condition);

    /**
     * 获得启动次数的版本分布数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
     Map<String, List> getTopVersionDataLaunch(Condition condition);

}
