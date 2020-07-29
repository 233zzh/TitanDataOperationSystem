package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.analysis._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/10 
 * @Time: 18:20
 * @Version: 1.0
 * @Description: Description 
 */
object UCACountFunction {

  def count(): Unit = {

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWS_APL_USR_UCA
//    val tbSource = "source"
    val tbTarget = Constants.HIVE_TABLE_ADS_APL_UCA

//    spark.read.parquet("file:///D:/data/mockData/Usr_UCA/*.parquet").createOrReplaceTempView(tbSource)


    // sql 语句
    val sql_insert =  s"insert into table $tbTarget partition(dt='$currentDate') " +
      s"select continuous_category, count(*) count from $tbSource where dt='$currentDate' and channel='' and version='' group by continuous_category"

    spark.sql(sql_insert)

  }

  def main(args: Array[String]): Unit = {
    count()
  }

}
