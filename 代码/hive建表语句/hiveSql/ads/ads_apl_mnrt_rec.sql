DROP TABLE IF EXISTS ads_apl_mnrt_rec;
CREATE TABLE ads_apl_mnrt_rec
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    nrt_month int COMMENT '留存月数',
    nrt_count int COMMENT '留存人数'
) COMMENT '新增用户月留存数明细表'
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_apl_mnrt_rec'
    TBLPROPERTIES ('parquet.compression' = 'lzo');