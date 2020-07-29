DROP TABLE IF EXISTS ads_apl_dnrt_rec;
CREATE TABLE ads_apl_dnrt_rec
(
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    nrt_days  int COMMENT '留存天数',
    nrt_count int COMMENT '留存人数'
) COMMENT '新增用户日留存数明细表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_apl_dnrt_rec'
    TBLPROPERTIES ('parquet.compression' = 'lzo');