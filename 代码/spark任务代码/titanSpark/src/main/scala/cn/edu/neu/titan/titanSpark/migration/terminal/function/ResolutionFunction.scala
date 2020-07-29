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
 * @Time: 10:12
 * @Version: 1.0
 * @Description: Description 
 */
object ResolutionFunction {

  def migrate()= {

    val dimName = "resolution"
    val tbResolution = Constants.MYSQL_TABLE_RESOLUTION
    val tbTarget = Constants.MYSQL_TABLE_TERMINAL_DEVICE_RESOLUTION

    // 声明需要增加的列
    val tbCols = Array((tbNewSource, colDNU), (tbStartSource, colStart))
    val colTarget = Array(colIncrease, colLaunch)
    // 执行迁移
    executeMigrate(tbCols, tbTarget, tbResolution, dimName, colTarget)

  }

  def main(args: Array[String]): Unit = {
    migrate()
  }

}
