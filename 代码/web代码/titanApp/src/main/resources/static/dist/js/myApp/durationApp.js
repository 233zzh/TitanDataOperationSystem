//使用时长分布条形图
var duration_distribution_Bar = echarts.init(document.getElementById('duration_distribution'));

//使用时长分布
var duration_distribution = [5000, 4000, 2500, 1300, 500, 300, 100, 10];

//使用时长
var duration = ['1秒-3秒', '4秒-10秒', '11秒-30秒', '31秒-1分', '1分-3分', '3分-10分', '10分-30分', '30分+'];


var duration_distribution_Bar_option = {
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
            data: duration,
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
                // formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name: "启动次数",
            type: 'bar',
            barWidth: '40%',
            data: duration_distribution
        }
    ]
};


duration_distribution_Bar.setOption(duration_distribution_Bar_option);

jQuery("#duration-datatable").dataTable();



//使用时长分布条形图
var day_duration_distribution_Bar = echarts.init(document.getElementById('day_duration_distribution'));

//使用时长分布
var day_duration_distribution = [100, 200, 300, 400, 300, 200, 200, 100];

//使用时长
// var duration = ['1秒-3秒', '4秒-10秒', '11秒-30秒', '31秒-1分', '1分-3分', '3分-10分', '10分-30分', '30分+'];


var day_duration_distribution_Bar_option = {
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
            data: duration,
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
                // formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name: "用户数",
            type: 'bar',
            barWidth: '40%',
            data: day_duration_distribution
        }
    ]
};


day_duration_distribution_Bar.setOption(day_duration_distribution_Bar_option);

jQuery("#day_duration-datatable").dataTable();

