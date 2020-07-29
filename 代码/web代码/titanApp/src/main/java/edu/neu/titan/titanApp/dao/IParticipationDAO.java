package edu.neu.titan.titanApp.dao;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;
import edu.neu.titan.titanApp.dao.sql.ParticipationTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/17
 * @Time: 17:19
 * @Version: 1.0
 * @Description: 访问用户参与度模块相关数据库的接口
 */
public interface IParticipationDAO {

    /**
     * 获得用户参与度模块中使用时长子功能中的单次使用时长分布
     *
     * @param condition
     * @return
     */
    List<Integer> getDurationDataSingle(Condition condition);

    /**
     * 获得用户参与度模块中使用时长子功能中的日使用时长分布
     *
     * @param condition
     * @return
     */
    List<Integer> getDurationDataDay(Condition condition);

    /**
     * 获得用户参与度模块中使用频率子功能中的日启动次数分布
     *
     * @param condition
     * @return
     */
    List<Integer> getFrequencyDataDay(Condition condition);

    /**
     * 获得用户参与度模块中使用时长子功能中的周启动次数分布
     *
     * @param condition
     * @return
     */
    List<Integer> getFrequencyDataWeek(Condition condition);

    /**
     * 获得用户参与度模块中使用时长子功能中的月启动次数分布
     *
     * @param condition
     * @return
     */
    List<Integer> getFrequencyDataMonth(Condition condition);

    /**
     * 获得用户参与度模块中访问页面子功能中的访问页面分布
     *
     * @param condition
     * @return
     */
    List<Integer> getPageData(Condition condition);

    /**
     * 获得用户参与度模块中使用间隔子功能中的使用间隔分布
     *
     * @param condition
     * @return
     */
    List<Integer> getIntervalData(Condition condition);

    /**
     * 获得用户参与度模块中使用时长子功能中的使用时长范围
     *
     * @return
     */
    List<String> getDurationRange();

    /**
     * 获得用户参与度模块中使用频率子功能中的日启动次数范围
     *
     * @return
     */
    List<String> getFrequencyDayRange();

    /**
     * 获得用户参与度模块中使用频率子功能中的周启动次数分布
     *
     * @return
     */
    List<String> getFrequencyWeekRange();

    /**
     * 获得用户参与度模块中使用频率子功能中的月启动次数分布
     *
     * @return
     */
    List<String> getFrequencyMonthRange();

    /**
     * 获得用户参与度模块中访问页面子功能中的访问页面范围
     *
     * @return
     */

    List<String> getPageRange();

    /**
     * 获得用户参与度模块中使用间隔子功能中的使用间隔范围
     *
     * @return
     */
    List<String> getIntervalRange();

}
