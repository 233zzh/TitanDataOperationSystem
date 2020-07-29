package cn.edu.neu.titan.titanSpark.analysis.flow.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.analysis._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/9 
 * @Time: 9:30
 * @Version: 1.0
 * @Description: 计算单次启动页面访问数在指定范围内的启动数，通过渠道版本交叉筛选
 */
object PageCountFunction {

  def pageCount() = {

    // 源表和目标表
    val tbSource = Constants.HIVE_TABLE_DWS_FLW_AGG_S
    val tbTarget = Constants.HIVE_TABLE_ADS_FLW_PAGE_CUBE

    // sq 语句
    val sql_insert = s"insert into table $tbTarget partition(dt='$currentDate') " +
      "select version, " +
      "channel, " +
      "pv_num_range, " +
      s"count(*) pv_count from $tbSource where dt='$currentDate' " +
      "group by version, channel, pv_num_range " +
      "grouping sets((pv_num_range),(pv_num_range,version),(pv_num_range,channel),(pv_num_range,version,channel))"

    spark.sql(sql_insert).show(100)
  }

  def main(args: Array[String]): Unit = {
    pageCount()
  }

}
