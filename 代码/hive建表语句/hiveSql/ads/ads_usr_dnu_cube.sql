use titan;
DROP TABLE IF EXISTS ads_usr_dnu_cube;
CREATE TABLE ads_usr_dnu_cube(
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    provinceId string COMMENT '省份id',
    os string COMMENT '操作系统',
    resolution string COMMENT '分辨率',
    model string COMMENT '设备类型',
    carrier string COMMENT '运营商',
    network string COMMENT '联网方式',
    dnu_num int COMMENT '月活人数'
) COMMENT '日新数多维报表，全称：ads_user_day_new_user_cube'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse//titan/ads/ads_usr_dnu_cube'
    TBLPROPERTIES('parquet.compression'='lzo');
