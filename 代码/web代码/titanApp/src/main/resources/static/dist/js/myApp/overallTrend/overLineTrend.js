function installationData(name, map) {
    var model_installation_line = echarts.init(document.getElementById('model-installation-line'));

    var option = getSingleLineOption(name, map.dateDay, map.installationData);   //天新增用户的横轴和纵轴

    model_installation_line.setOption(option);
}

function activeUserData(name, map) {
    var model_activeUser_line = echarts.init(document.getElementById('model-activeUser-line'));

    var option = getSingleLineOption(name, map.dateDay, map.activeUserData);   //天新增用户的横轴和纵轴

    model_activeUser_line.setOption(option);
}

function launchData(name, map) {
    var model_launch_line = echarts.init(document.getElementById('model-launch-line'));

    var option = getSingleLineOption(name, map.dateDay, map.launchData);   //天新增用户的横轴和纵轴

    model_launch_line.setOption(option);
}

function activeFormData(name, map) {
    var model_activeForm_line = echarts.init(document.getElementById('model-activeForm-line'));

    var option = getSingleLineOption(name, map.dateDay, map.activeFormData);   //天新增用户的横轴和纵轴

    model_activeForm_line.setOption(option);
}

function setDataTable(map) {
    var oldActiveForm = map.activeFormData;
    for(var i = 0; i < oldActiveForm.length; i++) {
        oldActiveForm[i] = decimalToPercent(oldActiveForm[i], 2);
    }
    //传入要转的列，得到一个二维数组
    var allData = listsToMatrix(map.dateDay, map.installationData, map.activeUserData, map.launchData, oldActiveForm);

    jQuery("#over-datatable").dataTable({
        "destroy": true,
        "autoWidth": false,
        "data": allData,
        "column": [{"title": "日期"},
            {"title": "新增用户"},
            {"title": "活跃用户"},
            {"title": "启动次数"},
            {"title": "活跃用户构成"}
            ],
        "columnDefs": tableColDefPer( 4)
    });
}

function generateAll() {
    var url = "/getLineTrendData";
    var condition = getCondition();
    condition.version_id = -1;
    condition.channel_id = -1;
    var map = getData(url, condition);

    installationData("新增用户", map);
    activeUserData("活跃用户", map);
    launchData("启动次数", map);
    activeFormData("新增用户占比", map);
    setDataTable(map);
}


$(function () {
    $('#date-range').daterangepicker({
        "startDate": moment().subtract("days", 7),
        "endDate": moment(),
    });

    generateAll();
});


