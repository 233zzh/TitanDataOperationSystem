/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/07/03
 * @Time: 09:33:14
 * @Version: 1.0
 * @Description: ods_event_log 建表语句
 */
DROP TABLE IF EXISTS titan.ods_event_log;
CREATE EXTERNAL TABLE titan.ods_event_log(
`line` string COMMENT '每行日志'
)
COMMENT 'ods层事件日志表'
PARTITIONED BY (`dt` string)
STORED AS
    INPUTFORMAT 'com.hadoop.mapred.DeprecatedLzoTextInputFormat'
    OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
LOCATION '/apps/hive/warehouse/titan/ods/ods_event_log';
