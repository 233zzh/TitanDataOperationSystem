package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.analysis._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/11 
 * @Time: 17:49
 * @Version: 1.0
 * @Description: 间隔天数统计
 */
object IntervalCountFunction {

  def count()= {

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWS_APL_ITV_AGU
    val tbTarget = Constants.HIVE_TABLE_ADS_APL_USR_ITV

    // sql 语句
    val sql_insert = s"insert into table $tbTarget partition(dt='$currentDate') " +
      "select case when version='' then NULL else version end as version," +
      s"case when channel='' then NULL else channel end as channel, " +
      "interval_range itv_days ," +
      s"sum(interval_num) itv_num from $tbSource where dt='$currentDate' group by version,channel, interval_range"

    // 执行语句
    spark.sql(sql_insert)

  }

  def main(args: Array[String]): Unit = {
    count()
  }

}
