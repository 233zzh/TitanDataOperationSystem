package cn.edu.neu.titan.titanSpark.common.constant

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/4
 * @Time: 10:51
 * @Version: 1.0
 * @Description: 包含程序所需常量
 */
object Constants {
  val JDBC_DATASOURCE_SIZE = "jdbc.datasource.size"
  val JDBC_URL = "jdbc.url"
  val JDBC_USER = "jdbc.user"
  val JDBC_PASSWORD = "jdbc.password"
  val JDBC_DRIVER = "jdbc.driver"

  val RANGE_START_DAY = "range.dayStart"
  val RANGE_START_WEEK = "range.weekStart"
  val RANGE_START_MONTH = "range.monthStart"

  val RANGE_PAGE = "range.page"
  val RANGE_DURATION_SINGLE = "range.singleDuration"
  val RANGE_DURATION_DAY = "range.dayDuration"

  val RANGE_INTERVAL = "range.interval"

  val RANGE_UCA = "range.uca"

  val HIVE_TABLE_ODS_EVENT_LOG = "titan.ods_event_log"

  val HIVE_TABLE_DWD_BASE_EVENT_LOG = "titan.dwd_base_event_log"
  val HIVE_TABLE_DWD_BASE_PAGE_LOG = "titan.dwd_base_page_log"

  val HIVE_TABLE_DWD_DIM_DATE = "titan.dwd_dim_date"

  val HIVE_TABLE_DWS_FLW_AGG_S = "titan.dws_flw_agg_s"
  val HIVE_TABLE_DWS_FLW_AGG_U = "titan.dws_flw_agg_u"

  val HIVE_TABLE_DWS_APL_DAU_REC = "titan.dws_apl_dau_rec"
  val HIVE_TABLE_DWS_APL_UCA_REC = "titan.dws_apl_uca_rec"
  val HIVE_TABLE_DWS_APL_USR_UCA = "titan.dws_apl_usr_uca"
  val HIVE_TABLE_DWS_APL_ITV_AGU = "titan.dws_apl_itv_agu"


  val HIVE_TABLE_ADS_USR_DAU_CUBE = "titan.ads_usr_dau_cube"
  val HIVE_TABLE_ADS_USR_WAU_CUBE = "titan.ads_usr_wau_cube"
  val HIVE_TABLE_ADS_USR_MAU_CUBE = "titan.ads_usr_mau_cube"

  val HIVE_TABLE_ADS_APL_UCA = "titan.ads_apl_uca"

  val HIVE_TABLE_ADS_USR_START_CUBE = "titan.ads_usr_start_cube"
  val HIVE_TABLE_ADS_FLW_PAGE_CUBE = "titan.ads_flw_page_cube"
  val HIVE_TABLE_ADS_FLW_SDURATION_CUBE = "titan.ads_flw_sduration_cube"
  val HIVE_TABLE_ADS_APL_USR_ITV = "titan.ads_apl_usr_itv"

  val HIVE_TABLE_DWS_APL_HSU_REC = "titan.dws_apl_hsu_rec"

  val HIVE_TABLE_DWS_APL_DNU_REC = "titan.dws_apl_dnu_rec"

  val HIVE_TABLE_ADS_USER_DNU_CUBE = "titan.ads_usr_dnu_cube"

  val HIVE_TABLE_ADS_USR_DNU_CUBE = "titan.ads_usr_dnu_cube"

  val HIVE_TABLE_ADS_APL_DNRT_REC = "titan.ads_apl_dnrt_rec"
  val HIVE_TABLE_ADS_APL_WNRT_REC = "titan.ads_apl_wnrt_rec"
  val HIVE_TABLE_ADS_APL_MNRT_REC = "titan.ads_apl_mnrt_rec"


  val MYSQL_TABLE_VERSION = "titan.version"
  val MYSQL_TABLE_CHANNEL = "titan.channel"
  val MYSQL_TABLE_MODEL = "titan.model"
  val MYSQL_TABLE_OS = "titan.os"
  val MYSQL_TABLE_RESOLUTION = "titan.resolution"
  val MYSQL_TABLE_NETWORK = "titan.network"
  val MYSQL_TABLE_PROVINCE = "titan.province"

