package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/9 
 * @Time: 11:48
 * @Version: 1.0
 * @Description:
 */
object DnuRecFunction {
    def insertData(): Unit = {
        val tbSource_hsu = Constants.HIVE_TABLE_DWS_APL_HSU_REC
        val tbSource_dau = Constants.HIVE_TABLE_DWS_APL_DAU_REC
        val tbTarget = Constants.HIVE_TABLE_DWS_APL_DNU_REC

        //从历史访问记录表中选出昨天 version 和 channel 都不为空的数据
        val hsuRec_TodaySql = s"SELECT guid as hsu_guid, version as hsu_version, channel as hsu_channel FROM $tbSource_hsu " +
                s"WHERE dt = '$currentDateBefore' and version != '' and channel != ''"

        //向今天的新增用户表中插入数据，插入思路就是活跃用户表与上一个 sql 的结果作左连接，选出连接结果之后右侧 guid 为空的数据即可
        val dnuRec_InsertSql = s"INSERT INTO TABLE $tbTarget " +
                s"PARTITION (dt = '$currentDate') " +
                "SELECT guid, version, channel, provinceId, os, resolution, model, carrier, network FROM " +    //选出想要的字段
                s"(SELECT * FROM $tbSource_dau dau LEFT JOIN tmp ON dau.guid = tmp.hsu_guid and dau.version = tmp.hsu_version and dau.channel = tmp.hsu_channel WHERE dau.dt = '$currentDate') " +     //左连接
                "WHERE hsu_guid is null"


        println(hsuRec_TodaySql)
        println(dnuRec_InsertSql)
        spark.sql(hsuRec_TodaySql).createOrReplaceTempView("tmp")

        spark.sql(dnuRec_InsertSql)
    }

    def main(args: Array[String]): Unit = {
        insertData()
    }

}
