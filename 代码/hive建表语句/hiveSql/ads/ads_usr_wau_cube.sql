use titan;
DROP TABLE IF EXISTS dws_apl_uca_rec;
CREATE TABLE dws_apl_uca_rec(
    guid string COMMENT '用户全局标识',
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    firstLoginDate string COMMENT '首次登录日期',
    startDate string COMMENT '活跃区间起始',
    endDate string COMMENT '活跃区间结束'
) COMMENT '连续活跃区间记录表，全称：dws_apl_user_continuous_active_record'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse//titan/dws/dws_apl_uca_rec'
    TBLPROPERTIES('parquet.compression'='lzo');
