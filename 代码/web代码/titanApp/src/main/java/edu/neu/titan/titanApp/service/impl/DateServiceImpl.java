package edu.neu.titan.titanApp.service.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.constant.Constants;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import edu.neu.titan.titanApp.service.IDateService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/19
 * @Time: 13:54
 * @Version: 1.0
 * @Description: IDateService 实现类
 */
@Service
public class DateServiceImpl implements IDateService {

    @Override
    public String[] getDays(Condition condition) {
        return DateUtils.getDays(condition.getStart_date(), condition.getEnd_date());
    }

    @Override
    public String[] getWeeks(Condition condition) {
        return DateUtils.getWeeks(condition.getStart_date(), condition.getEnd_date());
    }

    @Override
    public String[] getMonths(Condition condition) {
        return DateUtils.getMonths(condition.getStart_date(), condition.getEnd_date());
    }

}
