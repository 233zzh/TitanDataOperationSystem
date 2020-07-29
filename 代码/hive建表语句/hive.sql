use titan;
DROP TABLE IF EXISTS titan.ods_event_log;
CREATE EXTERNAL TABLE titan.ods_event_log(
    `line` string COMMENT '每行日志'
)
    COMMENT 'ods层事件日志表'
    PARTITIONED BY (`dt` string)
    LOCATION '/apps/hive/warehouse/titan/ods/ods_event_log';

DROP TABLE IF EXISTS titan.ods_base_province;
CREATE TABLE titan.ods_base_province(
     id              string COMMENT '省份id',
     name            string COMMENT '省份名',
     region_id       string COMMENT '地区id',
     area_code       string COMMENT '地区编码'
)
    COMMENT '省份表'
    LOCATION '/apps/hive/warehouse/titan/ods/ods_base_province';


DROP TABLE IF EXISTS titan.ods_base_region;
CREATE TABLE titan.ods_base_region(
     id      string COMMENT '地区id',
     name    string COMMENT '地区名称'
)
    COMMENT '地区表'
    LOCATION '/apps/hive/warehouse/titan/ods/ods_base_region';


DROP TABLE IF EXISTS titan.dwd_dim_province;
CREATE TABLE titan.dwd_dim_province(
    id          string COMMENT '省份id',
    name        string COMMENT '省份名',
    region_id   string COMMENT '地区id',
    area_code   string COMMENT '地区编码',
    region_name string COMMENT '地区名称'
)
    COMMENT '省份维度表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dwd/dwd_dim_province';


DROP TABLE IF EXISTS titan.dwd_dim_date;
CREATE TABLE titan.dwd_dim_date(
     dt          string COMMENT '当天日期',
     week_dt     string COMMENT '当天所在周的星期一的日期',
     month_dt    string COMMENT '当天所在月的1号日期'
)
    COMMENT '日期维度表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dwd/dwd_dim_date';


DROP TABLE IF EXISTS titan.dwd_dim_page;
CREATE TABLE titan.dwd_dim_page(
    pgid        string COMMENT '页面id',
    channel     string COMMENT '所在频道' ,
    category    string COMMENT '分类',
    url         string COMMENT '页面url'
)
    COMMENT '页面维度表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dwd/dwd_dim_page';


DROP TABLE IF EXISTS titan.dwd_dim_ad;
CREATE TABLE titan.dwd_dim_ad(
   adid        string COMMENT '广告ID',
   medium      string COMMENT '广告媒体形式',
   campaign    string COMMENT '广告系列名称'
)
    COMMENT '广告维度表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dwd/dwd_dim_ad';


