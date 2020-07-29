package edu.neu.titan.titanApp.common.beans;

import edu.neu.titan.titanApp.common.constant.Constants;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 封装前端传递的条件数据的VO类
 */
public class Condition {

    private String start_date;  // 开始日期
    private String end_date;    // 结束日期
    private int version_id;     // 版本id（当值为-1时表示全部版本）
    private int channel_id;     // 渠道id（当值为-1时表示全部渠道）

    // 无参构造器
    public Condition() { }

    // get、set方法
    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }


    /**
     * 显式构建Condition 实例
     * @param start_date 开始日期
     * @param end_date 结束日期
     * @return condition实例
     */
    public static Condition instance(String start_date, String end_date) {
        /// 显式构建Condition 实例
        Condition condition = new Condition();
        condition.setStart_date(start_date);
        condition.setEnd_date(end_date);
        condition.setChannel_id(Constants.CONDITION_INVALID_VALUE);
        condition.setVersion_id(Constants.CONDITION_INVALID_VALUE);
        return condition;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", version_id=" + version_id +
                ", channel_id=" + channel_id +
                '}';
    }
}
