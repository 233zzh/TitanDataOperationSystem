package edu.neu.titan.titanApp.controller;

import edu.neu.titan.titanApp.service.ICommonService;
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
 * @Date: 2020/6/17
 * @Time: 18:01
 * @Version: 1.0
 * @Description: 公共数据准备的Controller
 */

@Controller
public class CommonController {

    // 所需的服务
    @Resource
    private ICommonService commonService;

    @RequestMapping(value = "/getAllChannel")
    @ResponseBody
    public Map<String, Object> getAllChannel() {
        Map<String, Object> map = new HashMap<>();
        map.put("channels", commonService.getAllChannel());
        return map;
    }

    @RequestMapping(value = "/getAllVersion")
    @ResponseBody
    public Map<String, Object> getAllVersion() {
        Map<String, Object> map = new HashMap<>();
        map.put("versions", commonService.getAllVersion());
        return map;
    }
}
