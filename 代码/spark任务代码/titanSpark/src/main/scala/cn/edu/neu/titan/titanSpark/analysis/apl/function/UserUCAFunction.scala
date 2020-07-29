package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/10 
 * @Time: 17:28
 * @Version: 1.0
 * @Description: Description 
 */
object UserUCAFunction {

  def count() = {

    // 源表和目标表

    val tbSource = Constants.HIVE_TABLE_DWS_APL_UCA_REC
//    val tbSource = "source"
    val tbTarget = Constants.HIVE_TABLE_DWS_APL_USR_UCA

    // 一些常数
    val maxDate = Constants.MAX_DATE

    // 自定义udf
    val dateDiff = "dateDiff"
    val dayRange = "dayRange"
    val dateDiffFunc:(String,String)=>Int = (start: String, end:String) => DateUtils.daysBetween(start, end)

    val rangeUCA = ConfigurationManager.config.getInt(Constants.RANGE_UCA)

    val dayRangeFunc = (num: Int) => if ( num > rangeUCA ) rangeUCA.toString+"+" else num.toString

    spark.udf.register(dateDiff, dateDiffFunc)
    spark.udf.register(dayRange, dayRangeFunc)

//    spark.read.parquet("file:///D:/data/mockData/UCARec02/*.parquet").createOrReplaceTempView(tbSource)

    // sql语句
    val sql_insert_uca = s"insert into table $tbTarget partition(dt='$currentDate') " +
      s"select *, $dayRange(continuous_num) continuous_category from (select guid, version, channel, $dateDiff(startDate, '$currentDate')+1 continuous_num from $tbSource where dt='$currentDate' and endDate='$maxDate') "

    // 插入结果
      spark.sql(sql_insert_uca)
//      res.show(1000)
//      res.write.parquet("file:///D:/data/mockData/Usr_UCA")


  }

  def main(args: Array[String]): Unit = {
    count()
  }

}
