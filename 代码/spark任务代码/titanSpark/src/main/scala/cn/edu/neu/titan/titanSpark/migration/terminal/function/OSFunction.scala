package cn.edu.neu.titan.titanSpark.migration.terminal.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.terminal._
import cn.edu.neu.titan.titanSpark.migration.terminal.function.ModelFunction.migrate

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/13 
 * @Time: 17:38
 * @Version: 1.0
 * @Description: Description 
 */
object OSFunction {

  def migrate()= {

    // 声明源表和目标表
    val dimName = "os"
    val tbOS = Constants.MYSQL_TABLE_OS
    val tbTarget = Constants.MYSQL_TABLE_TERMINAL_DEVICE_OS

    // 声明需要增加的列
    val tbCols = Array((tbNewSource, colDNU), (tbStartSource, colStart))
    val colTarget = Array(colIncrease, colLaunch)
    // 执行迁移
    executeMigrate(tbCols, tbTarget, tbOS, dimName, colTarget)

  }

  def main(args: Array[String]): Unit = {
    migrate()
  }

}
