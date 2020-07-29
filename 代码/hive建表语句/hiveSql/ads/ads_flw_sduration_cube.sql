DROP TABLE IF EXISTS ads_flw_sduration_cube;
CREATE TABLE ads_flw_sduration_cube
(
    version        string COMMENT '应用版本',
    channel        string COMMENT '发售渠道',
    duration_range string COMMENT '访问时间（范围）',
    duration_count int COMMENT '在当前访问时间范围内的启动数'
) COMMENT '单次使用时长多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_flw_sduration_cube'
    TBLPROPERTIES ('parquet.compression' = 'lzo');