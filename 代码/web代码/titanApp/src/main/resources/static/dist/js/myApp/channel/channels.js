/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/21
 * @Time: 18:19
 * @Version: 1.0
 * @Description: Description
 */


/**
 * 设置渠道分析模块下渠道列表子模块中的新增用户折线图
 */
function installationLine(map) {
    var channels_installation_line = echarts.init(document.getElementById('channels-installation-line'));
    var option = getLineOption(map.channels_date, map.installation,"AppStore");   //天新增用户的横轴和纵轴

    channels_installation_line.setOption(option);
}

/**
 * 设置渠道分析模块下渠道列表子模块中的活跃用户折线图
 */
function activeUserLine(map) {
    var channels_activeUser_line = echarts.init(document.getElementById('channels-activeUser-line'));
    var option = getLineOption(map.channels_date, map.activeUser,"AppStore");   //天新增用户的横轴和纵轴

    channels_activeUser_line.setOption(option);
}

/**
 * 设置渠道分析模块下渠道列表子模块中的启动次数折线图
 */
function launchLine(map) {
    var channels_launch_line = echarts.init(document.getElementById('channels-launch-line'));
    var option = getLineOption(map.channels_date, map.launchNum,"AppStore");   //天新增用户的横轴和纵轴

    channels_launch_line.setOption(option);
}


/**
 * 当页面载入时就自动调用该函式
 */
$(function () {
    var condition = {"start_date": "2020-03-15", "end_date": "2020-06-19", "version_id": 1, "channel_id": 1};
    var url = "/getChannelListData";
    var map = getData(condition,url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
    console.log(map);

    // getData();          //ajax 获得数据
    installationLine(map);      //设置天折线图
    activeUserLine(map);     //设置周折线图
    launchLine(map);    //设置月折线图
});