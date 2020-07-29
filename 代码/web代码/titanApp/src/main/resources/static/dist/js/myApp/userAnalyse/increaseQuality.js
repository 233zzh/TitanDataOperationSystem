// /**
//  * 设置次日留存率的折线图
//  */
// function retentionRate(name, map) {
//     var model_increaseTrend_day = echarts.init(document.getElementById('model-increaseQuality'));
//     var option = getSingleLineOption(name, map.dateDay, map.installationDay);   //天新增用户的横轴和纵轴
//
//     model_increaseTrend_day.setOption(option);
// }
//
// function setDataTable(map) {
// }
//
// function generateAll() {
//     var url = "/getRetentionDataDay";
//     var map = getData(url, getCondition());    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
//     var name = "次日留存率";
//     console.log(map);
//     retentionRate(name, map);      //设置天折线图
//     setDataTable(map);
//
// }
// /**
//  * 当页面载入时就自动调用该函数
//  */
// $(function () {
//     $('#date-range').daterangepicker({
//         "startDate": moment().subtract("days", 7),
//         "endDate": moment(),
//     });
//
//     generateAll();
// });
