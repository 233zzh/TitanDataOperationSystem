/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/21
 * @Time: 21:59
 * @Version: 1.0
 * @Description: Description
 */

/**
 * ajax 向 controller 发送请求，获得要展示的数据
 * @param condition
 * @param url
 */
function getData(condition,url) {
    var map;

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
 * 传入横轴和纵轴，获得 Option（折线图）
 * @param x  横轴
 * @param y 纵轴
 * @param name
 * @returns  设置好的 Option
 */
function getLineOption(x, y, name) {
    return {
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
                data: x,     //设置横轴数据
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
                data: y   //设置纵轴数据
            }
        ]
    };
}

/**
 * 传入横轴和纵轴，获得 Option（条形图）
 * @param x  横轴
 * @param y 纵轴
 * @param name
 * @returns  设置好的 Option
 */
function getBarOption(x, y, name) {
    return {
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
                data: x,
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
                name: name,
                type: 'bar',
                barWidth: '40%',
                data: y
            }
        ]
    };
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
