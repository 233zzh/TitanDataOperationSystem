package cn.edu.neu.titan.titanSpark.migration.terminal.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.terminal._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/12 
 * @Time: 17:43
 * @Version: 1.0
 * @Description: Description 
 */
object ModelFunction {

  def migrate()= {

    val dimName = "model"
    val tbModel = Constants.MYSQL_TABLE_MODEL
    val tbTarget = Constants.MYSQL_TABLE_TERMINAL_DEVICE_MODEL

    // 声明需要增加的列
    val tbCols = Array((tbNewSource, colDNU), (tbStartSource, colStart))
    val colTarget = Array(colIncrease, colLaunch)
    // 执行迁移
    executeMigrate(tbCols, tbTarget, tbModel, dimName, colTarget)

  }

  def main(args: Array[String]): Unit = {
    migrate()
  }

}
