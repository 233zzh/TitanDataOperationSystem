//使用间隔分布条形图
var visiting_interval_distribution_Bar = echarts.init(document.getElementById('visiting_interval'));

//使用间隔分布(启动次数)
var visiting_interval_distribution = [100,5000, 4000, 2500,2000,1000,300,200,100,20,10];

//使用间隔
var interval = ['首次', '0-24h', '1天', '2天', '3天', '4天', '5天', '6天', '7天', '8-14天', '15-30天'];


var visiting_interval_distribution_Bar_option = {
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
            data: interval,
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
            data: visiting_interval_distribution
        }
    ]
};


visiting_interval_distribution_Bar.setOption(visiting_interval_distribution_Bar_option);

jQuery("#visiting_interval-datatable").dataTable();
