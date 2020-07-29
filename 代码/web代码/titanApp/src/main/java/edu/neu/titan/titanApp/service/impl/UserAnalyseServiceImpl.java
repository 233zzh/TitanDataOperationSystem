package edu.neu.titan.titanApp.service.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.Distribution;
import edu.neu.titan.titanApp.common.constant.Constants;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.common.utils.DecimalUtils;
import edu.neu.titan.titanApp.common.utils.SetConditionUtils;
import edu.neu.titan.titanApp.dao.IUserAnalyseDAO;
import edu.neu.titan.titanApp.service.IUserAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: zhaolei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/17
 * @Time: 16:25
 * @Version: 1.0
 * @Description:
 */
@Service
public class UserAnalyseServiceImpl implements IUserAnalyseService {

    public IUserAnalyseDAO userAnalyseDAO;

    @Autowired
    public UserAnalyseServiceImpl(IUserAnalyseDAO userAnalyseDAO) {
        this.userAnalyseDAO = userAnalyseDAO;
    }

    /**
     * 实现接口中定义的 getInstallationData，通过调用下面前缀为 getInstallationData 的方法准备新增用户分析中新增趋势的天、周、月所需的数据
     * @param condition 传入的条件
     * @return K可以是天、周、月或者 “x轴”，V可以是x轴数据、y轴数据
     */
    @Override
    public Map<String, List> getInstallationData(Condition condition) {
        Map<String, List> map = new HashMap<>();

        //横轴
        map.putAll(getDateCoordinate(condition));

        //纵轴
        map.put("installationDay", getInstallationDataDay(condition));
        map.put("installationWeek", getInstallationDataWeek(condition));
        map.put("installationMonth", getInstallationDataMonth(condition));
        //datatable 需要的数据
        map.put("activeFormDay", getActiveFormDataDay(condition));
        map.put("activeFormWeek", getActiveFormDataWeek(condition));
        map.put("activeFormMonth", getActiveFormDataMonth(condition));

        return map;
    }

    /**
     * 实现接口中定义的 getActiveData，通过调用下面前缀为 getActiveData 的方法准备活跃用户分析中活跃趋势、活跃构成、活跃粘度天、周、月所需的数据和周活跃率、月活跃率所需的数据
     * @param condition 传入的条件
     * @return 这个 Map 中的数据比较多，K 有x_天，x_周，x_月，活跃用户_天，活跃用户_周，活跃用户_月，活跃构成天、周、月，活跃粘度天、周、月，周活跃率，月活跃率
     *                               V 就是公用的三个 x 轴数据和各个K所需的y轴数据
     */
    @Override
    public Map<String, List> getActiveData(Condition condition) {
        Map<String, List> map = new HashMap<>();

        //横轴
        map.putAll(getDateCoordinate(condition));
        //纵轴————活跃趋势
        map.put("activeTrendDay", getActiveTrendDataDay(condition));
        map.put("activeTrendWeek", getActiveTrendDataWeek(condition));
        map.put("activeTrendMonth", getActiveTrendDataMonth(condition));
        //纵轴————活跃构成
        map.put("activeFormDay", getActiveFormDataDay(condition));
        map.put("activeFormWeek", getActiveFormDataWeek(condition));
        map.put("activeFormMonth", getActiveFormDataMonth(condition));
        //纵轴————活跃粘度，计算活跃粘度时，需要更改 condition，改成起始日期为 start_date 的前 day 天
        map.put("activeVicosityWeek", getActiveViscosityData(Constants.DEFAULT_DAYS_OVERVIEW_DAY_1, SetConditionUtils.beforeDaysCondition(Constants.DEFAULT_DAYS_OVERVIEW_DAY_1, condition)));
        map.put("activeVicosityMonth", getActiveViscosityData(Constants.DEFAULT_DAYS_OVERVIEW_DAY_2, SetConditionUtils.beforeDaysCondition(Constants.DEFAULT_DAYS_OVERVIEW_DAY_2, condition)));
        //纵轴————周活跃率、月活跃率
        map.put("activeRateWeek", getActiveRateWeekData(condition));
        map.put("activeRateMonth", getActiveRateMonthData(condition));
        return map;
    }


