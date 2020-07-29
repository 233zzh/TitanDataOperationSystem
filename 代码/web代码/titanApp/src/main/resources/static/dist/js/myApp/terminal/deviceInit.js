/**
 * Created by IntelliJ IDEA.
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/06/21
 * @Time: 17:10:45
 * @Version: 1.0
 * @Description: 设备终端页面所需变量声明和初始化
 */
// 机型数据
var model_data = [];
// 机型条形图及其所用数据
var model_increase_bar = echarts.init(document.getElementById('model-increase'));
var model_start_bar = echarts.init(document.getElementById('model-start'));

var data_model_increase = [];
var data_model_start = [];

// 机型条形图横轴
var x_model_increase = [];
var x_model_start = [];

// 机型条形图配置
var model_increase_bar_option = option = {
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params) {
            return params[0].name + '<br />' +params[0].seriesName + ': ' + params[0].value + '%';
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            axisLabel: {
                formatter: '{value}%'
            }
        }
    ],
    series: [
        {
            name: '新增占比',
            type: 'bar',
            barWidth: '40%',
        }
    ]
};
var model_start_bar_option = option = {
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params) {
            return params[0].name + '<br />' +params[0].seriesName + ': ' + params[0].value + '%';
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            axisLabel: {
                formatter: '{value}%'
            }
        }
    ],
    series: [
        {
            name: '启动占比',
            type: 'bar',
            barWidth: '40%',
        }
    ]
};

// 初始化
model_increase_bar.setOption(model_increase_bar_option);
model_start_bar.setOption(model_start_bar_option);

// 分辨率数据
var resolution_data = [];

// 分辨率条形图及所需数据
var resolution_increase_bar = echarts.init(document.getElementById('resolution-increase'));
var resolution_start_bar = echarts.init(document.getElementById('resolution-start'));

var data_resolution_increase = [];
var data_resolution_start = [];

// 分辨率条形图横轴
var x_resolution_increase = [];
var x_resolution_start = [];

// 分辨率条形图配置
var resolution_increase_bar_option = option = {
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
            name: '新增人数',
            type: 'bar',
            barWidth: '40%',
        }
    ]
};
var resolution_start_bar_option = option = {
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
            name: '启动次数',
            type: 'bar',
            barWidth: '40%',
        }
    ]
};

// 初始化
resolution_increase_bar.setOption(resolution_increase_bar_option);
resolution_start_bar.setOption(resolution_start_bar_option);


// 操作系统数据
var os_data = [];

// 操作系统条形图及所需数据
var os_increase_bar = echarts.init(document.getElementById('os-increase'));
var os_start_bar = echarts.init(document.getElementById('os-start'));

var data_os_increase = [];
var data_os_start = [];

// 操作系统条形图横轴
var x_os_increase = [];
var x_os_start = [];

// 操作系统条形图配置
var os_increase_bar_option = option = {
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
            name: '新增人数',
            type: 'bar',
            barWidth: '40%',
        }
    ]
};
var os_start_bar_option = option = {
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
            name: '启动次数',
            type: 'bar',
            barWidth: '40%',
        }
    ]
};

// 初始化
os_increase_bar.setOption(os_increase_bar_option);
os_start_bar.setOption(os_start_bar_option);
