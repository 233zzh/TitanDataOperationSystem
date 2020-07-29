use titan;
DROP TABLE IF EXISTS dws_agg_wstart_cube;
CREATE TABLE dws_agg_wstart_cube(
    guid string COMMENT '用户全局标识',
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    view_num int COMMENT '访问次数',
    start_num_range string COMMENT '启动次数（范围）'
) COMMENT '启动次数周聚合多维报表，全称：dws_agg_week_start_cube'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse//titan/dws/dws_agg_wstart_cube'
    TBLPROPERTIES('parquet.compression'='lzo');