  val MYSQL_TABLE_USER_LAUNCH = "titan.base_user_launch"
  val MYSQL_TABLE_USER_INSTALLATION_DAY = "titan.base_user_installation_day"
  val MYSQL_TABLE_USER_ACTIVE_DAY = "titan.base_user_active_day"
  val MYSQL_TABLE_USER_ACTIVE_WEEK = "titan.base_user_active_week"
  val MYSQL_TABLE_USER_ACTIVE_MONTH = "titan.base_user_active_month"

  val MYSQL_TABLE_TERMINAL_DEVICE_MODEL = "titan.base_terminal_device_model"
  val MYSQL_TABLE_TERMINAL_DEVICE_OS = "titan.base_terminal_device_os"
  val MYSQL_TABLE_TERMINAL_DEVICE_RESOLUTION = "titan.base_terminal_device_resolution"
  val MYSQL_TABLE_TERMINAL_NETWORK = "titan.base_terminal_network"
  val MYSQL_TABLE_TERMINAL_REGION_PROVINCE = "titan.base_terminal_region_province"

  val MYSQL_TABLE_RETENTION_ACTIVE_DAY = "titan.base_retention_active_day"
  val MYSQL_TABLE_RETENTION_ACTIVE_WEEK = "titan.base_retention_active_week"
  val MYSQL_TABLE_RETENTION_ACTIVE_MONTH = "titan.base_retention_active_month"

  val MYSQL_TABLE_RETENTION_INSTALLATION_DAY = "titan.base_retention_installation_day"
  val MYSQL_TABLE_RETENTION_INSTALLATION_WEEK = "titan.base_retention_installation_week"
  val MYSQL_TABLE_RETENTION_INSTALLATION_MONTH = "titan.base_retention_installation_month"



  val HIVE_TABLE_ADS_APL_DART_REC = "titan.ads_apl_dart_rec"
  val HIVE_TABLE_ADS_APL_WART_REC = "titan.ads_apl_wart_rec"
  val HIVE_TABLE_ADS_APL_MART_REC = "titan.ads_apl_mart_rec"

  //zzh
  //zzh
  val HIVE_TABLE_ADS_FLW_DDURATION_CUBE = "titan.ads_flw_dduration_cube"
  //  val HIVE_TABLE_ADS_FLW_SDURATION_CUBE = "titan.ads_flw_sduration_cube"
  val HIVE_TABLE_ADS_FLW_DSTART_CUBE = "titan.ads_flw_dstart_cube"
  val HIVE_TABLE_ADS_FLW_WSTART_CUBE = "titan.ads_flw_wstart_cube"
  val HIVE_TABLE_ADS_FLW_MSTART_CUBE = "titan.ads_flw_mstart_cube"

  val MYSQL_TABLE_BASE_PARTICIPATION_DURATION_SINGLE = "titan.base_participation_duration_single"
  val MYSQL_TABLE_BASE_PARTICIPATION_DURATION_DAY = "titan.base_participation_duration_day"
  val MYSQL_TABLE_BASE_PARTICIPATION_FREQUENCY_DAY="titan.base_participation_frequency_day"
  val MYSQL_TABLE_BASE_PARTICIPATION_FREQUENCY_WEEK="titan.base_participation_frequency_week"
  val MYSQL_TABLE_BASE_PARTICIPATION_FREQUENCY_MONTH="titan.base_participation_frequency_month"
  val MYSQL_TABLE_BASE_PARTICIPATION_PAGE = "titan.base_participation_page"
  val MYSQL_TABLE_BASE_PARTICIPATION_INTERVAL = "titan.base_participation_interval"


  val MYSQL_TABLE_DURATION_RANGE_DAY="titan.duration_range_day"
  val MYSQL_TABLE_DURATION_RANGE_SINGLE="titan.duration_range_single"
  val MYSQL_TABLE_FREQUENCY_RANGE_DAY="titan.frequency_range_day"
  val MYSQL_TABLE_FREQUENCY_RANGE_WEEK="titan.frequency_range_week"
  val MYSQL_TABLE_FREQUENCY_RANGE_MONTH="titan.frequency_range_month"
  val MYSQL_TABLE_PAGE_RANGE="titan.page_range"
  val MYSQL_TABLE_INTERVAL_RANGE="titan.interval_range"


  val MYSQL_TABLE_RETENTION_ACTIVE = "titan.base_retention_activity"


  val PATH_ID_MAP = "path.idMap"

  val EVENT_ID_PAGE_VIEW = "pageView"
  val EVENT_ID_AD_CLICK = "adClick"

  val MAX_DATE = "9999-12-31"
}
