var abs = true;

var analy = false;

var active_trend_line = echarts.init(document.getElementById('active-trend'));

var data_active_trend = [];

var color = ['#e17148','#e17148','#e17148','#f9cdb6','#fbf8db','#e1e5f2','#a7b1e2','#7985d4']

var date_range = [];

var range_slider = document.getElementById('range-slider');

var range_left = 0;

var range_right = 8;

noUiSlider.create(range_slider,{
    start: [0,6],
    step: 1,
    connect: [false,true,false],
    range: {
        'min': [0],
        'max': [7]
    }
});

var active_trend_line_option = {
    color: color,
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter:function (datas) {
            var res = datas[0].name + '<br/>';
            for (var i = datas.length-1; i >= 0; i--) {
                res += datas[i].seriesName + '：'
                    + datas[i].data + (!abs?'%':'人')+'<br/>'
            }
            return res
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    dataZoom: [
        {
            type: 'slider',
            show: true,
            xAxisIndex: [0],
        },
        {
            type: 'inside',
            xAxisIndex: [0],
        }
    ],
    xAxis: [
        {
            type: 'category',
            boundaryGap: false,
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
            name:'活跃7+天',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'活跃7天',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'活跃6天',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'活跃5天',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'活跃4天',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'活跃3天',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'活跃2天',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'活跃1天',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        }
    ]
};

range_slider.setAttribute('disabled', true);
$('#range-info').hide();


function toggle(element) {

    // If the checkbox is unchecked, disabled the slider.
    // Otherwise, re-enable it.
    if (!this.checked) {
        analy = false;
        element.setAttribute('disabled', true);
        $('#range-info').hide();
    } else {
        analy = true;
        element.removeAttribute('disabled');
        $('#range-info').show();
    }
    refresh();
}

document.getElementById('analysis-ckb').addEventListener('click', function () {
    toggle.call(this, range_slider);
});

range_slider.noUiSlider.on('update', function (values, handle) {
    var value = parseInt(values[handle]);
    var text = value+1;
    if (text===8) {
        text = '7+'
    }
    if (!handle) {
        $('#range-left').html(text);
        range_left = value;
    } else {
        $('#range-right').html(text);
        range_right = value;
    }
    refresh();
});

// 数据转化为百分数的具体方法
function data_to_series_percent() {

    let i;
    active_trend_line_option.series.forEach(function (value) {
        value.data=null;
    });
    active_trend_line_option.yAxis[0].axisLabel= {
            formatter: '{value} %'
    };
    active_trend_line_option.yAxis[0].max = 100;
    const right = analy?7 - range_left:7;
    const left = analy?7 - range_right:0;
    const sum = JSON.parse(JSON.stringify(data_active_trend[left]));
    for (i = left+1; i <= right; i++) {
        for (var j = 0; j < sum.length; j++) {
            sum[j] = sum[j]+data_active_trend[i][j];
        }
    }
    for (i = left; i <= right; i++) {
        var percents = [];
        for (var j = 0; j < sum.length; j++) {
            percents.push(Math.round(data_active_trend[i][j]*10000/sum[j])/100);
        }
        active_trend_line_option.series[i].data = percents;
    }
    active_trend_line.setOption(active_trend_line_option);
}

// 转化为绝对值的具体方法
function data_to_series_abs() {
    let i;
    active_trend_line_option.series.forEach(function (value) {
        value.data=null;
    });
    active_trend_line_option.yAxis[0].axisLabel= {
        formatter: '{value}'
    };
    active_trend_line_option.yAxis[0].max = null;
    const right = analy?7 - range_left:7;
    const left = analy?7 - range_right:0;
    for (i = left; i <= right; i++) {
        active_trend_line_option.series[i].data = data_active_trend[i];
    }
    active_trend_line.setOption(active_trend_line_option);
}

function to_abs() {
    abs = true;
    data_to_series_abs();
}

function to_percent() {
    abs = false;
    data_to_series_percent();
}

function refresh() {
    abs?to_abs():to_percent();
}


$.ajax({
    url:"/getActivityData",
    type: "get",
    dataType: "json",
    success: function (data) {
        date_range = data.days;
        data_active_trend = data.data.reverse();
        active_trend_line.setOption({
            xAxis: [{data: date_range}]
        });
        refresh();
    }
});