package edu.neu.titan.titanApp.service.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.dao.IParticipationDAO;
import edu.neu.titan.titanApp.service.IParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 17:37
 * @Version: 1.0
 * @Description: 接口实现类，用于实现用户参与度部分具体业务逻辑
 */
@Service
public class ParticipationServiceImpl implements IParticipationService {

    // 相关DAO字段
    private IParticipationDAO participationDAO;

    /**
     * @param participationDAO
     */
    @Autowired
    public void setParticipationDAO(IParticipationDAO participationDAO) {
        this.participationDAO = participationDAO;
    }

    /**
     * 获得用户参与度模块中使用时长子功能中的单次使用时长分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getDurationDataSingle(Condition condition) {
        return participationDAO.getDurationDataSingle(condition);
    }

    /**
     * 获得用户参与度模块中使用时长子功能中的日使用时长分布
     *
     * @param condition
     * @return
     */
    @Override
    public List<Integer> getDurationDataDay(Condition condition) {
        return participationDAO.getDurationDataDay(condition);
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的日启动次数分布
     *
     * @param condition
     * @return
     */
    @Override
    public Integer[] getFrequencyDataDay(Condition condition) {
        return participationDAO.getFrequencyDataDay(condition).toArray(new Integer[0]);
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的周启动次数分布
     *
     * @param condition
     * @return
     */
    @Override
    public Integer[] getFrequencyDataWeek(Condition condition) {
        return participationDAO.getFrequencyDataWeek(condition).toArray(new Integer[0]);
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的月启动次数分布
     *
     * @param condition
     * @return
     */
    @Override
    public Integer[] getFrequencyDataMonth(Condition condition) {
        return participationDAO.getFrequencyDataMonth(condition).toArray(new Integer[0]);
    }

    /**
     * 获得用户参与度模块中访问页面子功能中的访问页面分布
     *
     * @param condition
     * @return
     */
    @Override
    public Integer[] getPageData(Condition condition) {
        return participationDAO.getPageData(condition).toArray(new Integer[0]);
    }

    /**
     * 获得用户参与度模块中使用间隔子功能中的使用间隔分布
     *
     * @param condition
     * @return
     */
    @Override
    public Integer[] getIntervalData(Condition condition) {
        return participationDAO.getIntervalData(condition).toArray(new Integer[0]);
    }

    /**
     * 获得用户参与度模块中使用时长子功能中的使用时长范围
     *
     * @return
     */
    public List<String> getDurationRange() {
        return participationDAO.getDurationRange();
    }


    /**
     * 获得用户参与度模块中使用频率子功能中的日启动次数范围
     *
     * @return
     */
    public List<String> getFrequencyDayRange() {
        return participationDAO.getFrequencyDayRange();
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的周启动次数范围
     *
     * @return
     */
    public List<String> getFrequencyWeekRange() {
        return participationDAO.getFrequencyWeekRange();
    }

    /**
     * 获得用户参与度模块中使用频率子功能中的月启动次数范围
     *
     * @return
     */
    public List<String> getFrequencyMonthRange() {
        return participationDAO.getFrequencyMonthRange();
    }

    /**
     * 获得用户参与度模块中访问页面子功能中的访问页面范围
     *
     * @return
     */

    public List<String> getPageRange() {
        return participationDAO.getPageRange();
    }

    /**
     * 获得用户参与度模块中使用间隔子功能中的使用间隔范围
     *
     * @return
     */
    public List<String> getIntervalRange() {
        return participationDAO.getIntervalRange();
    }
}
