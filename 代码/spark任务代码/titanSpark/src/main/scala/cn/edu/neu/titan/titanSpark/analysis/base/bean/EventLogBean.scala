package cn.edu.neu.titan.titanSpark.analysis.base.bean

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/4 
 * @Time: 17:07
 * @Version: 1.0
 * @Description: 与dwd_base_event_log表对应的案例类
 */
case class EventLogBean(var guid:Long,
                         eventid: String,
                         event: Map[String, String],
                         uid: String,
                         imei: String,
                         mac: String,
                         imsi: String,
                         os: String,
                         androidid: String,
                         resolution: String,
                         model: String,
                         deviceId: String,
                         uuid: String,
                         appid: String,
                         version: String,
                         channel: String,
                         promotion_ch: String,
                         area_code:String,
                         provinceid: String,
                         longtitude: Double,
                         latitude: Double,
                         carrier: String,
                         network: String,
                         cid_sn: String,
                         ip: String,
                         sessionId: String,
                         timestamp: Long)

