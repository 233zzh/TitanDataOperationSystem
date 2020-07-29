/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/24
 * @Time: 18:16
 * @Version: 1.0
 * @Description: Description
 */

/**
 * 设置新增用户版本分布
 * @param map 后端请求到的数据
 */
function installationTop10Channels(map) {
    var model_installation_version = echarts.init(document.getElementById("channels-installation-line"));

    var date = map.commonDate.dateDay;  //公共日期
    var installationData = map.installationData;    //新增用户相关的数据
    var nameList = installationData.nameList; //新增用户的top10版本名称
    var series = [];    //用于设置 Echarts Option 的series

    //设置series
    for (var i = 0, len = nameList.length; i < len; i++) {
        var top = "top" + (i + 1);  //后端传来的数据是 {“top1"：[.....], "top2": [.....], "top3": [.....], ....} 这种格式的
        series[i] = {
            name: nameList[i],
            type: 'line',
            data: installationData[top]
        };
    }

    var option = getMultiLineOption(date, nameList, series);
    model_installation_version.setOption(option);
}

/**
 * 设置活跃用户版本分布
 * @param map 后端请求到的数据
 */
function activeUserTop10Channels(map) {
    var model_active_version = echarts.init(document.getElementById("channels-activeUser-line"));

    var date = map.commonDate.dateDay;  //公共日期
    var activeData = map.activeData;    //新增用户相关的数据
    var nameList = activeData.nameList; //新增用户的top10版本名称
    var series = [];    //用于设置 Echarts Option 的series

    //设置series
    for (var i = 0, len = nameList.length; i < len; i++) {
        var top = "top" + (i + 1);  //后端传来的数据是 {“top1"：[.....], "top2": [.....], "top3": [.....], ....} 这种格式的
        series[i] = {
            name: nameList[i],
            type: 'line',
            data: activeData[top]
        };
    }

    var option = getMultiLineOption(date, nameList, series);
    model_active_version.setOption(option);
}

/**
 * 设置启动次数版本分布
 * @param map 后端请求到的数据
 */
function launchTop10Channels(map) {
    var model_launch_version = echarts.init(document.getElementById("channels-launch-line"));

    var date = map.commonDate.dateDay;  //公共日期
    var launchData = map.launchData;    //新增用户相关的数据
    var nameList = launchData.nameList; //新增用户的top10版本名称
    var series = [];    //用于设置 Echarts Option 的series

    //设置series
    for (var i = 0, len = nameList.length; i < len; i++) {
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
 * 加载页面时自动执行
 */

/*$(function () {
    var condition = {"start_date": "2020-03-15", "end_date": "2020-03-23", "version_id": -1, "channel_id": -1};
    var url = "/getTop10ChannelsData";
    var map = getData(condition, url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
    console.log(map);
    installationTop10Channels(map);
    activeUserTop10Channels(map);
    launchTop10Channels(map);
});*/


function setDataTable(map) {
    console.log(map);
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.datatable.nameTable, map.datatable.installationTable, map.datatable.activeTable, map.datatable.sumUserTable);
    console.log(allData);
    toPercent(allData, 3);

    jQuery("#channel-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "渠道名称"},
            {"title": "新增用户"},
            {"title": "活跃用户"},
            {"title": "累计用户"},
            {"title": "累计用户占比"},
        ],
        "columnDefs": tableColDefPer(4)
    });
}

function generateAll() {
    var url = "/getTop10ChannelsData";
    var condition = getCondition();
    condition.version_id = -1;
    condition.channel_id = -1;
    // var condition = {"start_date": '2020-05-15', "end_date": '2020-06-05', "version_id": -1, "channel_id": -1};
    var map = getData(condition,url);
    /*var condition = {"start_date": "2020-03-15", "end_date": "2020-03-23", "version_id": -1, "channel_id": -1};
    var url = "/getTop10ChannelsData";
    var map = getData(condition, url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据*/

    console.log(map);
    installationTop10Channels(map);
    activeUserTop10Channels(map);
    launchTop10Channels(map);
    setDataTable(map);
}


$(function () {
    $('#date-range').daterangepicker({
        "startDate": moment().subtract("days", 7),
        "endDate": moment(),
    });

    generateAll();
});

