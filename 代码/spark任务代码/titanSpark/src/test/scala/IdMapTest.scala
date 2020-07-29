
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.{DateUtils, IdMapUtils, RangeUtils}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/6 
 * @Time: 17:08
 * @Version: 1.0
 * @Description:
 */
/*
object IdMapTest {
    def main(args: Array[String]): Unit = {
        val tbDAUSource = Constants.HIVE_TABLE_DWS_APL_DAU_REC
        val sql_users = "select " +
          "guid, " +
          "case when version is NULL then '' else version end as version," +
          s"case when channel is NULL then '' else channel end as channel from $tbDAUSource  " +
          s"where dt='2020-07-01' " +
          "group by guid,version,channel " +
          "grouping sets((guid),(guid,version),(guid,channel),(guid,channel,version))"

        println(sql_users)
    }
}
*/
