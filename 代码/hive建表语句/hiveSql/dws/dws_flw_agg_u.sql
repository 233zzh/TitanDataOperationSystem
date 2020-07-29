/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3 
 * @Time: 11:40
 * @Version: 1.0
 * @Description:  dws_flw_agg_u 建表语句
 * @Source:  titan.dws_flw_agg_s
 */
DROP TABLE IF EXISTS titan.dws_flw_agg_u;
CREATE TABLE titan.dws_flw_agg_u(
guid	            string COMMENT  '全局用户标识',
version	            string COMMENT	'应用版本',
channel	            string COMMENT	'发售渠道',
provinceid	        string COMMENT	'省份id',
os	                string COMMENT	"操作系统",
resolution	        string COMMENT	'分辨率',
model	            string COMMENT	'设备类型',
carrier	            string COMMENT	'运营商',
network	            string COMMENT	'联网方式',
view_num            int COMMENT	'访问次数',
duration	        bigInt COMMENT	'访问时间',
pv_num	            int	   COMMENT  '页面访问量'
)
COMMENT '流量用户聚合表 flow-aggregate-user'
PARTITIONED BY (`dt` string)
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/dws/dws_flw_agg_u';