package cn.edu.neu.titan.titanSpark.analysis.flow.function

import cn.edu.neu.titan.titanSpark.analysis.flow.function.AggMStartCubeFunction.aggMStartCubeFunction
import cn.edu.neu.titan.titanSpark.analysis.{currentMonth, spark, today}
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import org.apache.spark.sql.DataFrame

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/7/13
 * @Time: 17:54
 * @Version: 1.0
 * @Description: Description
 */
object FlwMstartCubeFunction {
  def flwMstartCubeFunction(): DataFrame = {

    //    val currentMonth = "2020-07-13"

    val sql = "INSERT INTO titan.ads_flw_mstart_cube " + //这里的语句和上一个表是一样的
      s"PARTITION(dt = '$currentMonth') " +
      "SELECT version, channel, start_num_range, COUNT(DISTINCT guid) AS start_count " +
      "FROM titan.dws_agg_mstart_cube " +
      s"WHERE dt = '$currentMonth' " +
      "GROUP BY version, channel, start_num_range"

    spark.sql(sql)
  }

  def main(args: Array[String]): Unit = {
    if(DateUtils.isFirstDayOfMonth(today)){
      flwMstartCubeFunction()
    }
  }
}
