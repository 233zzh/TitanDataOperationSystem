package edu.neu.titan.titanApp.service.impl;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.utils.ArrayUtils;
import edu.neu.titan.titanApp.dao.ITerminalDAO;
import edu.neu.titan.titanApp.dao.IUserAnalyseDAO;
import edu.neu.titan.titanApp.service.ITerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/18
 * @Time: 18:07
 * @Version: 1.0
 * @Description: ITerminalService 接口实现类，用于实现终端属性部分具体业务逻辑
 */
@Service
public class TerminalServiceImpl implements ITerminalService {

    // 所需的DAO字段
    private ITerminalDAO terminalDAO;

    @Autowired
    public TerminalServiceImpl(ITerminalDAO terminalDAO) {
        this.terminalDAO = terminalDAO;
    }

    @Override
    public Object[][] getModelData(Condition condition) {
        return ArrayUtils.transform(Object.class,terminalDAO.getModelData(condition));
    }

    @Override
    public Object[][] getResolutionData(Condition condition) {
        return ArrayUtils.transform(Object.class,terminalDAO.getResolutionData(condition));
    }

    @Override
    public Object[][] getOSData(Condition condition) {
        return ArrayUtils.transform(Object.class,terminalDAO.getOSData(condition));

    }

    @Override
    public Object[][] getNetWorkData(Condition condition) {
        return ArrayUtils.transform(Object.class,terminalDAO.getNetWorkData(condition));

    }

    @Override
    public Object[][] getProvinceData(Condition condition) {
        return ArrayUtils.transform(Object.class,terminalDAO.getProvinceData(condition));
    }
}
