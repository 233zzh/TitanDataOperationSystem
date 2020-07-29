/**
 * Created by IntelliJ IDEA.
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/21
 * @Time: 19:10
 * @Version: 1.0
 * @Description: 公共的函数，如 ajax 请求、getSingleLineOption
 */

function getData(url, condition) {
    var map = {};

    $.ajax({
        url: url,
        type: "get",
        data: condition,
        async: false,   //这里必须把异步请求关了，不了下面的函数获取不到 map
        dataType: "json",
        success: function (data) {
            map = data;
        }
    });

    return map;
}

/**
 * 传入横轴和纵轴，获得 Option，用于单折线图得情况
 * @param name
 * @param date  横轴
 * @param num 纵轴
 * @returns  设置好的 Option
 */
function getSingleLineOption(name, date, num) {
    var option = {
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
                data: date,     //设置横轴数据
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
                name: name,
                type: 'line',
                barWidth: '40%',
                data: num   //设置纵轴数据
            }
        ]
    };

    return option;
}

/**
 * 传入 x 轴、legend、series，返回一个折线图中有多个折现所需的数据
 * @param date  x 轴
 * @param legend 每条折现的名称
 * @param series 所有折现的数据
 * @returns 设置好的 Option
 */
function getMultiLineOption(date, legend, series) {
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: legend,
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            type: 'value'
        },
        series: series
    };

    return option;
}

function getPieOption(name, legend, data) {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'horizontal',
            bottom: 10,
            data: legend
        },
        series: [
            {
                name: name,
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: data
            }
        ]
    };

    return option;
}