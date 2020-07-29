/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3 
 * @Time: 11:35
 * @Version: 1.0
 * @Description:  ods_base_province 建表语句
 * @Source:  NONE
 */
DROP TABLE IF EXISTS titan.ods_base_province;
CREATE TABLE titan.ods_base_province(
id              string COMMENT '省份id',
name            string COMMENT '省份名',
region_id       string COMMENT '地区id',
area_code       string COMMENT '地区编码'
)
COMMENT '省份表'
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/ods/ods_base_province';