    /**
     * 实现接口中定义的 getLaunchData，通过调用下面前缀为 getLaunchData 的方法准备启动次数分析中所需的天、周、月数据
     * @param condition 传入的条件
     * @return K是x_天，x_周，x_月，V是K对应的x轴和y轴所需的数据
     */
    @Override
    public Map<String, List> getLaunchData(Condition condition) {
        Map<String, List> map = new HashMap<>();

        //横轴
        map.putAll(getDateCoordinate(condition));
        //纵轴———天、周、月启动次数
        map.put("launchDay", getLaunchDataDay(condition));
        map.put("launchWeek", getLaunchDataWeek(condition));
        map.put("launchMonth", getLaunchDataMonth(condition));

        return map;
    }

    /**
     * 实现接口中定义的 getVersionData，通过调用下面前缀为 getVersionData 的方法准备版本分布所需的数据
     * @param condition 传入的条件
     * @return  外层 Map：K 代表 tag，如新增用户，活跃用户等，V 代表每个 tag 的数据；内层 Map: K 有"nameList"和”topK“， V 代表版本名称和每个版本的 topK 数据
     *          如：
     *          {   "commonDate":   //这里本可以返回一个 Map<String, List> 就好了，但是为了兼顾其他元素，还是把它封装到了 Map<String, Map<String, List>>
     *              {
     *                  "dateDay": [2020-05-10, 2020-05-11, 2020-05-12]
     *              },
     *              "installationData":
     *              {
     *                  "nameList": ["V6", "V2", "V11"],
     *                  "top1": [18, 20, 30],
     *                  "top2": [19, 5, 9],
     *                  "top3": [1, 3, 12]
     *               },
     *               "activeData":
     *               {
     *                  "nameList": ["V3", "V5", "V8"],
     *                  "top1": [150, 250, 350],
     *                  "top2": [10, 20, 30],
     *                  "top3": [1, 2, 3]
     *               },
     *               "launchData":
     *               {
     *                  "nameList": ["V4", "V9", "V6"],
     *                  "top1": [12, 25, 51],
     *                  "top2": [13, 29, 43],
     *                  "top3": [15, 21, 35]
     *               },
     *               "allIofo":
     *               {
     *                  "versionName":
     *               }
     *          }
     */
    @Override
    public Map<String, Map<String, List>> getVersionData(Condition condition) {
        Map<String, Map<String, List>> map = new HashMap<>();

        map.put("commonDate", getDateCoordinate(condition));
        map.put("installationData", getTopVersionDataInstallation(condition));
        map.put("activeData", getTopVersionDataActive(condition));
        map.put("launchData", getTopVersionDataLaunch(condition));

        Map<String, List> map2 = new HashMap<>();
        map2.put("versionName", getAllVersionName());
        map2.put("installationAllVersion", getAllVersionDataInstallation(condition));
        map2.put("calUserAllVersion", getAllVersionDataCalUser(condition));
        map2.put("activeAllVersion", getAllVersionDataActive(condition));
        map2.put("launchAllVersion", getAllVersionDataLaunch(condition));

        map.put("overInfo", map2);
        return map;
    }

