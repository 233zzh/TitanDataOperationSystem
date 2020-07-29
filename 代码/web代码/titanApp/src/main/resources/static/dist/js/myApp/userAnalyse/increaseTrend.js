/**
 * Created by IntelliJ IDEA.
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/20
 * @Time: 15:53
 * @Version: 1.0
 * @Description: 新增用户分析
 */


/**
 * 设置天新增用户的折线图
 */
function increaseDay(name, map) {
    var model_increaseTrend_day = echarts.init(document.getElementById('model-increaseTrend-day'));
    var option = getSingleLineOption(name, map.dateDay, map.installationDay);   //天新增用户的横轴和纵轴

    model_increaseTrend_day.setOption(option);
}

/**
 * 设置周新增用户的折线图
 */
function increaseWeek(name, map) {
    var model_increaseTrend_week = echarts.init(document.getElementById('model-increaseTrend-week'));
    var option = getSingleLineOption(name, map.dateWeek, map.installationWeek); //周新增用户的横轴和纵轴

    model_increaseTrend_week.setOption(option);
}

/**
 * 设置月新增用户的折线图
 */
function increaseMonth(name, map) {
    var model_increaseTrend_month = echarts.init(document.getElementById('model-increaseTrend-month'));
    var option = getSingleLineOption(name, map.dateMonth, map.installationMonth);   //月新增用户的横轴和纵轴

    model_increaseTrend_month.setOption(option);
}

function setDataTableDay(map) {
    var activeFormDay = map.activeFormDay;
    for(var i = 0; i < activeFormDay.length; i++) {
        activeFormDay[i] = decimalToPercent(activeFormDay[i], 2);
    }
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dateDay,activeFormDay);

    jQuery("#increase-trend-day").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "日期"},
            {"title": "新增用户占比"},
        ],
        "columnDefs": tableColDefPer( 1)
    });
}

function setDataTableWeek(map) {
    var activeFormWeek = map.activeFormWeek;
    for(var i = 0; i < activeFormWeek.length; i++) {
        activeFormWeek[i] = decimalToPercent(activeFormWeek[i], 2);
    }
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dateWeek,activeFormWeek);

    jQuery("#increase-trend-week").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "日期"},
            {"title": "新增用户占比"},
        ],
        "columnDefs": tableColDefPer( 1)
    });
}

function setDataTableMonth(map) {
    var activeFormMonth = map.activeFormMonth;
    for(var i = 0; i < activeFormMonth.length; i++) {
        activeFormMonth[i] = decimalToPercent(activeFormMonth[i], 2);
    }
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dateMonth,activeFormMonth);

    jQuery("#increase-trend-month").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "日期"},
            {"title": "新增用户占比"},
        ],
        "columnDefs": tableColDefPer( 1)
    });
}

/**
 * 设置次日留存率的折线图
 */
function retentionRate(name, map) {
    var model_increaseQuality = echarts.init(document.getElementById('model-increaseQuality'));
    var option = getSingleLineOption(name, map.dateDay, map.retentionData);   //天新增用户的横轴和纵轴

    model_increaseQuality.setOption(option);
}

function retentionDataTable(map) {
    var retentionRate = map.retentionData;
    for(var i = 0; i < retentionRate.length; i++) {
        retentionRate[i] = decimalToPercent(retentionRate[i], 2);
    }
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dateDay,retentionRate);

    jQuery("#increase-quality").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "日期"},
            {"title": "次日留存率"},
        ],
        "columnDefs": tableColDefPer( 1)
    });
}

function generateAll() {
    var url = "/getInstallationData";
    var map = getData(url, getCondition());    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
    var name = "新增用户";
    increaseDay(name, map);      //设置天折线图
    increaseWeek(name, map);     //设置周折线图
    increaseMonth(name, map);    //设置月折线图

    setDataTableDay(map);
    setDataTableWeek(map);
    setDataTableMonth(map);


    var url2 = "/getRetentionDataDay";
    var map2 = getData(url2, getCondition());    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
    var name2 = "次日留存率";
    console.log(map2);
    retentionRate(name2, map2);      //次日留存率发
    retentionDataTable(map2);
}

/**
 * 当页面载入时就自动调用该函数
 */
$(function () {
    $('#date-range').daterangepicker({
        "startDate": moment().subtract("days", 7),
        "endDate": moment(),
    });

    generateAll();
});


$('#list-model-day-list').click(function() {
    document.getElementById("table-day-container").style.display="";
    document.getElementById("table-week-container").style.display="none";
    document.getElementById("table-month-container").style.display="none";
});


$('#list-model-week-list').click(function() {
    document.getElementById("table-day-container").style.display="none";
    document.getElementById("table-week-container").style.display="";
    document.getElementById("table-month-container").style.display="none";
});

$('#list-model-month-list').click(function() {
    document.getElementById("table-day-container").style.display="none";
    document.getElementById("table-week-container").style.display="none";
    document.getElementById("table-month-container").style.display="";
});
