package cn.edu.neu.titan.titanSpark.analysis.flow.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.RangeUtils

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/7/9
 * @Time: 16:35
 * @Version: 1.0
 * @Description: Description
 */
object AggUsrCubeFunction {
  def main(args: Array[String]): Unit = {
    val startCnt = "startRange" //启动次数
    val timeCnt = "timeRange" //访问总时长
    val pageCnt = "pageRange" //页面访问量

//    val currentDate: String = "2020-07-01"

    val startRange: Array[String] = ConfigurationManager.config.getString(Constants.RANGE_START_DAY).split(",")
    val timeRange: Array[String] = ConfigurationManager.config.getString(Constants.RANGE_DURATION_SINGLE).split(",")
    val pageRange: Array[String] = ConfigurationManager.config.getString(Constants.RANGE_PAGE).split(",")

    spark.udf.register(startCnt, (cnt: Int) => RangeUtils.getRange(cnt, startRange))
    spark.udf.register(timeCnt, (cnt: Int) => RangeUtils.getRange(cnt, timeRange))
    spark.udf.register(pageCnt, (cnt: Int) => RangeUtils.getRange(cnt, pageRange))

    val sql1: String = s"SELECT guid, version, channel, sum(view_num) view_num, sum(duration) view_time, sum(pv_num) pv_num " +
      s"FROM titan.dws_flw_agg_u " +
      s"WHERE dt = '$currentDate' " +
      s"GROUP BY guid, version, channel " +
      s"GROUPING SETS(guid, (guid, version), (guid, channel), (guid, version, channel))"

    val sql2: String = "INSERT INTO titan.dws_agg_usr_cube " +
      s"PARTITION(dt = '$currentDate') " +
      "SELECT guid, version, channel, " +
//      "case when version is NULL then '' else version end as version, " +
//      "case when channel is NULL then '' else channel end as channel, " +
      "view_num, view_time, pv_num, " +
      s"$startCnt(view_num) start_num_range, " +
      s"$timeCnt(view_time) duration_range, " +
      s"$pageCnt(pv_num) pv_num_range " +
      "FROM tmp"

    spark.sql(sql1).createOrReplaceTempView("tmp")
    spark.sql(sql2)
  }


}
