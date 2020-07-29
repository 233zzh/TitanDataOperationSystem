package cn.edu.neu.titan.titanSpark.common.conf

import org.apache.commons.configuration2.{FileBasedConfiguration, PropertiesConfiguration}
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder
import org.apache.commons.configuration2.builder.fluent.Parameters

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/4 
 * @Time: 10:55
 * @Version: 1.0
 * @Description: 配置工具类
 */
object ConfigurationManager {
  //使用Parameters来读取配置参数
  private val params = new Parameters()

  /**
   * FileBasedConfigurationBuilder:产生一个传入的类的实例对象
   * FileBasedConfiguration:融合FileBased与Configuration的接口
   * PropertiesConfiguration:从一个或者多个文件读取配置的标准配置加载器
   * configure():通过params实例初始化配置生成器
   * 向FileBasedConfigurationBuilder()中传入一个标准配置加载器类，生成一个加载器类的实例对象，然后通过params参数对其初始化
   */
  private val builder =
    new FileBasedConfigurationBuilder[FileBasedConfiguration](classOf[PropertiesConfiguration])
      .configure(params.properties()
        .setFileName("titan.properties"))

  // 通过getConfiguration获取配置对象
  val config: FileBasedConfiguration = builder.getConfiguration
}

