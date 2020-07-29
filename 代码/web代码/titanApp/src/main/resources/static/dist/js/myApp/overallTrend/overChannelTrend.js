/**
 * Created by IntelliJ IDEA.
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/29
 * @Time: 11:46
 * @Version: 1.0
 * @Description: 为整体趋势渠道分布准备饼图数据
 */

function installationTopChannel(name, map) {
    var model_topChannel_installation = echarts.init(document.getElementById('model-topChannel-installation'));
    var option = getPieOption(name, map.installationNameList, map.installationDistributionList);

    model_topChannel_installation.setOption(option);

}

function activeUserTopChannel(name, map) {
    var model_activeUser_pie = echarts.init(document.getElementById('model-topChannel-active'));
    var option = getPieOption(name, map.activeUserNameList, map.activeUserDistributionList);   //天新增用户的横轴和纵轴

    model_activeUser_pie.setOption(option);

}

function calUserTopChannel(name, map) {
    var model_launch_pie = echarts.init(document.getElementById('model-topChannel-calUser'));

    var option = getPieOption(name, map.calUserNameList, map.calUserDistributionList);   //天新增用户的横轴和纵轴

    model_launch_pie.setOption(option);

}

$(function () {
    var condition = null;
    var url = "/getTopChannelData";
    var map = getData(url, condition);
    console.log(map);
    var name = "版本：";
    installationTopChannel(name, map);
    activeUserTopChannel(name, map);
    calUserTopChannel(name, map);
});
