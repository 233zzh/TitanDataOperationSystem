/**
 * Created by IntelliJ IDEA.
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/21
 * @Time: 19:09
 * @Version: 1.0
 * @Description: 设置活跃用户分析所需的图表
 */

function activeTrendDay(name, map) {
   var model_activeTrend_day = echarts.init(document.getElementById('model-activeTrend-day'));
   var option = getSingleLineOption(name, map.dateDay, map.activeTrendDay);   //天新增用户的横轴和纵轴

   model_activeTrend_day.setOption(option);
}

function activeTrendWeek(name, map) {
   var model_activeTrend_week = echarts.init(document.getElementById('model-activeTrend-week'));
   var option = getSingleLineOption(name, map.dateWeek, map.activeTrendWeek);   //天新增用户的横轴和纵轴

   model_activeTrend_week.setOption(option);
}

function activeTrendMonth(name, map) {
   var model_activeTrend_month = echarts.init(document.getElementById('model-activeTrend-month'));
   var option = getSingleLineOption(name, map.dateMonth, map.activeTrendMonth);   //天新增用户的横轴和纵轴

   model_activeTrend_month.setOption(option);
}


function activeFormDay(name, map) {
   var model_activeForm_day = echarts.init(document.getElementById('model-activeForm-day'));
   var option = getSingleLineOption(name, map.dateDay, map.activeFormDay);   //天新增用户的横轴和纵轴

   model_activeForm_day.setOption(option);
}

function activeFormWeek(name, map) {
   var model_activeForm_week = echarts.init(document.getElementById('model-activeForm-week'));
   var option = getSingleLineOption(name, map.dateWeek, map.activeFormWeek);   //天新增用户的横轴和纵轴

   model_activeForm_week.setOption(option);
}

function activeFormMonth(name, map) {
   var model_activeForm_month = echarts.init(document.getElementById('model-activeForm-month'));
   var option = getSingleLineOption(name, map.dateMonth, map.activeFormMonth);   //天新增用户的横轴和纵轴

   model_activeForm_month.setOption(option);
}

function activeVicosity(map) {
   var model_activeVicosity = echarts.init(document.getElementById('model-activeVicosity'));

   var date = map.dateDay;
   var nameList = ["DAU / 过去7日活跃用户", "DAU / 过去30日活跃用户"];
   var series = [
      {
         name: nameList[0],
         type: 'line',
         data: map.activeVicosityWeek
      },
      {
         name: nameList[1],
         type: 'line',
         data: map.activeVicosityMonth
      }
   ];
   var option = getMultiLineOption(date, nameList, series);

   model_activeVicosity.setOption(option);
}

function activeRateWeek(name, map) {
   var model_activeRate_week = echarts.init(document.getElementById('model-activeRate-week'));
   var option = getSingleLineOption(name, map.dateWeek, map.activeRateWeek);

   model_activeRate_week.setOption(option);
}

function activeRateMonth(name, map) {
   var model_activeRate_month = echarts.init(document.getElementById('model-activeRate-month'));
   var option = getSingleLineOption(name, map.dateMonth, map.activeRateMonth);

   model_activeRate_month.setOption(option);
}


function setDataTableDay(map) {
   var activeFormDay = map.activeFormDay;
   var activeVicosityWeek = map.activeVicosityWeek;
   var activeVicosityMonth = map.activeVicosityMonth;

   for(var i = 0; i < activeFormDay.length; i++) {
      activeFormDay[i] = decimalToPercent(activeFormDay[i], 2);
      activeVicosityWeek[i] = decimalToPercent(activeVicosityWeek[i], 2);
      activeVicosityMonth[i] = decimalToPercent(activeVicosityMonth[i], 2);

   }
   //传入要转的列，得到一个二维数组
   var allData = listsToMatrix(map.dateDay,map.activeTrendDay ,activeFormDay, activeVicosityWeek, activeVicosityMonth);
   console.log(allData);

   jQuery("#active-day").dataTable({
      "destroy": true,
      "autoWidth": false,
      "data": allData,
      "column": [{"title": "日期"},
         {"title": "新增用户占比"},
         {"title": "活跃构成(新用户占比)"},
         {"title": "DAU/过去7日活跃用户"},
         {"title": "DAU/过去30日活跃用户"},
      ],
      "columnDefs": tableColDefPer( 2, 3, 4)
   });
}

