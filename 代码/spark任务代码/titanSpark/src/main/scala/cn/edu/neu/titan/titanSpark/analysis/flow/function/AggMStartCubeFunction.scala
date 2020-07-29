package cn.edu.neu.titan.titanSpark.analysis.flow.function

import cn.edu.neu.titan.titanSpark.analysis.flow.function.AggWStartCubeFunction.aggWStartCubeFunction
import cn.edu.neu.titan.titanSpark.analysis.{currentMonth, spark, today}
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.{DateUtils, RangeUtils}
import org.apache.spark.sql.DataFrame

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/7/13
 * @Time: 16:13
 * @Version: 1.0
 * @Description: Description
 */
object AggMStartCubeFunction {
  def aggMStartCubeFunction(): DataFrame = {

    //    val currentMonth = "2020-07-01"
    val startCnt = "startRange"

    val startRange = ConfigurationManager.config.getString(Constants.RANGE_START_DAY).split(",")

    spark.udf.register(startCnt, (cnt: Int) => RangeUtils.getRange(cnt, startRange))

    val sql1 = "SELECT guid, version, channel, sum(view_num) view_num " +
      "FROM titan.dws_agg_usr_cube " +
      s"WHERE trunc(dt, 'MM') = '$currentMonth' " + //与上一个表相比，日期的范围变大了
      "GROUP BY guid, version, channel" //因为源表已经做了cube，所以在这里不需要 grouping sets


    val sql2 = "INSERT INTO titan.dws_agg_mstart_cube " + //这里的语句和上一个表是一样的
      s"PARTITION(dt = '$currentMonth') " +
      "SELECT guid, version, channel, view_num, " +
      s"$startCnt(view_num) start_num_range " +
      "FROM tmp"

    spark.sql(sql1).createOrReplaceTempView("tmp")
    spark.sql(sql2)
  }

  def main(args: Array[String]): Unit = {
    if(DateUtils.isFirstDayOfMonth(today)){
      aggMStartCubeFunction()
    }
  }
}