    /**
     * 获得以天为间隔的新增用户数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getInstallationDataDay(Condition condition) {
        return userAnalyseDAO.getInstallationDataDay(condition);
    }

    /**
     * 获得以周为间隔的新增用户数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getInstallationDataWeek(Condition condition) {
        return userAnalyseDAO.getInstallationDataWeek(SetConditionUtils.weekCondition(condition));
    }

    /**
     * 获得以月为间隔的新增用户数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getInstallationDataMonth(Condition condition) {
        return userAnalyseDAO.getInstallationDataMonth(SetConditionUtils.monthCondition(condition));
    }

    /**
     * 获得以天为间隔的活跃趋势数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getActiveTrendDataDay(Condition condition) {
        return userAnalyseDAO.getActiveTrendDataDay(condition);
    }

    /**
     * 获得以周为间隔的活跃趋势数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getActiveTrendDataWeek(Condition condition) {
        return userAnalyseDAO.getActiveTrendDataWeek(SetConditionUtils.weekCondition(condition));
    }

    /**
     * 获得以月为间隔的活跃趋势数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getActiveTrendDataMonth(Condition condition) {
        return userAnalyseDAO.getActiveTrendDataMonth(SetConditionUtils.monthCondition(condition));
    }

    /**
     * 获得以天为间隔的活跃用户构成数据，先查询新增用户和活跃用户，在做除法
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Double> getActiveFormDataDay(Condition condition) {
        List<Integer> installationList = getInstallationDataDay(condition);
        List<Integer> activeList = getActiveTrendDataDay(condition);

        return listDivision(installationList, activeList);
    }

    /**
     * 获得以周为间隔的活跃用户构成数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Double> getActiveFormDataWeek(Condition condition) {
        List<Integer> installationList = getInstallationDataWeek(condition);
        List<Integer> activeList = getActiveTrendDataWeek(condition);

        return listDivision(installationList, activeList);
    }

    /**
     * 获得以月为间隔的活跃用户构成数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Double> getActiveFormDataMonth(Condition condition) {
        List<Integer> installationList = getInstallationDataMonth(condition);
        List<Integer> activeList = getActiveTrendDataMonth(condition);

        return listDivision(installationList, activeList);
    }

    /**
     * 获得活跃粘度数据： DAU/最近7天活跃用户, DAU/最近30天活跃用户
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Double> getActiveViscosityData(int days, Condition condition) {
        //获得 start_date 和 end_date 的间隔
        int interval = DateUtils.getDateInterval(condition.getStart_date(), condition.getEnd_date()) + 1;
        //获取 beforeDate 和 endDate 之间每天的活跃用户
        List<Integer> activeUserList = userAnalyseDAO.getActiveTrendDataDay(condition);
        //正确间隔与实际获取到的元素个数之差，这一步是为了防止数据没有 start_date 前 day 天的数据
        int sub = interval - activeUserList.size();
        if(sub > 0) {   //如果没有前 day 天的数据，就用 0 填充
            List<Integer> zeroList = new ArrayList<>(sub);
            for(int i = 0; i < sub; i++) {
                zeroList.add(0);
            }
            activeUserList.addAll(0, zeroList);
        }

        //两个指针，使用滑动窗口法来计算每个窗口大小为 day 的sum, m 为指针头，n 为指针尾
        int m = 0, n = 0, sum = 0;
        //先把 start_date 前 day 天的数据和计算到
        while(n < days) {
            n++;
            sum += activeUserList.get(n);
        }

        //待返回数组
        int len = activeUserList.size();
        List<Double> viscosityList = new ArrayList<>(len - days);   //用已确定的长度建立List，避免扩容
        while(n < len) {
            double d = (double)activeUserList.get(n) / sum; //这一步是为了把 Integer 转成 double
            viscosityList.add(DecimalUtils.doubleDigits(d));
            sum += activeUserList.get(n);
            sum -= activeUserList.get(m);
            n++;
            m++;    //窗口向前移
        }

        return viscosityList;
    }

    /**
     * 获得周活跃率
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Double> getActiveRateWeekData(Condition condition) {
        List<Integer> activeWeekList = getActiveTrendDataWeek(condition);
        List<Integer> installationWeekList = getInstallationDataWeek(SetConditionUtils.maxBeforeCondition(condition));  //将起始日期初更改

        return calActiveRate(activeWeekList, installationWeekList);
    }

    /**
     * 获得月活跃率
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Double> getActiveRateMonthData(Condition condition) {
        List<Integer> activeMonthList = getActiveTrendDataMonth(condition);
        List<Integer> installationMonthList = getInstallationDataMonth(SetConditionUtils.maxBeforeCondition(condition));

        return calActiveRate(activeMonthList, installationMonthList);
    }

    /**
     * 获得以天为间隔的启动次数数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getLaunchDataDay(Condition condition) {
        return userAnalyseDAO.getLaunchDataDay(condition);
    }

    /**
     * 获得以周为间隔的启动次数数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getLaunchDataWeek(Condition condition) {
        return userAnalyseDAO.getLaunchDataWeek(SetConditionUtils.weekCondition(condition));
    }

    /**
     * 获得以月为间隔的启动次数数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public List<Integer> getLaunchDataMonth(Condition condition) {
        return userAnalyseDAO.getLaunchDataMonth(SetConditionUtils.monthCondition(condition));
    }

    /**
     * 获得新增用户的TOP版本分布数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public Map<String, List> getTopVersionDataInstallation(Condition condition) {
        List<Distribution> distributions = userAnalyseDAO.getDateTopVersionDataInstallation(condition);    //通过DAO层获得Distribution列表

        return getVersionMap(distributions);
    }

    /**
     * 获得活跃用户的TOP版本分布数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public Map<String, List> getTopVersionDataActive(Condition condition) {
        List<Distribution> distributions = userAnalyseDAO.getDateTopVersionDataActive(condition);

        return getVersionMap(distributions);
    }

    /**
     * 获得启动次数的TOP版本分布数据
     * @param condition 传入的条件
     * @return 某段时间的数据
     */
    @Override
    public Map<String, List> getTopVersionDataLaunch(Condition condition) {
        List<Distribution> distributions = userAnalyseDAO.getDateTopVersionDataLaunch(condition);

        return getVersionMap(distributions);
    }


