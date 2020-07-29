package cn.edu.neu.titan.titanSpark.migration.participation.function

import cn.edu.neu.titan.titanSpark.analysis.today
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import cn.edu.neu.titan.titanSpark.migration.currentMonth
import cn.edu.neu.titan.titanSpark.migration.participation.executeMigrate2

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/7/20 
 * @Time: 11:46
 * @Version: 1.0
 * @Description: Description 
 */
object ParticipationFrequencyMonth {


  def main(args: Array[String]): Unit = {
    if (DateUtils.isFirstDayOfMonth(today)) {
      migrate()
    }
  }

  def migrate(): Unit = {
    val tbDurationRange = Constants.MYSQL_TABLE_FREQUENCY_RANGE_MONTH
    val tbTarget = Constants.MYSQL_TABLE_BASE_PARTICIPATION_FREQUENCY_MONTH
    val tbSource = Constants.HIVE_TABLE_ADS_FLW_MSTART_CUBE

    val dimName = "start_num_range"

    val colStartCount = "start_count"
    val colUser = "user_num" //单日使用总时长在指定范围内的用户数

    // 声明需要增加的列
    val colSource = Array((tbSource, colStartCount))
    val colTarget = Array(colUser)

    //时间
    val dt = currentMonth
    // 执行迁移
    executeMigrate2(dt, colSource, tbTarget, tbDurationRange, dimName, colTarget)
  }
}
