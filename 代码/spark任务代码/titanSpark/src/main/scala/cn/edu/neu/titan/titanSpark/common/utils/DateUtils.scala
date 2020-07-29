package cn.edu.neu.titan.titanSpark.common.utils


import org.joda.time._
import org.joda.time.{DateTimeConstants, Days, Months, Weeks}
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/4 
 * @Time: 11:02
 * @Version: 1.0
 * @Description: 日期处理工具类。
 */
object DateUtils {

  val DATE_FORMAT: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")

  /**
   * 判断日期字符是否是当月第一天
   * @param dateStr 日期字符
   * @return 是否为当月第一天
   */
  def isFirstDayOfMonth(dateStr: String):Boolean = {
    DATE_FORMAT.parseDateTime(dateStr).getDayOfMonth==1
  }

  /**
   * 判断日期字符是否是当周第一天
   * @param dateStr 日期字符
   * @return 是否为当周第一天
   */
  def isFirstDayOfWeek(dateStr: String):Boolean = {
    DATE_FORMAT.parseDateTime(dateStr).getDayOfWeek==1
  }

  /**
   * 得到本周第一天
   * @param dateStr 日期字符
   * @return
   */
  def getFirstDayOfWeek(dateStr: String):String = {
    new LocalDate(dateStr).withDayOfWeek(DateTimeConstants.MONDAY).toString()
  }

  /**
   * 得到本月第一天
   * @param dateStr 日期字符
   * @return
   */
  def getFirstDayOfMonth(dateStr: String):String = {
    new LocalDate(dateStr).withDayOfMonth(1).toString()
  }

  /**
   * 获取两日期的天数差
   * @param start 开始时间
   * @param end 结束时间
   * @return 时间差
   */
  def daysBetween(start: String, end: String): Int = {
    Days.daysBetween(DATE_FORMAT.parseDateTime(start),DATE_FORMAT.parseDateTime(end)).getDays
  }

  /**
   * 获取两个日期的周数差
   * @param start 开始时间
   * @param end 结束时间
   * @return 时间差
   */
  def weeksBetween(start: String, end: String): Int = {
    Weeks.weeksBetween(DATE_FORMAT.parseDateTime(start),DATE_FORMAT.parseDateTime(end)).getWeeks
  }

  /**
   * 获取两个日期的月数差
   * @param start 开始时间
   * @param end 结束时间
   * @return 时间差
   */
  def monthsBetween(start: String, end: String): Int = {
    Months.monthsBetween(DATE_FORMAT.parseDateTime(start),DATE_FORMAT.parseDateTime(end)).getMonths
  }

  /**
   * 获得今天日期字符
   * @return 日期字符
   */
  def today: String = {
    LocalDate.now().toString
  }

  /**
   * 获得昨天日期字符
   * @return 日期字符
   */
  def yesterday :String = {
    LocalDate.now().minusDays(1).toString
  }

  /**
   * 获得前天日期字符
   * @return 日期字符
   */
  def yesterdayBefore :String = {
    LocalDate.now().minusDays(2).toString
  }

  /**
   * 得到指定天数前的日期字符
   * @param base 原日期
   * @param num 差值
   * @return
   */
  def getDayBefore(base: String, num: Int): String = {
//    DATE_FORMAT.parseDateTime(base).minusDays(num).toString()
    new LocalDate(base).minusDays(num).toString()
  }

  /**
   * 得到指定周数前的周一字符
   * @param base 原日期
   * @param num 差值
   * @return
   */
  def getWeekBefore(base: String, num: Int): String = {
    new LocalDate(base).minusWeeks(num).withDayOfWeek(1).toString()
  }
  /**
   * 得到指定月数前的某月第一天字符
   * @param base 原日期
   * @param num 差值
   * @return
   */
  def getMonthBefore(base: String, num: Int): String = {
    new LocalDate(base).minusMonths(num).withDayOfMonth(1).toString()
  }
}
