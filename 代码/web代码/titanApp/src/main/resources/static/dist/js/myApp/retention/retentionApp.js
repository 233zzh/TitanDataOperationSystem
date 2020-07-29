// 表格数据
var data_increase_day_num = [];
var data_increase_week_num = [];
var data_increase_month_num = [];
var data_active_day_num = [];
var data_active_week_num = [];
var data_active_month_num = [];

var data_increase_day_per = [];
var data_increase_week_per = [];
var data_increase_month_per = [];
var data_active_day_per = [];
var data_active_week_per = [];
var data_active_month_per = [];

// 全局控制显示的变量
var time = -1; // 时间间隔
var active = false; // 是否显示活跃用户留存

// 留存趋势变化折线图
var retention_trend_line = echarts.init(document.getElementById('retention-trend'));


// 选项
const opt_day = [{id: 2, name: "1天后"}, {id: 4, name: "3天后"}, {id: 8, name: "7天后"}, {id: 10, name: "30天后"}];
const opt_week = [{id: 2, name: "1周后"}, {id: 3, name: "2周后"}, {id: 4, name: "3周后"}];
const opt_month = [{id: 2, name: "1月后"}, {id: 3, name: "2月后"}, {id: 4, name: "3月后"}];

// 配置信息
var retention_trend_line_option = {
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params) {
            return params[0].name + '<br />' +params[0].seriesName + ': ' + params[0].value + '%';
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            axisLabel: {
                    formatter: '{value}%'
            }
        }
    ],
    series: [
        {
            name: '留存率',
            type: 'line',
            barWidth: '40%',
        }
    ]
};

// 表格
var table_num = jQuery('#retention-datatable-num');
var table_per = jQuery('#retention-datatable-per');

// 表格头部
var title_day = [{"title": "日期"}, {"title": "总人数"}, {"title": "1天后"}, {"title": "2天后"}, {"title": "3天后"}, {"title": "4天后"}, {"title": "5天后"}, {"title": "6天后"}, {"title": "7天后"}, {"title": "14天后"}, {"title": "30天后"}];
var title_week = [{"title": "日期"}, {"title": "总人数"}, {"title": "1周后"}, {"title": "2周后"}, {"title": "3周后"}, {"title": "4周后"}, {"title": "5周后"}, {"title": "6周后"}, {"title": "7周后"}, {"title": "8周后"}, {"title": "9周后"}];
var title_month = [{"title": "日期"}, {"title": "总人数"}, {"title": "1月后"}, {"title": "2月后"}, {"title": "3月后"}, {"title": "4月后"}, {"title": "5月后"}, {"title": "6月后"}, {"title": "7月后"}, {"title": "8月后"}, {"title": "9月后"}];

// 初始化表格
retention_trend_line.setOption(retention_trend_line_option);

const select_range = $('#rangeSelect');

function switchAll() {
    if (active) {
        if (time===1) renderTable(data_active_day_num, data_active_day_per);
        else if (time===2) renderTable(data_active_week_num, data_active_week_per);
        else renderTable(data_active_month_num, data_active_month_per);
    }else {
        if (time===1) renderTable(data_increase_day_num,data_increase_day_per);
        else if (time===2) renderTable(data_increase_week_num,data_increase_week_per);
        else renderTable(data_increase_month_num, data_increase_month_per);
    }
}

function switchLine() {
    if (active) {
        if (time===1) showLine( data_active_day_per);
        else if (time===2) showLine(data_active_week_per);
        else showLine(data_active_month_per);
    }else {
        if (time===1) showLine(data_increase_day_per);
        else if (time===2) showLine(data_increase_week_per);
        else showLine(data_increase_month_per);
    }
}

function getTitleByTime() {
    if (time===1) return title_day;
    else if(time===2) return title_week;
    else return title_month;
}

