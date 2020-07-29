package cn.edu.neu.titan.titanSpark.migration.terminal.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.migration.terminal._
import cn.edu.neu.titan.titanSpark.migration.terminal.function.ModelFunction.migrate
import cn.edu.neu.titan.titanSpark.migration.{connectionProperties, currentDate, spark, url}
import org.apache.spark.sql.SaveMode

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/14 
 * @Time: 10:22
 * @Version: 1.0
 * @Description: Description 
 */
object ProvinceFunction {

  def migrate()= {

    // 源表和目标表
    val tbTarget = Constants.MYSQL_TABLE_TERMINAL_REGION_PROVINCE
    val tbDim = Constants.MYSQL_TABLE_PROVINCE
    val dimName = "provinceid"
    val tbDimTemp = "tbDimTemp"
    val tbBaseTemps = Array("tbProNew", "tbProActive", "tbProStart")
    val colTarget = Array(colIncrease, colActive, colLaunch)
    val tbBases = Array((tbNewSource, colDNU), (tbActiveSource, colDAU), (tbStartSource, colStart))

    createTableDim(tbDim, tbDimTemp, dimName)

    var i = 0;
    for (tbBaseTemp <- tbBaseTemps) {
      createTableBase(tbBases(i), tbBaseTemp, dimName)
      i += 1
    }

    val cols_part = StringBuilder.newBuilder
    val join_part = StringBuilder.newBuilder

    i = 0
    while ( i<tbBaseTemps.length ) {
      val elem = tbBaseTemps(i)
      val col = colTarget(i)
      cols_part ++= s", case when $elem.num is NULL then 0 else $elem.num end as $col "
      join_part ++= s"left join $elem on cast(id as string)=$elem.$dimName and vname=$elem.version and cname=$elem.channel "
      i += 1
    }

    val sql_insert = s"select '$currentDate' province_date, " +
      "cid channel_id," +
      "vid version_id," +
      s"id province_id" +
      s"$cols_part " +
      s"from $tbDimTemp $join_part "
      spark.sql(sql_insert).write.mode(SaveMode.Append).jdbc(url,tbTarget, connectionProperties)
  }
  def main(args: Array[String]): Unit = {
    migrate()
  }
}
