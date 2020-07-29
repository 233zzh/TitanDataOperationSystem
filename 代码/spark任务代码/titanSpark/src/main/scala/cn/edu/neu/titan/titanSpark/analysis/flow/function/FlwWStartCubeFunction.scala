package cn.edu.neu.titan.titanSpark.analysis.flow.function

import cn.edu.neu.titan.titanSpark.analysis.flow.function.AggWStartCubeFunction.aggWStartCubeFunction
import cn.edu.neu.titan.titanSpark.analysis.{currentWeek, spark, today}
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import org.apache.spark.sql.DataFrame

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/7/13
 * @Time: 16:51
 * @Version: 1.0
 * @Description: Description
 */
object FlwWStartCubeFunction {
  def flwWStartCubeFunction(): DataFrame = {

    //    val currentWeek = "2020-07-13"

    val sql = "INSERT INTO titan.ads_flw_wstart_cube " + //这里的语句和上一个表是一样的
      s"PARTITION(dt = '$currentWeek') " +
      "SELECT version, channel, start_num_range, COUNT(DISTINCT guid) AS start_count " +
      "FROM titan.dws_agg_wstart_cube " +
      s"WHERE dt = '$currentWeek' " +
      "GROUP BY version, channel, start_num_range"

    spark.sql(sql)
  }

  def main(args: Array[String]): Unit = {
    if(DateUtils.isFirstDayOfWeek(today)){
      flwWStartCubeFunction()
    }
  }
}