    private List<String> getAllVersionName() {
        return userAnalyseDAO.getAllVersionName();
    }

    private List<Integer> getAllVersionDataInstallation(Condition condition) {
        return userAnalyseDAO.getAllVersionDataInstallation(condition);
    }

    private List<Integer> getAllVersionDataCalUser(Condition condition) {
        return userAnalyseDAO.getAllVersionDataInstallation(SetConditionUtils.maxBeforeCondition(condition));
    }

    private List<Integer> getAllVersionDataActive(Condition condition) {
        return userAnalyseDAO.getAllVersionDataActive(condition);
    }

    private List<Integer> getAllVersionDataLaunch(Condition condition) {
        return userAnalyseDAO.getAllVersionDataLaunch(condition);
    }


    /**
     * 将两个列表的元素进行相除，返回新的列表
     * @param list1 被除数列表
     * @param list2 除数列表
     * @return 相除之后的列表
     */
    private List<Double> listDivision(List<Integer> list1, List<Integer> list2) {
        List<Double> res = new ArrayList<>(list1.size());

        for(int i = 0; i < list1.size(); i++) {
            double d = (double) list1.get(i) / list2.get(i);
            res.add(DecimalUtils.doubleDigits(d));
        }

        return res;
    }

    /**
     * 返回指定区间内的天、周、月横轴
     * @param condition 传入的条件
     * @return 封装好的横轴
     */
    private Map<String, List> getDateCoordinate(Condition condition) {
        Map<String, List> map = new HashMap<>();
        //横轴
        map.put("dateDay", Arrays.asList(DateUtils.getDays(condition.getStart_date(), condition.getEnd_date())));
        map.put("dateWeek", Arrays.asList(DateUtils.getWeeks(condition.getStart_date(), condition.getEnd_date())));
        map.put("dateMonth", Arrays.asList(DateUtils.getMonths(condition.getStart_date(), condition.getEnd_date())));

        return map;
    }


    /**
     * 传入一个 Distribution List，返回一个 Map，map中包含了 "nameList"、“topK”，如：
     * List = {"name": V1, "num": 10},{"name": V1, "num": 15},{"name": V2, "num": 12},{"name": V2, "num": 8},{"name": V3, "num": 7},{"name": V3, "num": 6}
     * Map = {
     *            "nameList": [V1, V2, V3],
     *            "top1": [10, 15],
     *            "top2": [12, 8],
     *            "top3": [7, 6],
     *       }
     * @param distributionList 版本分布 List
     * @return Map
     */
    private Map<String, List> getVersionMap(List<Distribution> distributionList) {
        Map<String, List> map = new HashMap<>();    //map 中存储 “name"：List<name>，"topK"：List<Integer>，一共有 10 个K，即会存储10个List<Integer>

        List<String> nameList = new ArrayList<>();  //每个版本的名称
        List<Integer> numList = new ArrayList<>();  //每个名称对应的分布情况

        String name = "#intit";   //设置一个与任何版本名称都不相等的字符串
        int count = 0;  //用来记录到top几了

        for (Distribution distribution : distributionList) {
            if(!distribution.getName().equals(name)) {  //判断Distribution的名称是否与当前名称相等，如果不相等，则说明遍历到了下一个版本，需要把name添加到nameList中，并为该name新建一个numList
                name = distribution.getName();
                nameList.add(name);
                numList = new ArrayList<>();
                count++;
                map.put("top"+count, numList);
            }
            numList.add(distribution.getValue());
        }

        map.put("nameList", nameList);

        return map;
    }


    /**
     * 根据活跃用户列表和新增用户列表来计算活跃率，周活跃率 = 某一周的活跃用户 / 截至该周的总用户，月活跃率 = 某一月的活跃用户 / 截至该月的总用户。总用户 = 新增用户累加
     * @param activeList 活跃用户列表
     * @param installationList 新增用户列表
     * @return 活跃率列表
     */
    private List<Double> calActiveRate(List<Integer> activeList, List<Integer> installationList) {
        int n = 0;
        while(n < installationList.size() - activeList.size()) {
            n++;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += installationList.get(i);
        }

        List<Double> activeRateList = new ArrayList<>(activeList.size());

        for (Integer activeNumInt : activeList) {
            sum += installationList.get(n);
            double d = (double)activeNumInt / sum;
            activeRateList.add(DecimalUtils.doubleDigits(d));
            n++;
        }

        return activeRateList;
    }
}
