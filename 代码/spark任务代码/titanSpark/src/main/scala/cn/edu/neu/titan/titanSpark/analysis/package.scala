package cn.edu.neu.titan.titanSpark

import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/4
 * @Time: 10:43
 * @Version: 1.0
 * @Description: 包对象，包含spark相关的sparkSession和 sparkContext。
 */
package object analysis {

  System.setProperty("HADOOP_USER_NAME", "root")
  val conf: SparkConf = new SparkConf().setAppName("TitanAnalysis")
//    .setMaster("local[*]")
//    .setMaster("spark://172.16.29.109:7077")
//    .set("spark.sql.warehouse.dir","/apps/hive/warehouse")
  val spark: SparkSession = SparkSession.builder().config(conf).config("spark.sql.storeAssignmentPolicy","LEGACY")
  .enableHiveSupport().getOrCreate()

  val sc: SparkContext = spark.sparkContext
  val hConf  = new org.apache.hadoop.conf.Configuration
  // 处理数据的日期

//  var today: String = DateUtils.today
//  var currentDate: String = DateUtils.yesterday
//  var currentDateBefore: String = DateUtils.yesterdayBefore
  val today: String = "2020-07-17"
  val currentDate: String = "2020-07-16"
  val currentDateBefore: String = "2020-07-15"

  var currentWeek: String = DateUtils.getFirstDayOfWeek(currentDate)
  var currentMonth: String = DateUtils.getFirstDayOfMonth(currentDate)


//  def changeDate(args: Array[String]): Unit = {
//
//    if ( args.length==0 ) return
//    currentDate = args(0)
//    currentDateBefore = DateUtils.getDayBefore(currentDate, 1)
//    currentWeek = DateUtils.getFirstDayOfWeek(currentDate)
//    currentMonth = DateUtils.getFirstDayOfMonth(currentDate)
//  }

}
