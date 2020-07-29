package cn.edu.neu.titan.titanSpark.analysis.apl.udf

import java.util

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.common.utils.DateUtils
import org.apache.hadoop.hive.ql.exec.{UDFArgumentException, UDFArgumentLengthException}
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory
import org.apache.hadoop.hive.serde2.objectinspector.{ObjectInspector, ObjectInspectorFactory, StructObjectInspector}


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/11 
 * @Time: 11:05
 * @Version: 1.0
 * @Description: Description 
 */
object IntervalUDTF extends GenericUDTF{

  val fieldNameDays: String = "interval_days"
  val fieldNum: String = "interval_num"

  override def initialize(args: Array[ObjectInspector]): StructObjectInspector = {
    if (args.length != 1) {
      throw new UDFArgumentLengthException("UserDefinedUDTF takes only one argument")
    }
    if (args(0).getCategory != ObjectInspector.Category.PRIMITIVE) {
      throw new UDFArgumentException("UserDefinedUDTF takes string as a parameter")
    }

    val fieldNames = new util.ArrayList[String]
    val fieldOIs = new util.ArrayList[ObjectInspector]

    //这里定义的是输出列默认字段名称
    fieldNames.add(fieldNameDays)
    fieldNames.add(fieldNum)

    //这里定义的是输出列字段类型
    fieldOIs.add(PrimitiveObjectInspectorFactory.javaIntObjectInspector)
    fieldOIs.add(PrimitiveObjectInspectorFactory.javaIntObjectInspector)

    ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs)
  }

  // 处理每条数据 转化为多行两列[间隔天数，出现次数]
  override def process(args: Array[AnyRef]): Unit = {

    // 获得一个字符串型数据 格式为 "yyyy-MM-dd~yyyy-MM-dd,...,yyyy-MM-dd~yyyy-MM-dd"
    val intervals_1 = args(0).toString.split(",").map(_.split("~"))
    val num_1 = intervals_1.map(days => DateUtils.daysBetween(days(0), days(1))).sum
    if (num_1!=0) forward(Array(0,num_1))
    val intervals_n = args(0).toString.split("~").map(_.split(",")).filter(_.length==2)
    intervals_n.map(days => (DateUtils.daysBetween(days(0),days(1)),1))
      .groupBy(_._1)
      .foreach{case(itv, nums) => forward(Array(itv, nums.map(_._2).sum))}
  }

  override def close(): Unit = {}
}
