/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/21
 * @Time: 17:37
 * @Version: 1.0
 * @Description: 设备终端页面所需的方法和逻辑实现
 */

/**
 * 将所有机型数据加载
 */
function modelLoad() {
    toPercent(model_data, 1 , 2);
    jQuery("#model-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": model_data,
        "column": [{"title": "机型"},
            {"title": "新增用户"},
            {"title": "新增用户占比"},
            {"title": "启动次数"},
            {"title": "启动次数占比"}],
        "columnDefs": tableColDefPer(2, 4)
    });
    var increase = topN(model_data, 2, 10);
    x_model_increase = increase.x;
    data_model_increase = increase.y;
    var start = topN(model_data, 4, 10);
    x_model_start = start.x;
    data_model_start = start.y;
    model_increase_bar.setOption({
        xAxis: [{data: x_model_increase}],
        series: [{data: data_model_increase}]
    });
    model_start_bar.setOption({
        xAxis: [{data: x_model_start}],
        series: [{data: data_model_start}]
    });
}

/**
 * 将所有分辨率数据加载
 */
function resolutionLoad() {
    toPercent(resolution_data, 1 , 2);
    jQuery("#resolution-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": resolution_data,
        "column": [{"title": "机型"},
            {"title": "新增用户"},
            {"title": "新增用户占比"},
            {"title": "启动次数"},
            {"title": "启动次数占比"}],
        "columnDefs": tableColDefPer(2, 4)
    });
    var increase = topN(resolution_data, 1, 10);
    x_resolution_increase = increase.x;
    data_resolution_increase = increase.y;
    var start = topN(resolution_data, 3, 10);
    x_resolution_start = start.x;
    data_resolution_start = start.y;
    resolution_increase_bar.setOption({
        yAxis: [{data: x_resolution_increase.reverse()}],
        series: [{data: data_resolution_increase.reverse()}]
    });
    resolution_start_bar.setOption({
        yAxis: [{data: x_resolution_start.reverse()}],
        series: [{data: data_resolution_start.reverse()}]
    });
}

/**
 * 将所有操作系统数据加载
 */
function osLoad() {
    toPercent(os_data, 1 , 2);
    jQuery("#os-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": os_data,
        "column": [{"title": "机型"},
            {"title": "新增用户"},
            {"title": "新增用户占比"},
            {"title": "启动次数"},
            {"title": "启动次数占比"}],
        "columnDefs": tableColDefPer(2, 4)
    });
    var increase = topN(os_data, 1, 10);
    x_os_increase = increase.x;
    data_os_increase = increase.y;
    var start = topN(os_data, 3, 10);
    x_os_start = start.x;
    data_os_start = start.y;
    os_increase_bar.setOption({
        yAxis: [{data: x_os_increase.reverse()}],
        series: [{data: data_os_increase.reverse()}]
    });
    os_start_bar.setOption({
        yAxis: [{data: x_os_start.reverse()}],
        series: [{data: data_os_start.reverse()}]
    });
}

function generateAll() {
    $.ajax({
        url:"/getDeviceData",
        type: "get",
        data: getCondition(),
        dataType: "json",
        success: function (data) {
            model_data = data.model;
            resolution_data = data.resolution;
            os_data = data.os;
            modelLoad();
            osLoad();
            resolutionLoad();
        }
    });
}

$(document).ready(function () {
    jQuery("#date-range").val('06/04/2020 - 06/14/2020');
    generateAll();
});
