/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3 
 * @Time: 11:37
 * @Version: 1.0
 * @Description:  dwd_dim_province 建表语句
 * @Source:  NONE
 */
DROP TABLE IF EXISTS titan.dwd_dim_province;
CREATE TABLE titan.dwd_dim_province(
id          string COMMENT '省份id',
name        string COMMENT '省份名',
region_id   string COMMENT '地区id',
area_code   string COMMENT '地区编码',
region_name string COMMENT '地区名称'
)
COMMENT '省份维度表'
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/dwd/dwd_dim_province'
TBLPROPERTIES('parquet.compression'='lzo');