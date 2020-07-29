/**
 * Created by IntelliJ IDEA.
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/22
 * @Time: 16:01
 * @Version: 1.0
 * @Description: 版本分布信息
 */

/**
 * 设置新增用户版本分布
 * @param map 后端请求到的数据
 */
function installationVersion(map) {
    var model_installation_version = echarts.init(document.getElementById("model-installation-versionDivision"));

    var date = map.commonDate.dateDay;  //公共日期
    var installationData = map.installationData;    //新增用户相关的数据
    var nameList = installationData.nameList; //新增用户的top10版本名称
    var series = [];    //用于设置 Echarts Option 的series

    //设置series
    for(var i = 0,len = nameList.length; i < len; i++){
        var top = "top" + (i + 1);  //后端传来的数据是 {“top1"：[.....], "top2": [.....], "top3": [.....], ....} 这种格式的
        series[i] = {
            name: nameList[i],
            type: 'line',
            data: installationData[top]
        };
    }

    var option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: nameList,
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

    model_installation_version.setOption(option);
}


/**
 * 设置活跃用户版本分布
 * @param map 后端请求到的数据
 */
function activeVersion(map) {
    var model_active_version = echarts.init(document.getElementById("model-active-versionDivision"));
    model_active_version.clear();
    var date = map.commonDate.dateDay;  //公共日期
    var activeData = map.activeData;    //新增用户相关的数据
    var nameList = activeData.nameList; //新增用户的top10版本名称
    var series = [];    //用于设置 Echarts Option 的series

    //设置series
    for(var i = 0,len = nameList.length; i < len; i++){
        var top = "top" + (i + 1);  //后端传来的数据是 {“top1"：[.....], "top2": [.....], "top3": [.....], ....} 这种格式的
        series[i] = {
            name: nameList[i],
            type: 'line',
            data: activeData[top]
        };
    }

    var option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: nameList,
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

    // var option = getMultiLineOption(date, nameList, series);
    model_active_version.setOption(option);
}

/**
 * 设置启动次数版本分布
 * @param map 后端请求到的数据
 */
function launchVersion(map) {
    var model_launch_version = echarts.init(document.getElementById("model-launch-versionDivision"));

    var date = map.commonDate.dateDay;  //公共日期
    var launchData = map.launchData;    //启动次数用户相关的数据
    var nameList = launchData.nameList; //启动次数的top10版本名称
    var series = [];    //用于设置 Echarts Option 的series

    //设置series
    for(var i = 0,len = nameList.length; i < len; i++){
        var top = "top" + (i + 1);  //后端传来的数据是 {“top1"：[.....], "top2": [.....], "top3": [.....], ....} 这种格式的
        series[i] = {
            name: nameList[i],
            type: 'line',
            data: launchData[top]
        };
    }

    var option = getMultiLineOption(date, nameList, series);
    model_launch_version.setOption(option);
}

/**
 * 设置版本分布的 DataTable
 * @param map 后端请求到的数据
 */
function setDataTable(map) {
    var overInfo = map.overInfo;

    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(overInfo.versionName, overInfo.calUserAllVersion, overInfo.installationAllVersion, overInfo.activeAllVersion, overInfo.launchAllVersion);
    toPercent(allData, 1, 3);
    console.log(allData);

    jQuery("#version-division").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "版本"},
            {"title": "截至今日版本累计用户数"},
            {"title": "截至今日版本累计用户占比"},
            {"title": "新增用户"},
            {"title": "活跃用户"},
            {"title": "活跃用户占比"},
            {"title": "启动次数"},
        ],
        "columnDefs": tableColDefPer( 2, 5)
    });
}

function generateAll() {
    var condition = getCondition();
    condition.version_id = -1;
    var url = "/getVersionData";
    var map = getData(url, condition);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
    console.log(map);
    installationVersion(map);
    activeVersion(map);
    launchVersion(map);

    setDataTable(map);
}
/**
 * 加载页面时自动执行
 */
$(function () {
    $('#date-range').daterangepicker({
        "startDate": moment().subtract("days", 7),
        "endDate": moment(),
    });

    generateAll();
});

