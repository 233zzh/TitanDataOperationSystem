package cn.edu.neu.titan.titanSpark.migration.participation.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration._
import cn.edu.neu.titan.titanSpark.migration.participation._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/7/19 
 * @Time: 22:19
 * @Version: 1.0
 * @Description: Description 
 */
object ParticipationDurationDay {

  def main(args: Array[String]): Unit = {
    migrate()
  }

  def migrate(): Unit = {
    val tbDurationRange = Constants.MYSQL_TABLE_DURATION_RANGE_DAY
    val tbTarget = Constants.MYSQL_TABLE_BASE_PARTICIPATION_DURATION_DAY
    val tbSource = Constants.HIVE_TABLE_ADS_FLW_DDURATION_CUBE

    val dimName = "duration_range"

    val colDurationCount = "duration_count"
    val colUser = "user_num" //单日使用总时长在指定范围内的用户数

    // 声明需要增加的列
    val colSource = Array((tbSource, colDurationCount))
    val colTarget = Array(colUser)

    //时间
    val dt = currentDate
    // 执行迁移
    executeMigrate2(dt, colSource, tbTarget, tbDurationRange, dimName, colTarget)
  }
}
