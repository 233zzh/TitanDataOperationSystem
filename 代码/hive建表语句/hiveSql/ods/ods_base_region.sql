/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3 
 * @Time: 11:37
 * @Version: 1.0
 * @Description:  ods_base_region 建表语句
 * @Source:  sourceTB
 */
DROP TABLE IF EXISTS titan.ods_base_region;
CREATE TABLE titan.ods_base_region(
id      string COMMENT '地区id',
name    string COMMENT '地区名称'
)
COMMENT '地区表'
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/ods/ods_base_region';