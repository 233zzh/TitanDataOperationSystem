// 整体数据
var network_data = [];

// 网络条形图及数据
var network_increase_bar = echarts.init(document.getElementById('network-increase'));
var network_start_bar = echarts.init(document.getElementById('network-start'));

var data_network_increase = [];
var data_network_start = [];

// 横坐标周
var x_network_increase = [];
var x_network_start = [];

// echart配置
var network_increase_bar_option = option = {
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    yAxis: [
        {
            type: 'category',
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    xAxis: [
        {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 'auto'
            }
        }
    ],
    series: [
        {
            name: '新增数',
            type: 'bar',
            barWidth: '40%',
        }
    ]
};
var network_start_bar_option = option = {
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    yAxis: [
        {
            type: 'category',
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    xAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: '启动数',
            type: 'bar',
            barWidth: '40%',
            data: data_network_start
        }
    ]
};

// 初始化
network_increase_bar.setOption(network_increase_bar_option);
network_start_bar.setOption(network_start_bar_option);

/**
 * 将所有联网方式数据加载
 */
function networkLoad() {
    toPercent(network_data, 1 , 2);
    jQuery("#network-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": network_data,
        "column": [{"title": "联网方式"},
            {"title": "新增用户"},
            {"title": "新增用户占比"},
            {"title": "启动次数"},
            {"title": "启动次数占比"}],
        "columnDefs": tableColDefPer(2, 4)
    });
    var increase = topN(network_data, 1, 10);
    x_network_increase = increase.x;
    data_network_increase = increase.y;
    var start = topN(network_data, 3, 10);
    x_network_start = start.x;
    data_network_start = start.y;
    network_increase_bar.setOption({
        yAxis: [{data: x_network_increase.reverse()}],
        series: [{data: data_network_increase.reverse()}]
    });
    network_start_bar.setOption({
        yAxis: [{data: x_network_start.reverse()}],
        series: [{data: data_network_start.reverse()}]
    });
}

function generateAll() {
    $.ajax({
        url:"/getNetworkData",
        type: "get",
        data: getCondition(),
        dataType: "json",
        success: function (data) {
            network_data = data.network;
            networkLoad();
        }
    });
}

$(document).ready(function () {
    jQuery("#date-range").val('06/04/2020 - 06/14/2020');
    generateAll();
});