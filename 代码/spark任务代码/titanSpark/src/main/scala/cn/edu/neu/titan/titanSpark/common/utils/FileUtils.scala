package cn.edu.neu.titan.titanSpark.common.utils

import org.apache.spark.sql.SparkSession

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/7 
 * @Time: 10:47
 * @Version: 1.0
 * @Description: 文件处理工具类
 */
object FileUtils {

  def pathIsExist(spark: SparkSession, path: String): Boolean = {
    //取文件系统
    val filePath = new org.apache.hadoop.fs.Path( path )
    val fileSystem = filePath.getFileSystem( spark.sparkContext.hadoopConfiguration )

    // 判断路径是否存在
    fileSystem.exists( filePath )
  }

}