function setDataTableWeek(map) {
   var activeFormWeek = map.activeFormWeek;
   var activeRateWeek = map.activeRateWeek;

   for(var i = 0; i < activeFormWeek.length; i++) {
      activeFormWeek[i] = decimalToPercent(activeFormWeek[i], 2);
      activeRateWeek[i] = decimalToPercent(activeRateWeek[i], 2);
   }
   //传入要转的列，得到一个二维数组
   var allData = listsToMatrix(map.dateWeek,map.activeTrendWeek,activeFormWeek, activeRateWeek);
   console.log(allData);

   jQuery("#active-week").dataTable({
      "destroy": true,
      "autoWidth": false,
      "data": allData,
      "column": [{"title": "日期"},
         {"title": "新增用户占比"},
         {"title": "活跃构成(新用户占比)"},
         {"title": "用户周活跃率"},
      ],
      "columnDefs": tableColDefPer( 2, 3)
   });
}

function setDataTableMonth(map) {
   var activeFormMonth = map.activeFormMonth;
   var activeRateMonth = map.activeRateMonth;

   for(var i = 0; i < activeFormMonth.length; i++) {
      activeFormMonth[i] = decimalToPercent(activeFormMonth[i], 2);
      activeRateMonth[i] = decimalToPercent(activeRateMonth[i], 2);
   }
   //传入要转的列，得到一个二维数组
   var allData = listsToMatrix(map.dateMonth,map.activeTrendMonth,activeFormMonth, activeRateMonth);
   console.log(allData);

   jQuery("#active-month").dataTable({
      "destroy": true,
      "autoWidth": false,
      "data": allData,
      "column": [{"title": "日期"},
         {"title": "新增用户占比"},
         {"title": "活跃构成(新用户占比)"},
         {"title": "用户月活跃率"},
      ],
      "columnDefs": tableColDefPer( 2, 3)
   });


}
function generateAll() {
   var url = "/getActiveData";
   var map = getData(url, getCondition());    //map 就是 controller 发来的数据，里面包含了天、周、月对应的 x 周和 y 轴数据
   console.log(map);
   //活跃趋势天、周、月
   var activeTrend = "活跃用户";
   activeTrendDay(activeTrend, map);
   activeTrendWeek(activeTrend, map);
   activeTrendMonth(activeTrend, map);
   //活跃构成天、周、月
   var activeForm = "新增用户占比";
   activeFormDay(activeForm, map);
   activeFormWeek(activeForm, map);
   activeFormMonth(activeForm, map);
   //活跃粘度
   activeVicosity(map);
   //周活跃率
   var rateWeek = "周活跃率";
   activeRateWeek(rateWeek, map);
   //月活跃率
   var rateMonth = "月活跃率";
   activeRateMonth(rateMonth, map);

   setDataTableDay(map);
   setDataTableWeek(map);
   setDataTableMonth(map);
}

$(function () {
   $('#date-range').daterangepicker({
      "startDate": moment().subtract("days", 7),
      "endDate": moment(),
   });

   generateAll();
});

$('.day').click(function() {
   document.getElementById("table-day-container").style.display="";
   document.getElementById("table-week-container").style.display="none";
   document.getElementById("table-month-container").style.display="none";
});


$('.week').click(function() {
   document.getElementById("table-day-container").style.display="none";
   document.getElementById("table-week-container").style.display="";
   document.getElementById("table-month-container").style.display="none";
});

$('.month').click(function() {
   document.getElementById("table-day-container").style.display="none";
   document.getElementById("table-week-container").style.display="none";
   document.getElementById("table-month-container").style.display="";
});
