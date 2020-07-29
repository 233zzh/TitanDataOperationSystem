import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.RangeUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/10 
 * @Time: 10:28
 * @Version: 1.0
 * @Description:
 */
object RangeTest {
    def main(args: Array[String]): Unit = {
        val pageRange = ConfigurationManager.config.getString(Constants.RANGE_PAGE).split(",")
        val page = 10
        println(RangeUtils.getRange(page, pageRange))
    }

}
