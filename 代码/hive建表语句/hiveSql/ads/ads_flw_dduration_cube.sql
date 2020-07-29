DROP TABLE IF EXISTS ads_flw_dduration_cube;
CREATE TABLE ads_flw_dduration_cube
(
    version        string COMMENT '应用版本',
    channel        string COMMENT '发售渠道',
    duration_range string COMMENT '访问总时长（范围）',
    duration_count int COMMENT '在当前访问时间范围内的启动数'
) COMMENT '日使用时长多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_flw_dduration_cube'
    TBLPROPERTIES ('parquet.compression' = 'lzo');