var model_increase_bar = echarts.init(document.getElementById('model-increase'));
var model_start_bar = echarts.init(document.getElementById('model-start'));

var data_model_increase = [4,7,8,2,12,6,5,7,15,6];

var data_model_start = [4,7,8,2,12,6,5,7,15,6];

var top10_model = ['iPhone12,1','iPhone8Plus','iPhoneXR','iPhoneX','iPhoneXS Max','iPhone7Plus','iPhone12,5','iPhone8','iPhoneXR','iPhone6s'];


var model_increase_bar_option = option = {
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
    xAxis: [
        {
            type: 'category',
            data: top10_model,
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 'auto',
                formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name: '新增占比',
            type: 'bar',
            barWidth: '40%',
            data: data_model_increase
        }
    ]
};

var model_start_bar_option = option = {
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
    xAxis: [
        {
            type: 'category',
            data: top10_model,
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: '启动数',
            type: 'bar',
            barWidth: '40%',
            data: data_model_start
        }
    ]
};

model_increase_bar.setOption(model_increase_bar_option);

model_start_bar.setOption(model_start_bar_option);

jQuery("#model-datatable").dataTable();

var resolution_increase_bar = echarts.init(document.getElementById('resolution-increase'));
var resolution_start_bar = echarts.init(document.getElementById('resolution-start'));

var data_resolution_increase = [4,7,8,2,12,6,5,7,15,6];

var data_resolution_start = [4,7,8,2,12,6,5,7,15,6];

var top10_resolution = ['iPhone12,1','iPhone8Plus','iPhoneXR','iPhoneX','iPhoneXS Max','iPhone7Plus','iPhone12,5','iPhone8','iPhoneXR','iPhone6s'];

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
            data: top10_resolution,
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
            name: '新增占比',
            type: 'bar',
            barWidth: '40%',
            data: data_resolution_increase
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
    xAxis: [
        {
            type: 'category',
            data: top10_resolution,
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: '启动数',
            type: 'bar',
            barWidth: '40%',
            data: data_resolution_start
        }
    ]
};

resolution_increase_bar.setOption(resolution_increase_bar_option);

resolution_start_bar.setOption(resolution_start_bar_option);

jQuery("#resolution-datatable").dataTable();

var os_increase_bar = echarts.init(document.getElementById('os-increase'));
var os_start_bar = echarts.init(document.getElementById('os-start'));

var data_os_increase = [4,7,8,2,12,6,5,7,15,6];

var data_os_start = [4,7,8,2,12,6,5,7,15,6];

var top10_os = ['iPhone12,1','iPhone8Plus','iPhoneXR','iPhoneX','iPhoneXS Max','iPhone7Plus','iPhone12,5','iPhone8','iPhoneXR','iPhone6s'];

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
            data: top10_os,
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
            name: '新增占比',
            type: 'bar',
            barWidth: '40%',
            data: data_os_increase
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
            data: top10_os,
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
            data: data_os_start
        }
    ]
};

os_increase_bar.setOption(os_increase_bar_option);

os_start_bar.setOption(os_start_bar_option);

jQuery("#os-datatable").dataTable();