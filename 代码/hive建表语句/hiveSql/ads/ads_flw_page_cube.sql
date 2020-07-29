DROP TABLE IF EXISTS ads_flw_page_cube;
CREATE TABLE ads_flw_page_cube
(
    version      string COMMENT '应用版本',
    channel      string COMMENT '发售渠道',
    pv_num_range string COMMENT '访问页面数（范围）',
    pv_count     int COMMENT '在当前访问页面范围内的启动数'
) COMMENT '访问页面数多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    location '/apps/hive/warehouse/titan/ads/ads_flw_page_cube'
    TBLPROPERTIES ('parquet.compression' = 'lzo');