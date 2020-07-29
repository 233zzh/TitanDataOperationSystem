package edu.neu.titan.titanApp.service.impl;

import edu.neu.titan.titanApp.common.beans.*;
import edu.neu.titan.titanApp.common.constant.Constants;
import edu.neu.titan.titanApp.common.utils.ArrayUtils;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.dao.IRetentionDAO;
import edu.neu.titan.titanApp.dao.IUserAnalyseDAO;
import edu.neu.titan.titanApp.service.IRetentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: IRetentionService 接口实现类，用于实现存留分析部分具体业务逻辑
 */
@Service
public class RetentionServiceImpl implements IRetentionService {

    // 相关DAO字段
    private IRetentionDAO retentionDAO;

    private IUserAnalyseDAO userAnalysisDAO;

    @Autowired
    public RetentionServiceImpl(IRetentionDAO retentionDAO, IUserAnalyseDAO userAnalysisDAO) {
        this.retentionDAO = retentionDAO;
        this.userAnalysisDAO = userAnalysisDAO;
    }

    @Override
    public Integer[][] getRetentionDataIncreaseDay(Condition condition) {
        // 获取数据
        List<RetentionDataDay> list = retentionDAO.getRetentionDataIncreaseDay(condition);
        // 转化
        return ArrayUtils.transform(Integer.class, list);
    }

    @Override
    public Integer[][] getRetentionDataIncreaseWeek(Condition condition) {
        // 获取数据
        List<RetentionDataWeek> list = retentionDAO.getRetentionDataIncreaseWeek(condition);
        // 转化
        return ArrayUtils.transform(Integer.class, list);
    }

    @Override
    public Integer[][] getRetentionDataIncreaseMonth(Condition condition) {
        // 获取数据
        List<RetentionDataMonth> list = retentionDAO.getRetentionDataIncreaseMonth(condition);
        // 转化
        return ArrayUtils.transform(Integer.class, list);
    }

    @Override
    public Integer[][] getRetentionDataActiveDay(Condition condition) {
        // 获取数据
        List<RetentionDataDay> list = retentionDAO.getRetentionDataActiveDay(condition);
        // 转化
        return ArrayUtils.transform(Integer.class, list);
    }

    @Override
    public Integer[][] getRetentionDataActiveWeek(Condition condition) {
        // 获取数据
        List<RetentionDataWeek> list = retentionDAO.getRetentionDataActiveWeek(condition);
        // 转化
        return ArrayUtils.transform(Integer.class, list);
    }

    @Override
    public Integer[][] getRetentionDataActiveMonth(Condition condition) {
        // 获取数据
        List<RetentionDataMonth> list = retentionDAO.getRetentionDataActiveMonth(condition);
        // 转化
        return ArrayUtils.transform(Integer.class, list);
    }

    @Override
    public Pair<String[], Integer[][]> getRetentionDataFreshness() {
        // 获取默认时间范围
        String[] dates = getDefaultRetentionDays();
        // 判断数组是否为空
        if (dates.length==0) return null;
        // 构建condition实例
        Condition condition_increase = Condition.instance(DateUtils.minusDays(dates[0],Constants.DEFAULT_DAYS_BEFORE), dates[dates.length - 1]);
        Condition condition_active = Condition.instance(dates[0], dates[dates.length - 1]);
        // 查询新增人数
        Integer[] increase_data = userAnalysisDAO.getInstallationDataDay(condition_increase).toArray(new Integer[0]);
        // 错位堆叠转化
        Integer[][] stackShift = ArrayUtils.stackShift(Integer.class, increase_data, Constants.DEFAULT_DAYS_BEFORE + 1, 1);
        // 查询活跃人数
        Integer[] active_data = userAnalysisDAO.getActiveTrendDataDay(condition_active).toArray(new Integer[0]);
        // 计算剩余用户成分
        Integer[] sumByRow = ArrayUtils.sumByRow(stackShift);      // 求和
        Integer[] rest = ArrayUtils.minus(active_data, sumByRow);   // 计算差值
        // 转置并堆叠
        Integer[][] transpose = ArrayUtils.transpose(Integer.class, stackShift);
        assert transpose != null;
        Integer[][] data = ArrayUtils.stackByRow(Integer.class, transpose, rest);
        // 返回结果
        assert data != null;
        return Pair.of(dates, data);
    }

    @Override
    public Pair<String[], Integer[][]> getRetentionDataActivity() {
        // 获取默认时间范围
        String[] dates = getDefaultRetentionDays();
        // 判断数组是否为空
        if (dates.length==0) return null;
        // 构建condition实例
        Condition condition = Condition.instance(dates[0], dates[dates.length - 1]);
        // 获取数据
        List<RetentionActivityData> list = retentionDAO.getActivityData(condition);
        //        // 转化并转置
        Integer[][] data = ArrayUtils.transformAndTranspose(Integer.class, list);
        // 横轴/数据 对
        return Pair.of(dates, data);
    }

    @Override
    public Double[] getOneDayAfterRetentionRate(Condition condition) {
        return retentionDAO.getOneDayAfterRetentionRate(condition).toArray(new Double[0]);
    }

    /**
     * 获得默认的日期范围
     * @return 日期字符串数组
     */
    public String[] getDefaultRetentionDays() {
        return DateUtils.getDaysFromToday(Constants.DEFAULT_RETENTION_DAY_NUM);
    }
}
