use titan;
DROP TABLE IF EXISTS dws_agg_usr_cube;
CREATE TABLE dws_agg_usr_cube(
    guid string COMMENT '用户全局标识',
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    view_num int COMMENT '访问次数',
    view_time bigint COMMENT '访问总时长',
    pv_num int COMMENT '页面访问量',
    start_num_range string COMMENT '启动次数（范围）',
    duration_range string COMMENT '访问总时长（范围）',
    pv_num_range string COMMENT '页面访问量（范围）'
) COMMENT '用户多维聚合表'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse//titan/dws/dws_agg_usr_cube'
    TBLPROPERTIES('parquet.compression'='lzo');
