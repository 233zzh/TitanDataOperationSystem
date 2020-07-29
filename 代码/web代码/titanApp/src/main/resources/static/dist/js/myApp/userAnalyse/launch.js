/**
 * Created by IntelliJ IDEA.
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/21
 * @Time: 22:51
 * @Version: 1.0
 * @Description: 启动次数分析
 */

/**
 * 设置天启动次数的折线图
 */
function launchDay(name, map) {
    var model_launch_day = echarts.init(document.getElementById('model-launch-day'));
    var option = getSingleLineOption(name, map.dateDay, map.launchDay);   //天启动次数的横轴和纵轴

    model_launch_day.setOption(option);
}

/**
 * 设置周启动次数的折线图
 */
function launchWeek(name, map) {
    var model_launch_week = echarts.init(document.getElementById('model-launch-week'));
    var option = getSingleLineOption(name, map.dateWeek, map.launchWeek); //周启动次数的横轴和纵轴

    model_launch_week.setOption(option);
}

/**
 * 设置月启动次数的折线图
 */
function launchMonth(name, map) {
    var model_launch_month = echarts.init(document.getElementById('model-launch-month'));
    var option = getSingleLineOption(name, map.dateMonth, map.launchMonth);   //月启动次数的横轴和纵轴

    model_launch_month.setOption(option);
}

/**
 * 设置月启动次数的折线图
 */
function setDataTableDay(map) {

    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dateDay, map.launchDay);
    toPercent(allData, 1);
    console.log(allData);

    jQuery("#launch-day").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "日期"},
            {"title": "启动次数"},
            {"title": "启动次数占比"},
        ],
        "columnDefs": tableColDefPer( 2)
    });
}

function setDataTableWeek(map) {

    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dateWeek, map.launchWeek);
    toPercent(allData, 1);
    console.log(allData);

    jQuery("#launch-week").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "日期"},
            {"title": "启动次数"},
            {"title": "启动次数占比"},
        ],
        "columnDefs": tableColDefPer( 2)
    });
}

function setDataTableMonth(map) {

    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dateMonth, map.launchMonth);
    toPercent(allData, 1);
    console.log(allData);

    jQuery("#launch-month").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "日期"},
            {"title": "启动次数"},
            {"title": "启动次数占比"},
        ],
        "columnDefs": tableColDefPer( 2)
    });
}

function generateAll() {
    var url = "/getLaunchData";
    var map = getData(url, getCondition());    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
    console.log(map);
    var name = "启动次数";
    launchDay(name, map);      //设置天折线图
    launchWeek(name, map);     //设置周折线图
    launchMonth(name, map);    //设置月折线图

    setDataTableDay(map);
    setDataTableWeek(map);
    setDataTableMonth(map);
}
/**
 * 当页面载入时就自动调用该函式
 */
$(function () {
    $('#date-range').daterangepicker({
        "startDate": moment().subtract("days", 7),
        "endDate": moment(),
    });

    generateAll();
});


$('#list-model-launch-day-list').click(function() {
    document.getElementById("table-day-container").style.display="";
    document.getElementById("table-week-container").style.display="none";
    document.getElementById("table-month-container").style.display="none";
});


$('#list-model-launch-week-list').click(function() {
    document.getElementById("table-day-container").style.display="none";
    document.getElementById("table-week-container").style.display="";
    document.getElementById("table-month-container").style.display="none";
});

$('#list-model-launch-month-list').click(function() {
    document.getElementById("table-day-container").style.display="none";
    document.getElementById("table-week-container").style.display="none";
    document.getElementById("table-month-container").style.display="";
});
