package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/9 
 * @Time: 9:22
 * @Version: 1.0
 * @Description:
 */
object DauRecFunction {
    def insertData(): Unit = {
        val tbSource = Constants.HIVE_TABLE_DWS_FLW_AGG_U
        val tbTarget = Constants.HIVE_TABLE_DWS_APL_DAU_REC
        val dauRec_InsertSql: String = s"INSERT OVERWRITE TABLE $tbTarget " +
                s"PARTITION (dt = '$currentDate') " +
                "SELECT guid, version, channel, provinceId, os, resolution, model, carrier, network " +
                s"FROM $tbSource " +
                s"WHERE dt = '$currentDate'"

        println(dauRec_InsertSql)
        spark.sql(dauRec_InsertSql)
    }

    def main(args: Array[String]): Unit = {
        insertData()
    }

}
