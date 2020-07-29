package cn.edu.neu.titan.titanSpark.common.utils

import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/4 
 * @Time: 11:03
 * @Version: 1.0
 * @Description: 获得区间的工具类
 */
object RangeUtils {

  val SECOND: Int = 1000
  val MINUTE: Int = 60 * SECOND

  def main(args: Array[String]): Unit = {
    val timeranges = ConfigurationManager.config.getString(Constants.RANGE_START_DAY).split(",")
    val time = 10
    println(getRange(time, timeranges))
  }

  /**
   * 二分法查找范围
   * @param d 值
   * @param ranges 有序区间集合
   * @return 所在区间
   */
  def getRange(d: Long,ranges: Array[String]):String = {
    val length = ranges.length
    var left = 0
    var right = length-1
    while (left<right) {
      val mid: Int = left + (right-left)/2
      val value = getEndTime(ranges(mid))
      if (value==d) return ranges(mid)
      else if (value>d) {
        right = mid
      }else {
        left = mid+1
      }
    }
    ranges(left)
  }

  /**
   * 返回区间右侧的数值
   * @param range 区间
   * @return
   */
  def getEndTime(range: String):Int = {
    if (range.contains('+')) return Int.MaxValue
    else if (!range.contains("-")) return range.toInt
    val end = range.split("-")(1)
    val length = end.length
    val metric = end.charAt(length-1)
    if (metric=='s')  return end.substring(0,length-1).toInt*SECOND
    else if (metric=='m')  return end.substring(0,length-1).toInt*MINUTE
    end.toInt
  }
}
