import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.utils.SetConditionUtils;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/20
 * @Time: 16:32
 * @Version: 1.0
 * @Description:
 */
public class SetConditionUtilTest {
    public static void main(String[] args) {
        String start = "2020-03-04";
        String end = "2020-06-20";

        Condition condition = Condition.instance(start, end);

        Condition weekCondition = SetConditionUtils.weekCondition(condition);

        Condition monthCondition = SetConditionUtils.monthCondition(condition);

        printCondition(condition);
        printCondition(weekCondition);
        printCondition(monthCondition);

        test();

    }

    static void test() {
        String start = "2020-03-04";
        String end = "2020-06-20";
        LocalDate d1 = new LocalDate(end);

        System.out.println(d1.getMonthOfYear() == new LocalDate().getMonthOfYear());
        System.out.println(d1.getWeekOfWeekyear() == new LocalDate().getWeekOfWeekyear());
        System.out.println(new LocalDate().getMonthOfYear());
    }

    static void printCondition(Condition condition) {
        System.out.println(condition.getStart_date() + ", " + condition.getEnd_date());
    }


}