DROP TABLE IF EXISTS titan.dwd_base_event_log;
CREATE TABLE titan.dwd_base_event_log(
      guid                string COMMENT '全局用户标识',
      eventid             string COMMENT '事件id',
      event               map<string,string> COMMENT '事件内容（json）',
      uid                 string COMMENT '用户id',
      imei                string COMMENT '手机序列号',
      mac                 string COMMENT 'mac地址',
      imsi                string COMMENT '国际移动用户识别码',
      os                  string COMMENT '操作系统名',
      andriodid           string COMMENT 'Android设备唯一标识',
      resolution          string COMMENT '分辨率',
      model               string COMMENT '设备类型',
      deviceid            string COMMENT '设备id',
      uuid                string COMMENT '通用唯一识别码',
      appid               string COMMENT '应用辨识',
      version             string COMMENT '应用版本',
      channel             string COMMENT '发售渠道',
      promotion_ch        string COMMENT '升级渠道',
      area_code           string COMMENT '地区码',
      provinceid          string COMMENT '省份id',
      longtitude          double COMMENT '经度',
      latitude            double COMMENT '维度',
      carrier             string COMMENT '运营商',
      network             string COMMENT '联网类型',
      cid_sn              string COMMENT 'cid_sn码',
      ip                  string COMMENT 'ip 地址',
      sessionid           string COMMENT '会话id',
      `timestamp`         bigint COMMENT '日志信息产生的时间戳'
)
    COMMENT 'dwd层事件日志表（经json解析后）'
    PARTITIONED BY (`dt` string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dwd/dwd_base_event_log';


DROP TABLE IF EXISTS titan.dwd_base_page_log;
CREATE TABLE titan.dwd_base_page_log(
     guid                string COMMENT '全局用户标识',
     pgid                string COMMENT '页面id',
     uid                 string COMMENT '用户id',
     imei                string COMMENT '手机序列号',
     mac                 string COMMENT 'mac地址',
     imsi                string COMMENT '国际移动用户识别码',
     os                  string COMMENT '操作系统名',
     andriodid           string COMMENT 'Android设备唯一标识',
     resolution          string COMMENT '分辨率',
     model               string COMMENT '设备类型',
     deviceid            string COMMENT '设备id',
     uuid                string COMMENT '通用唯一识别码',
     appid               string COMMENT '应用辨识',
     version             string COMMENT '应用版本',
     channel             string COMMENT '发售渠道',
     promotion_ch        string COMMENT '升级渠道',
     area_code           string COMMENT '地区码',
     provinceid          string COMMENT '省份id',
     longtitude          double COMMENT '经度',
     latitude            double COMMENT '维度',
     carrier             string COMMENT '运营商',
     network             string COMMENT '联网类型',
     cid_sn              string COMMENT 'cid_sn码',
     ip                  string COMMENT 'ip 地址',
     sessionid           string COMMENT '会话id',
     `timestamp`         bigint COMMENT '日志信息产生的时间戳'
)
    COMMENT '页面浏览日志表'
    PARTITIONED BY (`dt` string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dwd/dwd_base_page_log';


DROP TABLE IF EXISTS titan.dws_flw_agg_s;
CREATE TABLE titan.dws_flw_agg_s(
      guid	            string COMMENT  '全局用户标识',
      sessionid	        string COMMENT	'会话id',
      version	            string COMMENT	'应用版本',
      channel	            string COMMENT	'发售渠道',
      provinceid	        string COMMENT	'省份id',
      os	                string COMMENT	"操作系统",
      resolution	        string COMMENT	'分辨率',
      model	            string COMMENT	'设备类型',
      carrier	            string COMMENT	'运营商',
      network	            string COMMENT	'联网方式',
      start_time	        bigInt COMMENT	'起始时间',
      end_time	        bigInt COMMENT	'结束时间',
      duration	        bigInt COMMENT	'访问时间',
      pv_num	            int	   COMMENT  '页面访问量',
      duration_range	    string COMMENT	'访问时间（范围）',
      pv_num_range	    string COMMENT	'访问页面数（范围）'
)
    COMMENT '流量会话聚合表 flow-aggregate-session'
    PARTITIONED BY (`dt` string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dws/dws_flw_agg_s';

DROP TABLE IF EXISTS titan.dws_flw_agg_u;
CREATE TABLE titan.dws_flw_agg_u(
     guid	            string COMMENT  '全局用户标识',
     version	            string COMMENT	'应用版本',
     channel	            string COMMENT	'发售渠道',
     provinceid	        string COMMENT	'省份id',
     os	                string COMMENT	"操作系统",
     resolution	        string COMMENT	'分辨率',
     model	            string COMMENT	'设备类型',
     carrier	            string COMMENT	'运营商',
     network	            string COMMENT	'联网方式',
     view_num            int COMMENT	'访问次数',
     duration	        bigInt COMMENT	'访问时间',
     pv_num	            int	   COMMENT  '页面访问量'
)
    COMMENT '流量用户聚合表 flow-aggregate-user'
    PARTITIONED BY (`dt` string)
    row format delimited fields terminated by ','
    STORED AS parquet;

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
    LOCATION '/apps/hive/warehouse/titan/dws/dws_agg_usr_cube';

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
    LOCATION '/apps/hive/warehouse/titan/dws/dws_agg_wstart_cube';


use titan;
DROP TABLE IF EXISTS dws_agg_mstart_cube;
CREATE TABLE dws_agg_mstart_cube(
    guid string COMMENT '用户全局标识',
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    view_num int COMMENT '访问次数',
    start_num_range string COMMENT '启动次数（范围）'
) COMMENT '启动次数月聚合多维报表，全称：dws_agg_month_start_cube'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dws/dws_agg_mstart_cube';



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
    LOCATION '/apps/hive/warehouse/titan/dws/dws_apl_dau_rec';


use titan;
DROP TABLE IF EXISTS dws_apl_dnu_rec;
CREATE TABLE dws_apl_dnu_rec(
    guid string COMMENT '用户全局标识',
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    provinceId string COMMENT '省份id',
    os string COMMENT '操作系统',
    resolution string COMMENT '分辨率',
    model string COMMENT '设备类型',
    carrier string COMMENT '运营商',
    network string COMMENT '联网方式'
) COMMENT '日新记录表，全称：dws_apl_day_new_user_record'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dws/dws_apl_dnu_rec';


use titan;
DROP TABLE IF EXISTS dws_apl_hsu_rec;
CREATE TABLE dws_apl_hsu_rec(
    guid string COMMENT '用户全局标识',
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    firstLoginDate string COMMENT '首次登录日期',
    lastLoginDate string COMMENT '末次登录日期'
) COMMENT '历史访问记录表，全称：dws_apl_history_user_record'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dws/dws_apl_hsu_rec';


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
    LOCATION '/apps/hive/warehouse/titan/dws/dws_apl_uca_rec';


use titan;
DROP TABLE IF EXISTS dws_apl_itv_agu;
CREATE TABLE dws_apl_itv_agu(
    guid string COMMENT '用户全局标识',
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    intervalDays string COMMENT '间隔天数',
    interval_range string COMMENT '间隔天数（范围）'
) COMMENT '访问间隔分布用户聚合表，全称：dws_apl_interval_aggregation_user'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/dws/dws_apl_itv_agu';



use titan;
DROP TABLE IF EXISTS ads_usr_dau_cube;
CREATE TABLE ads_usr_dau_cube(
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    provinceId string COMMENT '省份id',
    os string COMMENT '操作系统',
    resolution string COMMENT '分辨率',
    model string COMMENT '设备类型',
    carrier string COMMENT '运营商',
    network string COMMENT '联网方式',
    dau_num int COMMENT '日活人数'
) COMMENT '日活数多维报表，全称：ads_user_day_active_user_cube'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_usr_dau_cube';



use titan;
DROP TABLE IF EXISTS ads_usr_wau_cube;
CREATE TABLE ads_usr_wau_cube(
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    provinceId string COMMENT '省份id',
    os string COMMENT '操作系统',
    resolution string COMMENT '分辨率',
    model string COMMENT '设备类型',
    carrier string COMMENT '运营商',
    network string COMMENT '联网方式',
    wau_num int COMMENT '周活人数'
) COMMENT '周活数多维报表，全称：ads_user_week_active_user_cube'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_usr_wau_cube';



use titan;
DROP TABLE IF EXISTS ads_usr_mau_cube;
CREATE TABLE ads_usr_mau_cube(
    version string COMMENT '应用版本',
    channel string COMMENT '发售渠道',
    provinceId string COMMENT '省份id',
    os string COMMENT '操作系统',
    resolution string COMMENT '分辨率',
    model string COMMENT '设备类型',
    carrier string COMMENT '运营商',
    network string COMMENT '联网方式',
    mau_num int COMMENT '月活人数'
) COMMENT '月活数多维报表，全称：ads_user_month_active_user_cube'
    PARTITIONED BY (dt string COMMENT '日期')
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_usr_mau_cube';



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
    LOCATION '/apps/hive/warehouse/titan/ads/ads_usr_dnu_cube';


use titan;

DROP TABLE IF EXISTS ads_usr_start_cube;
CREATE TABLE ads_usr_start_cube
(
    version    string COMMENT '应用版本',
    channel    string COMMENT '发售渠道',
    provinceId string COMMENT '省份id',
    os         string COMMENT '操作系统',
    resolution string COMMENT '分辨率',
    model      string COMMENT '设备类型',
    carrier    string COMMENT '运营商',
    network    string COMMENT '联网方式',
    start_num  int COMMENT '新增人数'
) COMMENT '日启动数多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_usr_start_cube';


use titan;
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
    LOCATION '/apps/hive/warehouse/titan/ads/ads_flw_page_cube';

use titan;
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
    LOCATION '/apps/hive/warehouse/titan/ads/ads_flw_sduration_cube';

use titan;
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
    LOCATION '/apps/hive/warehouse/titan/ads/ads_flw_dduration_cube';


use titan;
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
    LOCATION '/apps/hive/warehouse/titan/ads/ads_flw_dstart_cube';


use titan;
DROP TABLE IF EXISTS ads_flw_wstart_cube;
CREATE TABLE ads_flw_wstart_cube
(
    version         string COMMENT '应用版本',
    channel         string COMMENT '发售渠道',
    start_num_range string COMMENT '启动次数（范围）',
    start_count     int COMMENT '在当前启动次数范围内的启动数'
) COMMENT '周启动数流量多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_flw_wstart_cube';


use titan;
DROP TABLE IF EXISTS ads_flw_mstart_cube;
CREATE TABLE ads_flw_mstart_cube
(
    version         string COMMENT '应用版本',
    channel         string COMMENT '发售渠道',
    start_num_range string COMMENT '启动次数（范围）',
    start_count     int COMMENT '在当前启动次数范围内的启动数'
) COMMENT '月启动数流量多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_flw_mstart_cube';


use titan;
DROP TABLE IF EXISTS ads_apl_uca;
CREATE TABLE ads_apl_uca
(
    continous          int COMMENT '连续活跃天数',
    continuos_categroy string COMMENT '连续活跃天数分类',
    count              int COMMENT '人数'
) COMMENT '活跃天数多维报表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_uca';


use titan;
DROP TABLE IF EXISTS ads_apl_dnrt_rec;
CREATE TABLE ads_apl_dnrt_rec
(
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    nrt_days  int COMMENT '留存天数',
    nrt_count int COMMENT '留存人数'
) COMMENT '新增用户日留存数明细表'
    PARTITIONED BY (dt string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_dnrt_rec';


use titan;
DROP TABLE IF EXISTS ads_apl_wnrt_rec;
CREATE TABLE ads_apl_wnrt_rec
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    nrt_weeks int COMMENT '留存周数',
    nrt_count int COMMENT '留存人数'
) COMMENT '新增用户周留存数明细表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_wnrt_rec';


use titan;
DROP TABLE IF EXISTS ads_apl_mnrt_rec;
CREATE TABLE ads_apl_mnrt_rec
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    nrt_month int COMMENT '留存月数',
    nrt_count int COMMENT '留存人数'
) COMMENT '新增用户月留存数明细表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_mnrt_rec';


use titan;
DROP TABLE IF EXISTS ads_apl_dart_rec;
CREATE TABLE ads_apl_dart_rec
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    art_days  int COMMENT '留存天数',
    art_count int COMMENT '留存人数'
) COMMENT '活跃用户日留存数明细表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_dart_rec';


use titan;
DROP TABLE IF EXISTS ads_apl_wart_rec;
CREATE TABLE ads_apl_wart_rec
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    art_weeks int COMMENT '留存周数',
    art_count int COMMENT '留存人数'
) COMMENT '活跃用户周留存数明细表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_wart_rec';


use titan;
DROP TABLE IF EXISTS ads_apl_mart_rec;
CREATE TABLE ads_apl_mart_rec
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    art_month int COMMENT '留存月数',
    art_count int COMMENT '留存人数'
) COMMENT '活跃用户月留存数明细表'
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_mart_rec';

use titan;
DROP TABLE IF EXISTS ads_apl_usr_itv;
CREATE TABLE ads_apl_usr_itv
(
    dt        string COMMENT '日期',
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    itv_days  int COMMENT '间隔天数',
    itv_count int COMMENT '发生次数'
) COMMENT '用户访问间隔分布概况统计报表 '
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_usr_itv';

DROP TABLE IF EXISTS ads_apl_usr_itv;
CREATE TABLE ads_apl_usr_itv
(
    version   string COMMENT '应用版本',
    channel   string COMMENT '发售渠道',
    itv_days  string COMMENT '间隔天数',
    itv_count int COMMENT '发生次数'
) COMMENT '用户访问间隔分布概况统计报表 '
    PARTITIONED BY (dt string)
    STORED AS parquet
    LOCATION '/apps/hive/warehouse/titan/ads/ads_apl_usr_itv'
    TBLPROPERTIES ('parquet.compression' = 'lzo');