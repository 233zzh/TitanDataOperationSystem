//日启动次数分布条形图
var day_startNum_distribution_Bar = echarts.init(document.getElementById('day_startNum_distribution'));

//日启动次数分布
var day_startNum_distribution = [5000, 4000, 2500, 1300, 500, 300];

//启动次数
var startNum = ['1-2', '3-5', '6-9', '10-19', '20-49', '50+'];


var day_startNum_distribution_Bar_option = {
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
            data: startNum,
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
            data: day_startNum_distribution
        }
    ]
};


day_startNum_distribution_Bar.setOption(day_startNum_distribution_Bar_option);

jQuery("#day_startNum_distribution-datatable").dataTable();


//周启动次数分布条形图
var week_startNum_distribution_Bar = echarts.init(document.getElementById('week_startNum_distribution'));

//周启动次数分布
var week_startNum_distribution = [5000, 4000, 2500, 1300, 500, 300];

//启动次数
// var startNum = ['1-2', '3-5', '6-9', '10-19', '20-49', '50+'];


var week_startNum_distribution_Bar_option = {
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
            data: startNum,
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
            data: week_startNum_distribution
        }
    ]
};


week_startNum_distribution_Bar.setOption(week_startNum_distribution_Bar_option);

jQuery("#week_startNum_distribution-datatable").dataTable();


//月启动次数分布条形图
var month_startNum_distribution_Bar = echarts.init(document.getElementById('month_startNum_distribution'));

//月启动次数分布
var month_startNum_distribution = [5000, 4000, 2500, 1300, 500, 300];

//启动次数
// var startNum = ['1-2', '3-5', '6-9', '10-19', '20-49', '50+'];


var month_startNum_distribution_Bar_option = {
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
            data: startNum,
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
            data: month_startNum_distribution
        }
    ]
};


month_startNum_distribution_Bar.setOption(month_startNum_distribution_Bar_option);

jQuery("#month_startNum_distribution-datatable").dataTable();



