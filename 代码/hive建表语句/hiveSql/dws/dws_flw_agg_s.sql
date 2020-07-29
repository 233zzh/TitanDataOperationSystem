/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3 
 * @Time: 11:39
 * @Version: 1.0
 * @Description:  dws_flw_agg_s 建表语句
 * @Source:  dwd_base_event_log
 */
DROP TABLE IF EXISTS titan.dws_flw_agg_s;
CREATE TABLE titan.dws_flw_agg_s(
guid	            string COMMENT  '全局用户标识',
sessionid	        string COMMENT	'会话id',
version	            string COMMENT	'应用版本',
channel	            string COMMENT	'发售渠道',
provinceid	        string COMMENT	'省份id',
os	                string COMMENT	"操作系统",
resolution	        string COMMENT	'分辨率',
model	            string COMMENT	'设备类型',
carrier	            string COMMENT	'运营商',
network	            string COMMENT	'联网方式',
start_time	        bigInt COMMENT	'起始时间',
end_time	        bigInt COMMENT	'结束时间',
duration	        bigInt COMMENT	'访问时间',
pv_num	            int	   COMMENT  '页面访问量',
duration_range	    string COMMENT	'访问时间（范围）',
pv_num_range	    string COMMENT	'访问页面数（范围）'
)
COMMENT '流量会话聚合表 flow-aggregate-session'
PARTITIONED BY (`dt` string)
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/dws/dws_flw_agg_s';