package cn.edu.neu.titan.titanSpark.analysis.flow.app

import cn.edu.neu.titan.titanSpark.analysis.flow.function.{PageCountFunction, SDurationFunction, StartCountFunction}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/9 
 * @Time: 9:55
 * @Version: 1.0
 * @Description: session级别流量统计
 */
object SessionFlowApp {

  def main(args: Array[String]): Unit = {
    // 1. 单次访问页面数统计
    PageCountFunction.pageCount()
    // 2. 单词访问时长统计
    SDurationFunction.durationCount()
    // 3. 启动数统计
    StartCountFunction.startCount()

  }

}
