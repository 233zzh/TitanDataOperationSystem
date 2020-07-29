/**
 * Created by IntelliJ IDEA.
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/21
 * @Time: 21:09
 * @Version: 1.0
 * @Description: Description
 */

/**
 * 用户参与度-使用时长-单次使用时长分布条形图
 */
function durationDataSingleBar(map) {
    var single_duration_distribution = echarts.init(document.getElementById('single-duration-distribution'));
    var option = getBarOption(map.durationRange, map.singleDurationData, "启动次数");   //天新增用户的横轴和纵轴

    single_duration_distribution.setOption(option);
}

/**
 * 用户参与度-使用时长-日使用时长分布条形图
 */
function durationDataDayBar(map) {
    var day_duration_distribution = echarts.init(document.getElementById('day-duration-distribution'));
    var option = getBarOption(map.durationRange, map.dayurationData, "用户数");   //天新增用户的横轴和纵轴

    day_duration_distribution.setOption(option);
}


/**
 * 当页面载入时就自动调用该函式
 */

/*
$(function () {
    var condition = {"start_date": "2020-03-15", "end_date": "2020-06-19", "version_id": 1, "channel_id": 1};
    var url = "/getDurationData";
    var map = getData(condition,url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据

    // getData();          //ajax 获得数据
    durationDataSingleBar(map);      //设置天折线图
    durationDataDayBar(map);     //设置周折线图
});*/

function setDataTable(map) {
    console.log(map);
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.durationRange, map.singleDurationData);
    console.log(allData);
    toPercent(allData, 1);

    jQuery("#single-duration-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "时长"},
            {"title": "启动次数"},
            {"title": "启动次数占比"},
        ],
        "columnDefs": tableColDefPer(2)
    });


    //传入要转的列，得到一个二维数组
    allData = listsToMatrix(map.durationRange, map.dayurationData);
    console.log(allData);
    toPercent(allData, 1);

    jQuery("#day-duration-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "时长"},
            {"title": "用户数"},
            {"title": "用户数占比"},
        ],
        "columnDefs": tableColDefPer(2)
    });
}

function generateAll() {
    var url = "/getDurationData";
    var condition = getCondition();
    condition.version_id = -1;
    condition.channel_id = -1;
    // var condition = {"start_date": '2020-05-15', "end_date": '2020-06-05', "version_id": -1, "channel_id": -1};
    var map = getData(condition, url);
    /* var condition = {"start_date": "2020-03-15", "end_date": "2020-04-15", "version_id": -1, "channel_id": -1};
     var url = "/getDurationData";
     var map = getData(condition, url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据*/

    console.log(map);
    durationDataSingleBar(map);
    durationDataDayBar(map);
    setDataTable(map);
}


$(function () {
    $('#date-range').daterangepicker({
        "startDate": moment().subtract("days", 7),
        "endDate": moment(),
    });

    generateAll();
});

