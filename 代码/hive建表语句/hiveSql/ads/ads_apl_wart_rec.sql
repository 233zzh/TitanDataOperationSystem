DROP TABLE IF EXISTS ads_apl_wart_rec;
CREATE TABLE ads_apl_wart_rec
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    art_weeks int COMMENT '留存周数',
    art_count int COMMENT '留存人数'
) COMMENT '活跃用户周留存数明细表'
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_apl_wart_rec'
    TBLPROPERTIES ('parquet.compression' = 'lzo');