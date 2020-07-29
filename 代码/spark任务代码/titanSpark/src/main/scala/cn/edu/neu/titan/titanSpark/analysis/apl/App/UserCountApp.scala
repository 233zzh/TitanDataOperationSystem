package cn.edu.neu.titan.titanSpark.analysis.apl.App

import cn.edu.neu.titan.titanSpark.analysis.apl.function.{ActiveUserCountFunction, IntervalUserAggFunction, UCACountFunction, UpdateUCARecFunction, UserUCAFunction}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/9 
 * @Time: 14:50
 * @Version: 1.0
 * @Description: 计算用户数量的app
 */
object UserCountApp {

  def main(args: Array[String]): Unit = {
//    ActiveUserCountFunction.activeUsercount()
//    UpdateUCARecFunction.update()
//    UserUCAFunction.count()
//    UCACountFunction.count()
    IntervalUserAggFunction.agg()
  }


}
