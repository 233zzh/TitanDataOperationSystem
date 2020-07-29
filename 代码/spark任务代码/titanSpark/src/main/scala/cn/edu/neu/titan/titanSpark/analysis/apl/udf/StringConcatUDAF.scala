package cn.edu.neu.titan.titanSpark.analysis.apl.udf

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator


import scala.collection.mutable.ArrayBuffer
/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/11 
 * @Time: 10:16
 * @Version: 1.0
 * @Description: 自定义UDAF 用于字符串拼接
 */
object StringConcatUDAF extends Aggregator[String,String,String] {

  // 初始值 空字符串
  override def zero: String = ""

  // 重载的reduce方法，用于与buffer合并
  override def reduce(b: String, a: String): String = b +","+ a

  // buffer之间的合并
  override def merge(b1: String, b2: String): String = b1 +","+ b2

  // 结果输出
  override def finish(reduction: String): String = {
    val strings: Array[String] = reduction.split(",").filter(_.contains("-"))
    strings.sortWith(_<_).foldLeft[String]("")((B, a) => { if (!B.isEmpty) B+","+a else a})
  }

  override def bufferEncoder: Encoder[String] = Encoders.STRING

  override def outputEncoder: Encoder[String] = Encoders.STRING
}
