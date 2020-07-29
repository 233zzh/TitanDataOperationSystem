package cn.edu.neu.titan.titanSpark.analysis.flow.function

import cn.edu.neu.titan.titanSpark.analysis._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/7/13
 * @Time: 18:01
 * @Version: 1.0
 * @Description: Description
 */
object FlwDDurationCubeFunction {
  def main(args: Array[String]): Unit = {

    val sql = "INSERT INTO titan.ads_flw_dduration_cube " + //这里的语句和上一个表是一样的
      s"PARTITION(dt = '$currentDate') " +
      "SELECT version, channel, duration_range, COUNT(DISTINCT guid) AS duration_count " +
      "FROM titan.dws_agg_usr_cube " +
      s"WHERE dt = '$currentDate' " +
      "GROUP BY version, channel, duration_range"

    spark.sql(sql)
  }
}
