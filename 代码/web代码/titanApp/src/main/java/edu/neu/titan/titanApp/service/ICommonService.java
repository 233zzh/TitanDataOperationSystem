package edu.neu.titan.titanApp.service;

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
 * @Description: 为公共数据准备Controller提供服务的接口
 */
public interface ICommonService {

    /**
     * 得到所有渠道集合
      * @return 渠道集合
     */
    public List<IdName> getAllChannel() ;

    /**
     * 得到所有版本集合
     * @return 版本集合
     */
    public List<IdName> getAllVersion() ;
}
