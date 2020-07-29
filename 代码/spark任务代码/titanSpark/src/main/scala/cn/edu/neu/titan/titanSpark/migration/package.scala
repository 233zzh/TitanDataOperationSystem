package cn.edu.neu.titan.titanSpark

import java.util.Properties

import cn.edu.neu.titan.titanSpark.analysis.currentDate
import cn.edu.neu.titan.titanSpark.common.conf.ConfigurationManager
import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/12 
 * @Time: 14:57
 * @Version: 1.0
 * @Description: Description 
 */
package object migration {

  // spark 配置
  val conf: SparkConf = new SparkConf().setAppName("TitanMigration")
//    .setMaster("local[*]")
  // spark session配置
  val spark: SparkSession = SparkSession.builder().config(conf).config("spark.sql.storeAssignmentPolicy", "LEGACY")
    .enableHiveSupport().getOrCreate()

  // spark context 配置
  val sc: SparkContext = spark.sparkContext

  // 时间全局信息
//  val today: String = DateUtils.today
  //  val currentDate: String = DateUtils.yesterday
  //  val currentDateBefore: String = DateUtils.yesterdayBefore
  val today: String = "2020-07-17"
  val currentDate: String = "2020-07-16"
  val currentDateBefore: String = "2020-07-15"

  var currentWeek: String = DateUtils.getFirstDayOfWeek(currentDate)
  var currentMonth: String = DateUtils.getFirstDayOfMonth(currentDate)

  // 数据库连接信息
  val url: String = ConfigurationManager.config.getString(Constants.JDBC_URL)
  val username: String = ConfigurationManager.config.getString(Constants.JDBC_USER)
  val password: String = ConfigurationManager.config.getString(Constants.JDBC_PASSWORD)
  val driver: String = ConfigurationManager.config.getString(Constants.JDBC_DRIVER)

  val connectionProperties = new Properties()
  connectionProperties.put("user", username)
  connectionProperties.put("password", password)

  // 全局表
  val tbChannel = "tbChannel"
  val tbVersion = "tbVersion"
  val tbCVJoin = "tbCVJoin"

  spark.read.jdbc(url, Constants.MYSQL_TABLE_CHANNEL, connectionProperties).cache().createOrReplaceTempView(tbChannel)
  spark.read.jdbc(url, Constants.MYSQL_TABLE_VERSION, connectionProperties).cache().createOrReplaceTempView(tbVersion)


  val sql_join: String = s"select " +
    s"case when $tbVersion.id is NULL then -1 else $tbVersion.id end as vid, " +
    s"case when $tbVersion.name is NULL then '' else $tbVersion.name end as vname," +
    s"case when $tbChannel.id is NULL then -1 else $tbChannel.id end as cid, " +
    s"case when $tbChannel.name is NULL then '' else $tbChannel.name end as cname " +
    s"from $tbVersion, $tbChannel " +
    s"where $tbVersion.id >= 0 AND $tbChannel.id >= 0 " +
    s"group by $tbVersion.id, $tbVersion.name, $tbChannel.id, $tbChannel.name " +
    s"grouping sets((),($tbVersion.id, $tbVersion.name),($tbChannel.id, $tbChannel.name), ($tbVersion.id, $tbVersion.name, $tbChannel.id, $tbChannel.name))"

  spark.sql(sql_join).cache().createOrReplaceTempView(tbCVJoin)
}
