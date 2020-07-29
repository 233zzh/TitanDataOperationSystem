var abs = true;

var analy = false;

var freshness_trend_line = echarts.init(document.getElementById('freshness-trend'));

var data_freshness_trend = [];

var date_range = [];

var range_slider = document.getElementById('range-slider');

var range_left = 0;

var range_right = 8;

noUiSlider.create(range_slider,{
    start: [0,7],
    step: 1,
    connect: [false,true,false],
    range: {
        'min': [0],
        'max': [8]
    }
});

var freshness_trend_line_option = {
    color: ['#4674be','#6191ca','#669bcd','#73bdd9','#7fcadd','#83cfdf','#85d4e1','#88dae2','#8de4e5'],
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
            name:'7天+前新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'7天前新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'6天前新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'5天前新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'4天前新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'3天前新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'2天前新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'1天前新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        },
        {
            name:'当天新增',
            type:'line',smooth:true,
            stack: '总量',
            areaStyle: {normal: {}},
        }
    ]
};

range_slider.setAttribute('disabled', true);
$('#range-info').hide();

function toggle(element) {

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
    var text = value;
    if (text===0) {
        text = '当';
    }else if (text===8) {
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


freshness_trend_line.setOption(freshness_trend_line_option);
refresh();
// 数据转化为百分数的方法
function data_to_series_percent() {

    let i;
    freshness_trend_line_option.series.forEach(function (value) {
        value.data=null;
    });
    freshness_trend_line_option.yAxis[0].axisLabel= {
        formatter: '{value} %'
    };
    freshness_trend_line_option.yAxis[0].max = 100;
    const right = analy?8 - range_left:8;
    const left = analy?8 - range_right:0;
    const sum = JSON.parse(JSON.stringify(data_freshness_trend[left]));
    for (i = left+1; i <= right; i++) {
        for (var j = 0; j < sum.length; j++) {
            sum[j] = sum[j]+data_freshness_trend[i][j];
        }
    }
    for (i = left; i <= right; i++) {
        var percents = [];
        for (var j = 0; j < sum.length; j++) {
            percents.push(Math.round(data_freshness_trend[i][j]*10000/sum[j])/100);
        }
        freshness_trend_line_option.series[i].data = percents;
    }
    freshness_trend_line.setOption(freshness_trend_line_option);
}

// 转化为绝对值的方法
function data_to_series_abs() {
    let i;
    freshness_trend_line_option.series.forEach(function (value) {
        value.data=null;
    });
    freshness_trend_line_option.yAxis[0].axisLabel= {
        formatter: '{value}'
    };
    freshness_trend_line_option.yAxis[0].max = null;
    const right = analy?8 - range_left:8;
    const left = analy?8 - range_right:0;
    for (i = left; i <= right; i++) {
        freshness_trend_line_option.series[i].data = data_freshness_trend[i];
    }
    freshness_trend_line.setOption(freshness_trend_line_option);
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
    url:"/getFreshnessData",
    type: "get",
    dataType: "json",
    success: function (data) {
        date_range = data.days;
        data_freshness_trend = data.data.reverse();
        freshness_trend_line.setOption({
            xAxis: [{data: date_range}]
        });
        refresh();
    }
});