package cn.edu.neu.titan.titanSpark.analysis.base.app

import cn.edu.neu.titan.titanSpark.analysis.base.function.{FilterPageFunction, IdMapFunction, JSONParseFunction, SessionAggFunction, UserAggFunction}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/5 
 * @Time: 18:19
 * @Version: 1.0
 * @Description: 数据预处理和初步聚合
 */
object BaseApp {
  def main(args: Array[String]): Unit = {
    // 1. 进行id-map更新
    val eventLog = IdMapFunction.idMap
    // 2. 进行数据预处理（json解析、分配guid、过滤数据）
    JSONParseFunction.jsonParse(eventLog)
    // 3. 提取页面点击事件
    FilterPageFunction.filter()
    // 4. session粒度聚合
    SessionAggFunction.sessionAgg()
    // 5. 用户粒度聚合
    UserAggFunction.userAgg()
  }
}
