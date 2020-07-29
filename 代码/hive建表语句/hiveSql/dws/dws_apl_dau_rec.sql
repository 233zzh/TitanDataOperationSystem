use titan;
DROP TABLE IF EXISTS dws_apl_dau_rec;
CREATE TABLE dws_apl_dau_rec(
    guid string COMMENT '用户全局标识',
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    provinceId string COMMENT '省份id',
    os string COMMENT '操作系统',
    resolution string COMMENT '分辨率',
    model string COMMENT '设备类型',
    carrier string COMMENT '运营商',
    network string COMMENT '联网方式'
) COMMENT '日活记录表，全称：dws_apl_day_active_user_record'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse//titan/dws/dws_apl_dau_rec'
    TBLPROPERTIES('parquet.compression'='lzo');
