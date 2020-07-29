package edu.neu.titan.titanApp.controller;

import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.service.ITerminalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/18
 * @Time: 16:43
 * @Version: 1.0
 * @Description: Description
 */
@Controller
public class TerminalController {

    // 所需service字段
    @Resource
    ITerminalService terminalService;

    /**
     * 为终端属性功能中设备终端页面准备数据
     * @param condition 接收前台的参数
     * @return 返回前台的包含所需数据的 Map
     */
    @RequestMapping(value = "/getDeviceData")
    @ResponseBody
    public Map<String, Object> getDeviceData(Condition condition) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("model", terminalService.getModelData(condition));
        map.put("resolution", terminalService.getResolutionData(condition));
        map.put("os", terminalService.getOSData(condition));
        return map;
    }

    /**
     * 为终端属性功能中网络页面准备数据
     * @param condition 接收前台的参数
     * @return 返回前台的包含所需数据的 Map
     */
    @RequestMapping(value = "/getNetworkData")
    @ResponseBody
    public Map<String, Object> getNetworkData(Condition condition) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("network", terminalService.getNetWorkData(condition));
        return map;
    }

    /**
     * 为终端属性功能中地域页面准备数据
     * @param condition 接收前台的参数
     * @return 返回前台的包含所需数据的 Map
     */
    @RequestMapping(value = "/getRegionData")
    @ResponseBody
    public Map<String, Object> getRegionData(Condition condition) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("province", terminalService.getProvinceData(condition));
        return map;
    }

}
