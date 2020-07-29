package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.analysis.apl.udf.{IntervalUDTF, StringConcatUDAF}
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.utils.{DateUtils, RangeUtils}
import org.apache.spark.sql.functions

import scala.collection.mutable.ArrayBuffer

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/10 
 * @Time: 18:58
 * @Version: 1.0
 * @Description: 根据用户进行间隔天数统计
 */
object IntervalUserAggFunction {

  def agg() = {

    import spark.implicits._
    // 源表和目标表
//    val tbSource = "rec"
//    val tbTarget = "itv"
    val tbTemp = "strings"
    val tbSource = Constants.HIVE_TABLE_DWS_APL_UCA_REC
    val tbTarget = Constants.HIVE_TABLE_DWS_APL_ITV_AGU

    // 一些常数
    val monthAgo = DateUtils.getDayBefore(currentDate, 30)
    val maxDate = Constants.MAX_DATE
    val itvRange = ConfigurationManager.config.getString(Constants.RANGE_INTERVAL).split(",")
    val UDTFPath = "cn.edu.neu.titan.titanSpark.analysis.apl.udf.IntervalUDTF"

    // 注册udaf和udtf
    val stringConcat = "strConcat"
    val intervalCount = "itvCount"
    val intervalRange = "itvRange"

    val sql_register_udtf = s"CREATE TEMPORARY FUNCTION $intervalCount as '$UDTFPath'"
    val intervalRangeFunc = (num:Int) => RangeUtils.getRange(num, itvRange)

    spark.udf.register(stringConcat, functions.udaf(StringConcatUDAF))
    spark.sql(sql_register_udtf)
    spark.udf.register(intervalRange,intervalRangeFunc)

    // sql语句
    val sql_select = s"select guid, version, channel," +
      s"$stringConcat(CONCAT_WS('~',startDate,endDate)) itvs from" +
      " (select guid, version, channel, " +
      s"case when startDate<'$monthAgo' then '$monthAgo' else startDate end as startDate," +
      s"case when endDate='$maxDate' then '$currentDate' else endDate end as endDate " +
      s"from $tbSource where dt='$currentDate' and endDate>='$monthAgo' )" +
      s"group by guid, version, channel"

//    val sql_insert = s"insert into table $tbTarget partition(dt='$currentDate') " +
//      s"select guid, version, channel, interval_days, $intervalRange(interval_days) interval_range, interval_num from (select guid, version, channel, $intervalCount(itvs) from $tbTemp) "
    val sql_insert = s"insert into table $tbTarget partition(dt='$currentDate') " +
      s"select guid, version, channel, interval_days, $intervalRange(interval_days) interval_range, interval_num from $tbTemp "

//    //数据源
//    val src = spark.read.parquet("file:///D:/data/mockData/UCARec02/*.parquet")
//    src.createOrReplaceTempView(tbSource)

    val agg = spark.sql(sql_select)

    val dateDiff = (start: String, end: String) => DateUtils.daysBetween(start, end)

    val resRdd = agg.rdd.flatMap(row => {
      val guid = row.getString(0)
      val version = row.getString(1)
      val channel = row.getString(2)
      val itvs = row.getString(3)

      val zeroItvNum = itvs.split(",").map(_.split("~")).filter(_.length>1).map(days => dateDiff(days(0), days(1))).sum
      val zeros = UserInterval(guid, version, channel, 0, zeroItvNum)
      val others = itvs.split("~").map(_.split(",")).filter(_.length>1).map(days => dateDiff(days(0), days(1))-1).map((_, 1))
        .groupBy(_._1).map(elem => {
        val sum = elem._2.map(_._2).sum
        UserInterval(guid, version, channel, elem._1, sum)
      })
      ArrayBuffer(zeros) ++ others
    })

    resRdd.toDF().createOrReplaceTempView(tbTemp)
//    agg.createOrReplaceTempView(tbTemp)
    spark.sql(sql_insert)

  }

  case class UserInterval(val guid: String,
                          val version: String,
                          val channel: String,
                          val interval_days: Int,
                          val interval_num: Int)

  def main(args: Array[String]): Unit = {
    agg()
  }

}
