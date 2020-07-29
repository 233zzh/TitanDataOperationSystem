// 全部数据
var region_data = [];

// 声明图表及其数据
var region_increase_map = echarts.init(document.getElementById('region-increase-map'));
var region_increase_bar = echarts.init(document.getElementById('region-increase-bar'));
var region_active_map = echarts.init(document.getElementById('region-active-map'));
var region_active_bar = echarts.init(document.getElementById('region-active-bar'));
var region_start_map = echarts.init(document.getElementById('region-start-map'));
var region_start_bar = echarts.init(document.getElementById('region-start-bar'));

var data_region_increase_bar = [];
var data_region_active_bar = [];
var data_region_start_bar = [];

// x轴数据
var x_region_increase_bar = [];
var x_region_active_bar = [];
var x_region_start_bar = [];

// 地图数据
var data_region_increase_map =  [];
var data_region_active_map =  [];
var data_region_start_map =  [];


// 图表配置项
var region_increase_map_option = {
    title: {
        text: '全国各省份新增人数',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b}<br/>{c}人'
    },
    visualMap: {
        min: 0,
        max: 500,
        text: ['高', '低'],
        realtime: false,
        calculable: true,
        inRange: {
            color: ['lightskyblue', 'yellow', 'orangered']
        }
    },
    geo: {
        map: "china",
        roam: !1,
        scaleLimit: {
            min: 1,
            max: 2
        },
        zoom: 1.23,
        top: 120,
        label: {
            normal: {
                show: !0,
                fontSize: "14",
                color: "rgba(0,0,0,0.7)"
            }
        },
        itemStyle: {
            normal: {
                //shadowBlur: 50,
                //shadowColor: 'rgba(0, 0, 0, 0.2)',
                borderColor: "rgba(0, 0, 0, 0.2)"
            },
            emphasis: {
                areaColor: "#4169E1",
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                borderWidth: 0
            }
        }
    },
    series: [{
        name: "增长数",
        type: "map",
        geoIndex: 0,
        data: data_region_increase_map
    }]
};
var region_active_map_option = {
    title: {
        text: '全国各省份活跃人数',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b}<br/>{c}人'
    },
    visualMap: {
        min: 0,
        max: 1000,
        text: ['高', '低'],
        realtime: false,
        calculable: true,
        inRange: {
            color: ['lightskyblue', 'yellow', 'orangered']
        }
    },
    geo: {
        map: "china",
        roam: !1,
        scaleLimit: {
            min: 1,
            max: 2
        },
        zoom: 1.23,
        top: 120,
        label: {
            normal: {
                show: !0,
                fontSize: "14",
                color: "rgba(0,0,0,0.7)"
            }
        },
        itemStyle: {
            normal: {
                //shadowBlur: 50,
                //shadowColor: 'rgba(0, 0, 0, 0.2)',
                borderColor: "rgba(0, 0, 0, 0.2)"
            },
            emphasis: {
                areaColor: "#4169E1",
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                borderWidth: 0
            }
        }
    },
    series: [{
        name: "活跃数",
        type: "map",
        geoIndex: 0,
        data: data_region_active_map
    }]
};
var region_start_map_option = {
    title: {
        text: '全国各省份启动人数',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b}<br/>{c}人'
    },
    visualMap: {
        min: 0,
        max: 2000,
        text: ['高', '低'],
        realtime: false,
        calculable: true,
        inRange: {
            color: ['lightskyblue', 'yellow', 'orangered']
        }
    },
    geo: {
        map: "china",
        roam: !1,
        scaleLimit: {
            min: 1,
            max: 2
        },
        zoom: 1.23,
        top: 120,
        label: {
            normal: {
                show: !0,
                fontSize: "14",
                color: "rgba(0,0,0,0.7)"
            }
        },
        itemStyle: {
            normal: {
                //shadowBlur: 50,
                //shadowColor: 'rgba(0, 0, 0, 0.2)',
                borderColor: "rgba(0, 0, 0, 0.2)"
            },
            emphasis: {
                areaColor: "#4169E1",
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                borderWidth: 0
            }
        }
    },
    series: [{
        name: "启动数",
        type: "map",
        geoIndex: 0,
        data: data_region_start_map
    }]
};

var region_increase_bar_option = option = {
    title: {
        text: '省份新增人数Top10',
        left: 'center'
    },
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
            name: '新增人数',
            type: 'bar',
            barWidth: '25%',
            data: data_region_increase_bar
        }
    ]
};
var region_active_bar_option = option = {
    title: {
        text: '省份新增人数Top10',
        left: 'center'
    },
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
            name: '活跃人数',
            type: 'bar',
            barWidth: '25%',
            data: data_region_increase_bar
        }
    ]
};
var region_start_bar_option = option = {
    title: {
        text: '省份新增人数Top10',
        left: 'center'
    },
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
            barWidth: '25%',
            data: data_region_increase_bar
        }
    ]
};

// 初始化
region_start_map.setOption(region_start_map_option);
region_active_map.setOption(region_active_map_option);
region_increase_map.setOption(region_increase_map_option);

region_increase_bar.setOption(region_increase_bar_option);
region_active_bar.setOption(region_active_bar_option);
region_start_bar.setOption(region_start_bar_option);

function getMaps() {
    data_region_increase_map = [];
    data_region_active_map = [];
    data_region_start_map = [];
    region_data.forEach((value => {
        data_region_increase_map.push({name:value[0], value:value[1]});
        data_region_active_map.push({name:value[0], value:value[3]});
        data_region_start_map.push({name:value[0], value:value[5]});
    }));
    region_increase_map.setOption({
        series: [{data: data_region_increase_map}]
    });
    region_active_map.setOption({
        series: [{data: data_region_active_map}]
    });
    region_start_map.setOption({
        series: [{data: data_region_start_map}]
    });
}

/**
 * 将所有省份数据加载
 */
function regionLoad() {
    toPercent(region_data, 1 , 2, 3);
    jQuery("#region-datatable").dataTable({
        "data": region_data,
        "autoWidth": false,
        "destroy": true,
        "columns": [{"title": "联网方式"},
            {"title": "新增用户"},
            {"title": "新增用户占比"},
            {"title": "活跃用户"},
            {"title": "活跃用户占比"},
            {"title": "启动次数"},
            {"title": "启动次数占比"}],
        "columnDefs": tableColDefPer(2, 4, 6)
    });
    var increase = topN(region_data, 1, 10);
    x_region_increase_bar = increase.x;
    data_region_increase_bar = increase.y;
    var active = topN(region_data, 3, 10);
    x_region_active_bar = active.x;
    data_region_active_bar = active.y;
    var start = topN(region_data, 5, 10);
    x_region_start_bar = start.x;
    data_region_start_bar = start.y;
    region_increase_bar.setOption({
        yAxis: [{data: x_region_increase_bar.reverse()}],
        series: [{data: data_region_increase_bar.reverse()}]
    });
    region_active_bar.setOption({
        yAxis: [{data: x_region_active_bar.reverse()}],
        series: [{data: data_region_active_bar.reverse()}]
    });
    region_start_bar.setOption({
        yAxis: [{data: x_region_start_bar.reverse()}],
        series: [{data: data_region_start_bar.reverse()}]
    });
    getMaps();
}

function generateAll() {
    $.ajax({
        url:"/getRegionData",
        type: "get",
        data: getCondition(),
        dataType: "json",
        success: function (data) {
            region_data = data.province;
            regionLoad();
        }
    });
}

$(document).ready(function () {
    jQuery("#date-range").val('06/04/2020 - 06/14/2020');
    generateAll();
});
