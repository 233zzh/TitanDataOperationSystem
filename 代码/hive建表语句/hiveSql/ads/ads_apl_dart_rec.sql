DROP TABLE IF EXISTS ads_apl_dart_rec;
CREATE TABLE ads_apl_dart_rec
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    art_days  int COMMENT '留存天数',
    art_count int COMMENT '留存人数'
) COMMENT '活跃用户日留存数明细表'
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_apl_dart_rec'
    TBLPROPERTIES ('parquet.compression' = 'lzo');