package cn.edu.neu.titan.titanSpark.analysis.retention.function


import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/10
 * @Time: 16:36
 * @Version: 1.0
 * @Description:
 */
object DartRecFunction {
    def insertData(): Unit = {
        val tbSource = Constants.HIVE_TABLE_DWS_APL_UCA_REC
        val tbTarget = Constants.HIVE_TABLE_ADS_APL_DART_REC
        val vwToday = "vwToday"
        val vwRes = "vwRes"

        //选出当天活跃用户的 guid，version，channel
        val today_sql: String = s"SELECT distinct guid, version, channel " +
                s"FROM $tbSource " +
                s"WHERE dt = '$currentDate' and  endDate= '${Constants.MAX_DATE}'"

        //选出 currentDate - startDate < 31 的历史数据：(version, channel, startDate, endDate)
        val before_sql: String = "SELECT before.version version, before.channel channel, before.startDate startDate, before.endDate endDate " +
                s"FROM $tbSource before JOIN $vwToday today ON before.guid = today.guid and before.version = today.version and before.channel = today.channel " +
                s"WHERE before.dt = '$currentDate' AND (datediff('$currentDate', before.startDate) between 1 and 31)"

        spark.sql(today_sql).createOrReplaceTempView(vwToday)

        val df = spark.sql(before_sql)

        val currentDt = currentDate
        val maxDt = Constants.MAX_DATE
        import spark.implicits._
        df.rdd.flatMap(row => {
            val version = row.getAs[String]("version")
            val channel = row.getAs[String]("channel")
            val startDate = row.getAs[String]("startDate")
            var endDate = row.getAs[String]("endDate")
            //如果截至日期是9999，那么为了下面的计算，需要改成 currentDt
            if(endDate.equals(maxDt)) {
                endDate = currentDt
            }

            val diff = DateUtils.daysBetween(startDate, endDate)    //更新在 startDate 和 endDate 之间的留存天数

            for(i <- 0 to diff) yield {
                val dt = DateUtils.getDayBefore(endDate, diff - i)      //endDate 的前 (diff-i) 天，即 startDate 的后 i 天
                val art_days = DateUtils.daysBetween(dt, currentDt)     //留存天数就是 currentDt 和 dt 的间隔天数
                (dt, version, channel, art_days)
            }

        }).toDF("dt", "version", "channel", "art_days")
                .filter(row => row.getAs[Int]("art_days") > 0)  //如果因为endDate=9999而被替换成currentDate，那么art_days=0，所以要过滤掉
                .createOrReplaceTempView(vwRes)

        spark.sql(s"select * from $vwRes").show(1000)


        val sql2: String = s"INSERT INTO $tbTarget " +
                "SELECT dt, version, channel, art_days, count(1) art_count " +
                s"FROM $vwRes " +
                "GROUP BY dt, version, channel, art_days "

        println(sql2)
        spark.sql(sql2)
    }

    def main(args: Array[String]): Unit = {
        insertData()
    }
}
