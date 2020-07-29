package cn.edu.neu.titan.titanSpark.migration.participation.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.currentDate
import cn.edu.neu.titan.titanSpark.migration.participation.executeMigrate2

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/7/20 
 * @Time: 11:10
 * @Version: 1.0
 * @Description: Description 
 */
object ParticipationDurationSingle {
  def main(args: Array[String]): Unit = {
    migrate()
  }

  def migrate(): Unit = {

    val tbDurationRange = Constants.MYSQL_TABLE_DURATION_RANGE_SINGLE
    val tbTarget = Constants.MYSQL_TABLE_BASE_PARTICIPATION_DURATION_SINGLE
    val tbSource = Constants.HIVE_TABLE_ADS_FLW_SDURATION_CUBE

    //需要转id的列
    val dimName = "duration_range"

    //hive的num列
    val colDurationCount = "duration_count"
    //mysql的num列
    val colStart = "start_num" //单日单次使用时长在指定范围内的使用次数

    // 声明需要增加的列
    val colSource = Array((tbSource, colDurationCount))
    val colTarget = Array(colStart)

    //时间
    val dt = currentDate
    // 执行迁移
    executeMigrate2(dt, colSource, tbTarget, tbDurationRange, dimName, colTarget)
  }
}
