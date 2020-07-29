/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/3 
 * @Time: 11:38
 * @Version: 1.0
 * @Description:  dwd_dim_page 建表语句
 * @Source:  NONE
 */
DROP TABLE IF EXISTS titan.dwd_dim_page;
CREATE TABLE titan.dwd_dim_page(
pgid        string COMMENT '页面id',
channel     string COMMENT '所在频道' ,
category    string COMMENT '分类',
url         string COMMENT '页面url'
)
COMMENT '页面维度表'
STORED AS parquet
LOCATION '/apps/hive/warehouse/titan/dwd/dwd_dim_page'
TBLPROPERTIES('parquet.compression'='lzo');