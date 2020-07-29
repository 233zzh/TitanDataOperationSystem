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
 * @Time: 11:46
 * @Version: 1.0
 * @Description: Description 
 */
object ParticipationPage {
  def main(args: Array[String]): Unit = {
    migrate()
  }

  def migrate(): Unit = {
    val tbDurationRange = Constants.MYSQL_TABLE_PAGE_RANGE
    val tbTarget = Constants.MYSQL_TABLE_BASE_PARTICIPATION_PAGE
    val tbSource = Constants.HIVE_TABLE_ADS_FLW_PAGE_CUBE

    val dimName = "pv_num_range"

    val colStartCount = "pv_count"
    val colStart = "start_num" //单日使用总时长在指定范围内的用户数

    // 声明需要增加的列
    val colSource = Array((tbSource, colStartCount))
    val colTarget = Array(colStart)

    //时间
    val dt = currentDate
    // 执行迁移
    executeMigrate2(dt, colSource, tbTarget, tbDurationRange, dimName, colTarget)
  }
}
