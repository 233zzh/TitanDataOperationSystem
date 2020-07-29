/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/10 
 * @Time: 11:20
 * @Version: 1.0
 * @Description:  dws_apl_usr_uca 建表语句
 * @Source:  dws_apl_uca_rec
 */
DROP TABLE IF EXISTS titan.dws_apl_usr_uca;
CREATE TABLE titan.dws_apl_usr_uca
(
    guid string COMMENT '全局用户标识',
    channel string COMMENT '渠道' ,
    version string COMMENT '版本' ,
    continuous_num int COMMENT '连续活跃天数' ,
    continuous_category string COMMENT '连续活跃天数分类'
)
    COMMENT '用户连续活跃天数表'
    PARTITIONED BY (`dt` string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dws/dws_apl_usr_uca';