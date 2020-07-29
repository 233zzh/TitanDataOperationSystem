package cn.edu.neu.titan.titanSpark.analysis.apl.function

import cn.edu.neu.titan.titanSpark.analysis._
import cn.edu.neu.titan.titanSpark.common.constant.Constants


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/9 
 * @Time: 9:45
 * @Version: 1.0
 * @Description:
 */
object HsuRecFunction {
    def insertData(): Unit = {
        val tbSource = Constants.HIVE_TABLE_DWS_APL_DAU_REC
        val tbTarget = Constants.HIVE_TABLE_DWS_APL_HSU_REC

        //把活跃用户表按照 guid，version，channel 聚合，选出当天的数据
        val dauRec_GroupSql =  s"SELECT " +
                "guid, " +
                "case when version is NULL then '' else version end as version, " +
                "case when channel is NULL then '' else channel end as channel " +
                s"FROM $tbSource " +
                s"WHERE dt = '$currentDate' " +
                "GROUP BY guid, version, channel " +
                "GROUPING SETS((guid),(guid,version),(guid,channel),(guid,channel,version))"

        //选出历史访问记录表中前一天的数据
        val hsuRec_BeforeSql = s"SELECT * from $tbTarget WHERE dt = '$currentDateBefore'"

        //今天的活跃用户和昨天的历史记录做全连接，选出对应的数据
        val hsuRec_GroupSql = "SELECT " +
                "case when dau.guid is not null then dau.guid else hsu.guid end as guid, " +    //如果左表的guid不为空，就用左表的guid
                "case when dau.guid is not null then dau.version else hsu.version end as version, " +  //如果左表的guid不为空，就用左表的version
                "case when dau.guid is not null then dau.channel else hsu.channel end as channel, " +   //如果坐标的guid不为空，左表的channel
                s"case when hsu.guid is not null then hsu.firstLoginDate else '$currentDate' end as firstLoginDate, " + //如果右表的guid不为空，就用右表的 firstLoginDate，否则用 currentDate
                s"case when dau.guid is not null then '$currentDate' else hsu.lastLoginDate end as lastLoginDate " +    //如果左表的guid不为空，lastLoginDate 就赋值为今天
                "FROM dau_tmp dau FULL JOIN beforeHsu_tmp hsu ON dau.guid = hsu.guid and dau.version = hsu.version and dau.channel = hsu.channel "  //全连接

        val hsuRec_InsertSql = s"INSERT INTO TABLE $tbTarget " +
                s"PARTITION (dt = '$currentDate') " +
                "SELECT guid, version, channel, " +
                "MIN(firstLoginDate), MAX(lastLoginDate) " +
                "FROM todayHsu_tmp " +
                "GROUP BY guid, version, channel"

        spark.sql(dauRec_GroupSql).createOrReplaceTempView("dau_tmp")
        spark.sql(hsuRec_BeforeSql).createOrReplaceTempView("beforeHsu_tmp")
        spark.sql(hsuRec_GroupSql).createOrReplaceTempView("todayHsu_tmp")
        spark.sql(hsuRec_InsertSql)
    }

    def main(args: Array[String]): Unit = {
        insertData()
    }

}