function renderTable(data_num,data_per) {
    table_num.dataTable({
        "destroy": true,
        "ordering": false,
        "searching": false,
        "autoWidth": false,
        "lengthChange": false,
        "data": data_num,
        "columns": getTitleByTime()
    });
    table_per.dataTable({
        "destroy": true,
        "ordering": false,
        "searching": false,
        "autoWidth": false,
        "lengthChange": false,
        "data": data_per,
        "columns": getTitleByTime(),
        "columnDefs": tableColDefColor(2,11)
    });
    showLine(data_per);
}

function showLine(data) {
    const index = select_range.val();
    retention_trend_line.setOption({
        xAxis: [{
            data: getColumn(data, 0)
        }],
        series: [{
            data: getColumn(data, index)
        }]
    })
}

function changeOption() {
    select_range.empty();
    if (time===1) {
        for (item of opt_day) {
            select_range.append(getOpt(item))
        } 
    }else if (time===2){
        for (item of opt_week) {
            select_range.append(getOpt(item))
        }
    }else {
        for (item of opt_month) {
            select_range.append(getOpt(item))
        }
    }
    select_range.val(2);
}

function generateAll(){
    $.ajax({
        url:"/getRetentionData",
        type: "get",
        data: getCondition(),
        dataType: "json",
        success: function (data) {
            data_increase_day_num = data.dataIncreaseDay;
            addColumn(data_increase_day_num, data.increase.dateDay, 0);
            data_increase_day_per = addSumAndPercent(data_increase_day_num, data.increase.installationDay, 1);

            data_active_day_num = data.dataActiveDay;
            addColumn(data_active_day_num, data.active.dateDay, 0);
            data_active_day_per = addSumAndPercent(data_active_day_num, data.active.activeTrendDay, 1);

            if (data.increase.dateWeek.length!==0) {
                $('#list-retention-week-list').removeClass('disabled');
                data_increase_week_num = data.dataIncreaseWeek;
                addColumn(data_increase_week_num, data.increase.dateWeek, 0);
                data_increase_week_per = addSumAndPercent(data_increase_week_num, data.increase.installationWeek, 1);

                data_active_week_num = data.dataActiveWeek;
                addColumn(data_active_week_num, data.active.dateWeek, 0);
                data_active_week_per = addSumAndPercent(data_active_week_num, data.active.activeTrendWeek, 1);
            }else {
                $('#list-retention-week-list').addClass('disabled');
            }

            if (data.increase.dateMonth.length!==0) {
                $('#list-retention-month-list').removeClass('disabled');
                data_increase_month_num = data.dataIncreaseMonth;
                addColumn(data_increase_month_num, data.increase.dateMonth, 0);
                data_increase_month_per = addSumAndPercent(data_increase_month_num, data.increase.installationMonth, 1);

                data_active_month_num = data.dataActiveMonth;
                addColumn(data_active_month_num, data.active.dateMonth, 0);
                data_active_month_per = addSumAndPercent(data_active_month_num, data.active.activeTrendMonth, 1);
            }else {
                $('#list-retention-month-list').addClass('disabled');
            }
            time = -1;
            document.getElementById('list-retention-day-list').click();
        }
    })
}

select_range.change(switchLine);

$('#list-retention-increaseNum-list').click(function () {
    if (!active) return;
    active = false;
    switchAll();
});

$('#list-retention-activeNum-list').click(function () {
    if (active) return;
    active = true;
    switchAll();
});

$('#list-retention-week-list').click(function () {
   if (time===2) return;
   time = 2;
   changeOption();
   switchAll();
});

$('#list-retention-month-list').click(function () {
   if (time===3) return;
   time = 3;
   changeOption();
   switchAll();
});

$('#list-retention-day-list').click(function () {
   if (time===1) return;
   time = 1;
   changeOption();
   switchAll();
});

$(document).ready(function () {
    jQuery("#date-range").val('06/04/2020 - 06/14/2020');
    generateAll();
});