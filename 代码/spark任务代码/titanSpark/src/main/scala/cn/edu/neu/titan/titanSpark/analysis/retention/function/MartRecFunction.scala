package cn.edu.neu.titan.titanSpark.analysis.retention.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/14
 * @Time: 12:22
 * @Version: 1.0
 * @Description: 计算活跃用户留存月数
 */
object MartRecFunction {
    def insertData(): Unit = {
        val tbSource = Constants.HIVE_TABLE_DWS_APL_UCA_REC
        val tbTarget = Constants.HIVE_TABLE_ADS_APL_MART_REC
        val vwCurrentMonth = "vwCurrentMonth"
        val vwRes = "vwRes"

        //1.选出本月活跃过的用户
        val currentMonth_sql: String = s"SELECT distinct guid, version, channel " +
                s"FROM $tbSource " +
                s"WHERE dt = '$currentDate' AND trunc(endDate, 'month') = '$currentMonth' OR endDate = '${Constants.MAX_DATE}'"

        //2. 选出 currentMonth - startMonth < 10 的历史数据：(guid, version, channel, startDate, endDate)
        val before_sql: String = "SELECT before.guid, before.version version, before.channel channel, cast(trunc(before.startDate, 'month') as string) startDate, cast(trunc(before.endDate, 'month') as string) endDate " +
                s"FROM $tbSource before JOIN $vwCurrentMonth today ON before.guid = today.guid and before.version = today.version and before.channel = today.channel " +
                s"WHERE before.dt = '$currentDate' AND (months_between('$currentDate', before.startDate) between 0 and 10)" //这里等有数据之后需要改回1


        spark.sql(currentMonth_sql).createOrReplaceTempView(vwCurrentMonth)
        val before_df = spark.sql(before_sql)

        import spark.implicits._
        val currentMt = currentMonth
        val maxMt = DateUtils.getFirstDayOfMonth(Constants.MAX_DATE) //在这里使用两个局部变量是因为把 package 里的变量放到 flatMap 里会报错
        //3. 把 startDate——endDate 之间的月份选出来，在做去重处理
        val beforeDistinctDf = before_df.rdd.flatMap(row => {
            val guid = row.getAs[String]("guid")
            val version = row.getAs[String]("version")
            val channel = row.getAs[String]("channel")
            val startDate = row.getAs[String]("startDate")
            var endDate = row.getAs[String]("endDate")
            if (endDate.equals(maxMt)) {
                endDate = currentMt
            }

            val diff = DateUtils.monthsBetween(startDate, endDate)
            for (i <- 0 to diff) yield {
                val dt = DateUtils.getMonthBefore(endDate, diff - i) //endDate 的前 (diff-i) 天，即 startDate 的后 i 天
                (dt, guid, version, channel)
            }

        }).toDF("dt", "guid", "version", "channel")
                .filter(row => !row.getAs[String]("dt").equals(currentMt)) //因为本月的数据留存月数是 0，所以要过滤掉
                .distinct() /*对 df 进行去重，是为了防止有类似这样的数据：
                                     对于同一guid,版本，渠道，在连续活跃区间中有：05-28~06-03, 06-05~06-15, 06-20~06-27, 06-29~07-01
                                    那么转成 month 之后就是：05-01~06-01，06-01~06-01，06-01，06-01，06-01~07-01
                                    此时在df中的结果就是: (guid, version, channel, 05-01)，(guid, version, channel, 06-01),(guid, version, channel, 06-01),(guid, version, channel, 06-01),(guid, version, channel, 07-01)
                                    所以需要去重，生成 (guid, version, channel, 05-01)，(guid, version, channel, 06-01),(guid, version, channel, 07-01)*/

        beforeDistinctDf.createOrReplaceTempView(vwRes)

        //4. 计算留存月数，以及其对应的人数
        val sql2: String = s"INSERT INTO $tbTarget " +
                s"SELECT dt, version, channel, cast(months_between('$currentMonth', dt) as int) art_months, count(1) art_count " +
                s"FROM $vwRes " +
                "GROUP BY dt, version, channel, art_months "

        spark.sql(sql2)
    }

    def main(args: Array[String]): Unit = {
        if(DateUtils.isFirstDayOfMonth(today)) {
            insertData()
        }
    }
}
