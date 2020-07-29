//访问页面分布条形图
var visiting_page_distribution_Bar = echarts.init(document.getElementById('visiting_page'));

//访问页面分布(启动次数)
var visiting_page_distribution = [5000, 4000, 2500];

//使用时长
var pageId = ['1-2', '3-5', '6-9'];


var visiting_page_distribution_Bar_option = {
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
            data: pageId,
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
            data: visiting_page_distribution
        }
    ]
};


visiting_page_distribution_Bar.setOption(visiting_page_distribution_Bar_option);

jQuery("#visiting_page-datatable").dataTable();
