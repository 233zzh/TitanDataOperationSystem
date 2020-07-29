DROP TABLE IF EXISTS ads_apl_usr_itv;
CREATE TABLE ads_apl_usr_itv
(
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    itv_days  string COMMENT '间隔天数',
    itv_count int COMMENT '发生次数'
) COMMENT '用户访问间隔分布概况统计报表 '
	PARTITIONED BY (dt string)
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_apl_usr_itv'
    TBLPROPERTIES ('parquet.compression' = 'lzo');