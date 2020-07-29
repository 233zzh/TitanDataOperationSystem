import cn.edu.neu.titan.titanSpark.common.utils.DateUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/11 
 * @Time: 9:13
 * @Version: 1.0
 * @Description:
 */
object DateUtilsTest {
    def main(args: Array[String]): Unit = {
        val current = "2020-07-11"
        val before = "2020-07-10"

        println(DateUtils.getDayBefore(current, 3))
        println(DateUtils.yesterday)
        println(DateUtils.getFirstDayOfWeek(current))
        println(DateUtils.getFirstDayOfMonth(current))

    }

}
