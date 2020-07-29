package cn.edu.neu.titan.titanSpark.analysis.dim

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.analysis.dim.bean.{AdBean, DateBean, PageBean}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/4 
 * @Time: 10:49
 * @Version: 1.0
 * @Description: 维度表初始化
 */
object DimInit {

  val ad_path = "D:\\data\\dim_ad.txt"
  val pg_path = "D:\\data\\dim_pg.txt"
  val date_path = "D:\\data\\dim_date.txt"

  def main(args: Array[String]): Unit = {

    import spark.implicits._

    // 导入广告数据
    spark.read.textFile(ad_path).map(line => {
      val splits = line.split(",")
      AdBean(splits(0),splits(1),splits(2))
    }).toDF() //.show(20)
      .write.mode("overwrite").saveAsTable("titan.dwd_dim_ad")

    // 导入页面数据
    spark.read.textFile(pg_path).map(line => {
      val splits = line.split(",")
      PageBean(splits(0),splits(1),splits(2),splits(3))
    }) //.show(20)
      .toDF().write.mode("overwrite").saveAsTable("titan.dwd_dim_page")

    // 导入日期数据
    spark.read.textFile(date_path).map(line => {
      val splits = line.split(",")
      DateBean(splits(0),splits(1),splits(2))
    }).toDF()
      .write.mode("overwrite").saveAsTable("titan.dwd_dim_date")
  }

}
