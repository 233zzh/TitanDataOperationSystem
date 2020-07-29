package cn.edu.neu.titan.titanSpark.common.utils

import cn.edu.neu.titan.titanSpark.common.constant.Constants
import net.sf.json.JSONObject
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataTypes, StructType}
import org.apache.commons.lang3.StringUtils
import org.apache.spark.broadcast.Broadcast

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/6 
 * @Time: 14:31
 * @Version: 1.0
 * @Description: id-mapping 的使用工具类
 */
object IdMapUtils {

    /**
     * 把保存在文件中的 id-map 加载到一个 map 中
     * @param spark 传入 spark 的目的是为了读取文件
     * @return map(biaoshi_Code, guid)
     */
    def loadMapDict(spark: SparkSession, path: String): collection.Map[Long, Long] = {

        //定义一个结构，为了读取文件
        val schema = new StructType()
                .add("biaoshi_hashCode", DataTypes.LongType)
                .add("guid", DataTypes.LongType)

        //把文件读取出来，保存成一个map
        val mapDict = spark.read.schema(schema).parquet(path).rdd
                .map(row => {
                    val idFlag = row.getAs[Long]("biaoshi_hashCode")
                    val guid = row.getAs[Long]("guid")
                    (idFlag, guid)
                }).collectAsMap()
        mapDict
    }


    /**
     * 传入一个 json 字符串，返回 (guid, JSONObject)
     * @param line json 字符串
     * @param bc 保存 id-map 的广播变量
     * @return (guid, JSONObject) 的元组
     */
    def idMap(line: String, bc: Broadcast[collection.Map[Long, Long]]): (Long, String) = {
        val mapDict = bc.value
        val jsonObj = JsonUtils.getJSON(line)   //将字符串转成 JSON

        val biaoshi_Code: String = getId(jsonObj)   //得到这个 JSON 的标识 code
        if(!StringUtils.isNotBlank(biaoshi_Code)) { //如果表示 code 是空，则返回 guid 为 0
            (0L, line)
        } else {
            val guid = mapDict(biaoshi_Code.hashCode)   //得到 biaoshi 的 hashCode 对应的 guid
            (guid, line) //返回 guid
        }
    }


    /**
     *  传入一个 jsonObject，返回其唯一标识，优先级：uid>imei>mac>imsi>androidId>deviceId>uuid
     * @param jsonObj 传入的 JSONObject
     * @return  标识码
     */
    private def getId(jsonObj: JSONObject): String = {

        // 从json对象中取user对象
        val userObj = jsonObj.getJSONObject("user")
        val uid = userObj.getString("uid")
        if(StringUtils.isNotBlank(uid)) {
            return uid
        }

        // 从user对象中取phone对象
        val phoneObj = userObj.getJSONObject("phone")
        val imei = phoneObj.getString("imei")
        if(StringUtils.isNotBlank(imei)) {
            return imei;
        }

        val mac = phoneObj.getString("mac")
        if(StringUtils.isNotBlank(mac)) {
            return mac;
        }
        val imsi = phoneObj.getString("imsi")
        if(StringUtils.isNotBlank(imsi)) {
            return imsi;
        }
        val androidId = phoneObj.getString("androidId")
        if(StringUtils.isNotBlank(androidId)) {
            return androidId;
        }
        val deviceId = phoneObj.getString("deviceId")
        if(StringUtils.isNotBlank(deviceId)) {
            return deviceId;
        }
        val uuid = phoneObj.getString("uuid")
        if(StringUtils.isNotBlank(uuid)) {
            return uuid;
        }

        //如果上述都为空，则返回一个空字符串
        ""
    }
}
