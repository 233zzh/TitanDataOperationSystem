package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import cn.edu.neu.titan.titanSpark.analysis._

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/9 
 * @Time: 15:29
 * @Version: 1.0
 * @Description:
 */
object DnuCubeFunction {
    def insertData(): Unit = {

        val tbSource = Constants.HIVE_TABLE_DWS_APL_DNU_REC
        val tbTarget = Constants.HIVE_TABLE_ADS_USR_DNU_CUBE
        val sql =
            s"INSERT INTO TABLE $tbTarget " +
                s"PARTITION(dt='$currentDate') " +
                s"SELECT version, channel, provinceId, os, resolution, model, carrier, network, count(distinct guid) dau_num FROM $tbSource " +
                s"WHERE dt = '$currentDate' " +
                "GROUP BY version, channel, provinceId, os, resolution, model, carrier, network " +
                "GROUPING SETS(" +
                "(), version, channel, (version, channel), " +
                "provinceId, (provinceId, version), (provinceId, channel), (provinceId, version, channel), " +
                "os, (os, version), (os, channel), (os, version, channel)," +
                "resolution, (resolution, version), (resolution, channel), (resolution, version, channel), " +
                "model, (model, version), (model, channel), (model, version, channel), " +
                "carrier, (carrier, version), (carrier, channel), (carrier, version, channel), " +
                "network, (network, version), (network, channel), (network, version, channel)" +
                ")"

        println(sql)
        spark.sql(sql)
    }

    def main(args: Array[String]): Unit = {
        insertData()
    }

}
