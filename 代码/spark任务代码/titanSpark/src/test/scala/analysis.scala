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
object analysis {

  val conf: SparkConf = new SparkConf().setAppName("TitanAnalysis").setMaster("local[*]")
  val spark: SparkSession = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()

  val sc: SparkContext = spark.sparkContext

  // 处理数据的日期
  val currentDate: String = DateUtils.yesterday
}
