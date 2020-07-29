/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/21
 * @Time: 23:10
 * @Version: 1.0
 * @Description: Description
 */

/**
 * 用户参与度-访问页面-访问页面分布条形图
 */
function frequencyDataDayBar(map) {
    var day_startNum_distribution = echarts.init(document.getElementById('day_startNum_distribution'));
    var option = getBarOption(map.dayFrequencyRange, map.dayFrequencyData, "启动次数");   //天新增用户的横轴和纵轴

    day_startNum_distribution.setOption(option);
}

/**
 * 用户参与度-使用频率-周启动次数分布条形图
 */
function frequencyDataWeekBar(map) {
    var week_startNum_distribution = echarts.init(document.getElementById('week_startNum_distribution'));
    var option = getBarOption(map.weekFrequencyRange, map.weekFrequencyData, "启动次数");   //天新增用户的横轴和纵轴

    week_startNum_distribution.setOption(option);
}

/**
 * 用户参与度-使用频率-月启动次数分布条形图
 */
function frequencyDataMonthBar(map) {
    var month_startNum_distribution = echarts.init(document.getElementById('month_startNum_distribution'));
    var option = getBarOption(map.monthFrequencyRange, map.monthFrequencyData, "启动次数");   //天新增用户的横轴和纵轴

    month_startNum_distribution.setOption(option);
}

/**
 * 当页面载入时就自动调用该函式
 */

/*
$(function () {
    var condition = {"start_date": "2020-03-15", "end_date": "2020-06-19", "version_id": 1, "channel_id": 1};
    var url = "/getFrequencyData";
    var map = getData(condition,url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据

    // getData();          //ajax 获得数据
    frequencyDataDayBar(map);      //设置天折线图
    frequencyDataWeekBar(map);     //设置周折线图
    frequencyDataMonthBar(map);     //设置周折线图
});*/

function setDataTable(map) {
    console.log(map);
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dayFrequencyRange, map.dayFrequencyData);
    console.log(allData);
    toPercent(allData, 1);

    jQuery("#day-startNum-distribution-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "启动次数"},
            {"title": "用户数"},
            {"title": "用户数占比"},
        ],
        "columnDefs": tableColDefPer(2)
    });


    //传入要转的列，得到一个二维数组
    allData = listsToMatrix(map.weekFrequencyRange, map.weekFrequencyData);
    console.log(allData);
    toPercent(allData, 1);

    jQuery("#week-startNum-distribution-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "启动次数"},
            {"title": "用户数"},
            {"title": "用户数占比"},
        ],
        "columnDefs": tableColDefPer(2)
    });

    //传入要转的列，得到一个二维数组
    allData = listsToMatrix(map.monthFrequencyRange, map.monthFrequencyData);
    console.log(allData);
    toPercent(allData, 1);

    jQuery("#month-startNum-distribution-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "启动次数"},
            {"title": "用户数"},
            {"title": "用户数占比"},
        ],
        "columnDefs": tableColDefPer(2)
    });
}

function generateAll() {
    var url = "/getFrequencyData";
    var condition = getCondition();
    condition.version_id = -1;
    condition.channel_id = -1;
    // var condition = {"start_date": '2020-05-15', "end_date": '2020-06-05', "version_id": -1, "channel_id": -1};
    var map = getData(condition, url);
    /* var condition = {"start_date": "2020-03-15", "end_date": "2020-03-23", "version_id": -1, "channel_id": -1};
     var url = "/getFrequencyData";
     var map = getData(condition, url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据*/

    console.log(map);
    frequencyDataDayBar(map);      //设置天折线图
    frequencyDataWeekBar(map);     //设置周折线图
    frequencyDataMonthBar(map);     //设置周折线图
    setDataTable(map);
}


$(function () {
    $('#date-range').daterangepicker({
        "startDate": moment().subtract("days", 7),
        "endDate": moment(),
    });

    generateAll();
});
