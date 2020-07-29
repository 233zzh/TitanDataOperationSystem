/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/23
 * @Time: 10:48
 * @Version: 1.0
 * @Description: Description
 */

/**
 * 用户参与度-使用间隔-使用间隔分布条形图
 */
function intervalDataBar(map) {
    var use_interval = echarts.init(document.getElementById('use_interval'));
    var option = getBarOption(map.intervalRange, map.intervalData, "启动次数");   //天新增用户的横轴和纵轴

    use_interval.setOption(option);
}


/**
 * 当页面载入时就自动调用该函式
 */

/*$(function () {
    var condition = {"start_date": "2020-03-15", "end_date": "2020-06-19", "version_id": 1, "channel_id": 1};
    var url = "/getIntervalData";
    var map = getData(condition,url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据

    // getData();          //ajax 获得数据
    intervalDataBar(map);      //设置天折线图
});*/

function setDataTable(map) {
    console.log(map);
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.intervalRange, map.intervalData);
    console.log(allData);
    toPercent(allData, 1);

    jQuery("#use-interval-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "使用间隔"},
            {"title": "启动次数"},
            {"title": "启动次数占比"},
        ],
        "columnDefs": tableColDefPer(2)
    });
}

function generateAll() {
    var url = "/getIntervalData";
    var condition = getCondition();
    condition.version_id = -1;
    condition.channel_id = -1;
    // var condition = {"start_date": '2020-05-15', "end_date": '2020-06-05', "version_id": -1, "channel_id": -1};
    var map = getData(condition, url);
    /*var condition = {"start_date": "2020-03-15", "end_date": "2020-03-23", "version_id": -1, "channel_id": -1};
    var url = "/getIntervalData";
    var map = getData(condition, url);    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
*/
    console.log(map);
    intervalDataBar(map);      //设置天折线图
    setDataTable(map);
}


$(function () {
    $('#date-range').daterangepicker({
        "startDate": moment().subtract("days", 7),
        "endDate": moment(),
    });

    generateAll();
});