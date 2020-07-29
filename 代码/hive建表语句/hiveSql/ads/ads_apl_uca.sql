DROP TABLE IF EXISTS ads_apl_uca;
CREATE TABLE ads_apl_uca
(
    continuous_category string COMMENT '连续活跃天数分类',
    count              int COMMENT '人数'
) COMMENT '活跃天数多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_apl_uca'
    TBLPROPERTIES ('parquet.compression' = 'lzo');