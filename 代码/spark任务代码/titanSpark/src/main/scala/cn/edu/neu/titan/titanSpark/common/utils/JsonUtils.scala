package cn.edu.neu.titan.titanSpark.common.utils

import net.sf.json.JSONObject

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/5 
 * @Time: 14:08
 * @Version: 1.0
 * @Description: Description 
 */
object JsonUtils {

  /**
   *将json字符串转化为
   * @param jsonStr
   * @return
   */
  def getJSON(jsonStr: String) = {
    JSONObject.fromObject(jsonStr)
  }

  def toMap(json: JSONObject) = {
    val iterator = json.keys()
    var map = Map.empty[String,String]
    while (iterator.hasNext){
      val key = iterator.next().toString
      map += ((key,json.getString(key)))
    }
    map
  }

  def toMap(jsonstr: String): Map[String, String] = {
    if (jsonstr.isEmpty) null
    else toMap(JSONObject.fromObject(jsonstr))
  }

}
