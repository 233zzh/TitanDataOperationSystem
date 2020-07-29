DROP TABLE IF EXISTS ads_flw_dstart_cube;
CREATE TABLE ads_flw_dstart_cube
(
    version         string COMMENT '应用版本',
    channel         string COMMENT '发售渠道',
    start_num_range string COMMENT '启动次数（范围）',
    start_count     int COMMENT '在当前启动次数范围内的启动数'
) COMMENT '日启动数流量多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_flw_dstart_cube'
    TBLPROPERTIES ('parquet.compression' = 'lzo');