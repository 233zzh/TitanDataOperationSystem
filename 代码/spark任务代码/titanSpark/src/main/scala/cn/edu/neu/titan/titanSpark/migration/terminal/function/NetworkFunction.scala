package cn.edu.neu.titan.titanSpark.migration.terminal.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.terminal.function.ModelFunction.migrate
import cn.edu.neu.titan.titanSpark.migration.terminal.{colDNU, colIncrease, colLaunch, colStart, executeMigrate, tbNewSource, tbStartSource}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/14 
 * @Time: 10:14
 * @Version: 1.0
 * @Description: Description 
 */
object NetworkFunction {

  def migrate()= {

    val dimName = "network"
    val tbNetwork = Constants.MYSQL_TABLE_NETWORK
    val tbTarget = Constants.MYSQL_TABLE_TERMINAL_NETWORK

    // 声明需要增加的列
    val tbCols = Array((tbNewSource, colDNU), (tbStartSource, colStart))
    val colTarget = Array(colIncrease, colLaunch)
    // 执行迁移
    executeMigrate(tbCols, tbTarget, tbNetwork, dimName, colTarget)
  }

  def main(args: Array[String]): Unit = {
    migrate()
  }
}
