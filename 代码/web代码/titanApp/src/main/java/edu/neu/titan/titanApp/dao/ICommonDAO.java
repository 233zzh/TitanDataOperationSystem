package edu.neu.titan.titanApp.dao;

import edu.neu.titan.titanApp.common.beans.IdName;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 访问数据库中公共常量的接口
 */
public interface ICommonDAO {

    /**
     * 得到所有的渠道的id和对应的名称
     * @return 以List形式返回的封装id和名称类的实例集合
     */
    public List<IdName> getChannels();

    /**
     * 得到所有的版本的id和对应的名称
     * @return 以List形式返回的封装id和名称类的实例集合
     */
    public List<IdName> getVersions();

}
