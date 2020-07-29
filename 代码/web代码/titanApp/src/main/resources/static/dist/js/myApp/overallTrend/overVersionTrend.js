/**
 * Created by IntelliJ IDEA.
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/23
 * @Time: 22:26
 * @Version: 1.0
 * @Description: 为整体趋势版本分布准备饼图数据
 */

function installationTopVersion(name, map) {
    var model_topVersion_installation = echarts.init(document.getElementById('model-topVersion-installation'));
    var option = getPieOption(name, map.installationNameList, map.installationDistributionList);

    model_topVersion_installation.setOption(option);

}

function activeUserTopVersion(name, map) {
    var model_activeUser_pie = echarts.init(document.getElementById('model-topVersion-active'));
    var option = getPieOption(name, map.activeUserNameList, map.activeUserDistributionList);   //天新增用户的横轴和纵轴

    model_activeUser_pie.setOption(option);

}

function calUserTopVersion(name, map) {
    var model_launch_pie = echarts.init(document.getElementById('model-topVersion-calUser'));

    var option = getPieOption(name, map.calUserNameList, map.calUserDistributionList);   //天新增用户的横轴和纵轴

    model_launch_pie.setOption(option);

}

$(function () {
    var condition = null;
    var url = "/getTopVersionData";
    var map = getData(url, condition);
    console.log(map);
    var name = "版本：";
    installationTopVersion(name, map);
    activeUserTopVersion(name, map);
    calUserTopVersion(name, map);
});
