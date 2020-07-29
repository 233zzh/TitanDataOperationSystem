use titan;
DROP TABLE IF EXISTS ads_usr_start_cube;
CREATE TABLE ads_usr_start_cube(
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    provinceId string COMMENT '省份id',
    os string COMMENT '操作系统',
    resolution string COMMENT '分辨率',
    model string COMMENT '设备类型',
    carrier string COMMENT '运营商',
    network string COMMENT '联网方式',
    start_num int COMMENT '启动次数人数'
) COMMENT '日启动数多维报表'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse//titan/ads/ads_usr_start_cube'
    TBLPROPERTIES('parquet.compression'='lzo');
