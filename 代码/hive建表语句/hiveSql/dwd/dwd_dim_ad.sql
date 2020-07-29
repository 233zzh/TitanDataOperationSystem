/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3 
 * @Time: 11:39
 * @Version: 1.0
 * @Description:  dwd_dim_ad 建表语句
 * @Source:  sourceTB
 */
DROP TABLE IF EXISTS titan.dwd_dim_ad;
CREATE TABLE titan.dwd_dim_ad(
adid        string COMMENT '广告ID',
medium      string COMMENT '广告媒体形式',
campaign    string COMMENT '广告系列名称'
)
COMMENT '广告维度表'
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/dwd/dwd_dim_ad'
TBLPROPERTIES('parquet.compression'='lzo');