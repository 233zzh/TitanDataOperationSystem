/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3 
 * @Time: 11:38
 * @Version: 1.0
 * @Description:  dwd_dim_date 建表语句
 * @Source:  sourceTB
 */
DROP TABLE IF EXISTS titan.dwd_dim_date;
CREATE TABLE titan.dwd_dim_date(
dt          string COMMENT '当天日期',
week_dt     string COMMENT '当天所在周的星期一的日期',
month_dt    string COMMENT '当天所在月的1号日期'
)
COMMENT '日期维度表'
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/dwd/dwd_dim_date'
TBLPROPERTIES('parquet.compression'='lzo');