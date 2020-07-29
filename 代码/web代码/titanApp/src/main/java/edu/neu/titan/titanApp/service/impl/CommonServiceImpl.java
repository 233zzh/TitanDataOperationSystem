package edu.neu.titan.titanApp.service.impl;

import edu.neu.titan.titanApp.common.beans.IdName;
import edu.neu.titan.titanApp.dao.ICommonDAO;
import edu.neu.titan.titanApp.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 17:56
 * @Version: 1.0
 * @Description: ICommonService 实现类，用于实现公共数据获取的具体业务逻辑
 */
@Service
public class CommonServiceImpl implements ICommonService {

    // 所需的DAO字段
    private ICommonDAO commonDAO;

    @Autowired
    public CommonServiceImpl(ICommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    @Override
    public List<IdName> getAllChannel() {
        return commonDAO.getChannels();
    }

    @Override
    public List<IdName> getAllVersion() {
        return commonDAO.getVersions();
    }
}
