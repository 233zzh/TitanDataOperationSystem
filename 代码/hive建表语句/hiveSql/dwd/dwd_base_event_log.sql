/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3
 * @Time: 11:39
 * @Version: 1.0
 * @Description:  dwd_base_event_log 建表语句
 * @Source:  ods_event_log
 */
DROP TABLE IF EXISTS titan.dwd_base_event_log;
CREATE TABLE titan.dwd_base_event_log(
guid                string COMMENT '全局用户标识',
eventid             string COMMENT '事件id',
event               map<string,string> COMMENT '事件内容（json）',
uid                 string COMMENT '用户id',
imei                string COMMENT '手机序列号',
mac                 string COMMENT 'mac地址',
imsi                string COMMENT '国际移动用户识别码',
os                  string COMMENT '操作系统名',
androidid           string COMMENT 'Android设备唯一标识',
resolution          string COMMENT '分辨率',
model               string COMMENT '设备类型',
deviceid            string COMMENT '设备id',
uuid                string COMMENT '通用唯一识别码',
appid               string COMMENT '应用辨识',
version             string COMMENT '应用版本',
channel             string COMMENT '发售渠道',
promotion_ch        string COMMENT '升级渠道',
area_code           string COMMENT '地区码',
provinceid          string COMMENT '省份id',
longtitude          double COMMENT '经度',
latitude            double COMMENT '维度',
carrier             string COMMENT '运营商',
network             string COMMENT '联网类型',
cid_sn              string COMMENT 'cid_sn码',
ip                  string COMMENT 'ip 地址',
sessionid           string COMMENT '会话id',
`timestamp`         bigint COMMENT '日志信息产生的时间戳'
)
COMMENT 'dwd层事件日志表（经json解析后）'
PARTITIONED BY (`dt` string)
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/dwd/dwd_base_event_log';