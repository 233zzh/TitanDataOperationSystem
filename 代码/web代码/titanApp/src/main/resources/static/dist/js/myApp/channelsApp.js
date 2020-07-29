var new_users_Line = echarts.init(document.getElementById('Top10-channels'));

var new_users_Data = [4, 7, 8, 2, 12, 6, 5, 7, 15, 6];

var date_range = ['2020-03-29', '2020-03-30', '2020-03-31', '2020-04-01', '2020-04-02', '2020-04-03', '2020-04-04', '2020-04-05', '2020-04-06', '2020-04-07']

var new_users_Line_option = {
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
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
            data: date_range,
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
                interval: 'auto'
            }
        }
    ],
    series: [
        {
            name: 'AppStore',
            type: 'line',
            barWidth: '40%',
            data: new_users_Data
        }
    ]
};
jQuery("#channel-datatable").dataTable();
new_users_Line.setOption(new_users_Line_option);
