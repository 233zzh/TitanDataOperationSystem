package cn.edu.neu.titan.titanSpark.analysis.retention.function

import java.util.Properties

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/10
 * @Time: 18:18
 * @Version: 1.0
 * @Description:
 */
object WartRecFunction {
    def insertData(): Unit = {
        val tbSource = Constants.HIVE_TABLE_DWS_APL_UCA_REC
        val tbTarget = Constants.HIVE_TABLE_ADS_APL_WART_REC
        val vwCurrentWeek = "vwCurrentWeek"
        val vwRes = "vwRes"

        //自定义udf，计算两个日期的间隔周数，在 mnrt 中没有自定义函数，是因为官方提供了计算月间隔的函数，而没有提供计算周间隔的函数
        val weeksBetween = "weeksBetween"
        spark.udf.register(weeksBetween, (startDate: String, endDate: String) => DateUtils.weeksBetween(startDate, endDate))

        //1.选出本周活跃过的用户
        val currentWeek_sql: String = s"SELECT distinct guid, version, channel " +
                s"FROM $tbSource " +
                s"WHERE dt = '$currentDate' AND trunc(endDate, 'week') = '$currentWeek' OR endDate = '${Constants.MAX_DATE}'"


        //2. 选出 currentWeek - startWeek < 10 的历史数据：(guid, version, channel, startDate, endDate)
        val before_sql: String = "SELECT before.guid, before.version version, before.channel channel, cast(trunc(before.startDate, 'week') as string) startDate, cast(trunc(before.endDate, 'week') as string) endDate " +
                s"FROM $tbSource before JOIN $vwCurrentWeek today ON before.guid = today.guid and before.version = today.version and before.channel = today.channel " +
                s"WHERE before.dt = '$currentDate' AND ($weeksBetween(before.startDate, '$currentDate') between 0 and 10)" //这里等有数据之后需要改回1


        spark.sql(currentWeek_sql).createOrReplaceTempView(vwCurrentWeek)
        val before_df = spark.sql(before_sql)

        import spark.implicits._
        val currentWt = currentWeek
        val maxWt = DateUtils.getFirstDayOfWeek(Constants.MAX_DATE)    //在这里使用两个局部变量是因为把 package 里的变量放到 flatMap 里会报错
        //3. 把 startDate——endDate 之间的月份选出来，在做去重处理
        val beforeDistinctDf =  before_df.rdd.flatMap(row => {
            val guid = row.getAs[String]("guid")
            val version = row.getAs[String]("version")
            val channel = row.getAs[String]("channel")
            val startDate = row.getAs[String]("startDate")
            var endDate = row.getAs[String]("endDate")
            if(endDate.equals(maxWt)) {
                endDate = currentWt
            }

            val diff = DateUtils.weeksBetween(startDate, endDate)
            for(i <- 0 to diff) yield {
                val dt = DateUtils.getWeekBefore(endDate, diff - i)      //endDate 的前 (diff-i) 天，即 startDate 的后 i 天
                (dt, guid, version, channel)
            }

        }).toDF("dt", "guid", "version", "channel")
                .filter(row => !row.getAs[String]("dt").equals(currentWt))  //因为本月的数据留存月数是 0，所以要过滤掉
                .distinct()         //去重的原因与MartRecFunction中的一样

        beforeDistinctDf.createOrReplaceTempView(vwRes)

        //4. 计算留存月数，以及其对应的人数
        val sql2: String = s"INSERT INTO $tbTarget " +
                s"SELECT dt, version, channel, cast($weeksBetween(dt, '$currentWeek') as int) art_weeks, count(1) art_count " +
                s"FROM $vwRes " +
                "GROUP BY dt, version, channel, art_weeks "

        spark.sql(sql2)
    }

    def main(args: Array[String]): Unit = {
        if(DateUtils.isFirstDayOfWeek(today)) {
            insertData()
        }
    }
}
