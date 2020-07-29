import edu.neu.titan.titanApp.common.utils.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/20
 * @Time: 14:03
 * @Version: 1.0
 * @Description: 查看 DateUtils 中方法的效果
 */
public class DateUtilsTest {
    public static void main(String[] args) {
//        String start = "2020-03-04";
//        String end = "2020-05-20";
//
//        String startWeek = DateUtils.convertWeekDateStart(start);
//        String endWeek = DateUtils.convertWeekDateEnd(end);
//
//        System.out.println(startWeek);
//        System.out.println(endWeek);
//
//        String startMonth = DateUtils.convertMonthDateStart(start);
//        String endMonth = DateUtils.convertMonthDateEnd(end);
//
//        System.out.println(startMonth);
//        System.out.println(endMonth);


        System.out.println(Arrays.asList(DateUtils.getDateFromToday(30)));
    }
}
