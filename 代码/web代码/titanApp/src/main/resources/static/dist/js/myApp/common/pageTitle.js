$.ajax({
    url:"/getAllChannel",
    type: "get",
    dataType: "json",
    async: false,
    success: function (data) {
        var list = data.channels;
        var select = jQuery("#channelSelect");
        select.empty();
        select.append('<option value=-1>全部渠道</option>');
        select.val(-1);
        for (let i = 0; i < list.length; i++) {
            select.append(getOpt(list[i]));
        }
    }
});

$.ajax({
    url:"/getAllVersion",
    type: "get",
    dataType: "json",
    async: false,
    success: function (data) {
        var list = data.versions;
        var select = jQuery("#versionSelect");
        select.empty();
        select.append('<option value=-1>全部版本</option>');
        select.val(-1);
        for (let i = 0; i < list.length; i++) {
            select.append(getOpt(list[i]));
        }
    }
});

// 根据一个 idname对象{id：..., name:...} 返回select选项的html语句
function getOpt(data) {
    return `<option value=${data.id}>${data.name}</option>`;
}

/**
 * 日期格式转化
 * @param dateStr 原字符
 * @return {string} 转化后
 */
function dateTransform(dateStr) {
    var split = dateStr.split('/');
    return split[2]+'-'+split[0]+'-'+split[1];
}

/**
 * 得到条件对象
 * @return 返回的结果
 */
function getCondition() {
    var split = jQuery("#date-range").val().split(" - ");
    var start = dateTransform(split[0]);
    var end = dateTransform(split[1]);
    return {
        start_date: start,
        end_date: end,
        version_id: jQuery("#versionSelect").val(),
        channel_id: jQuery("#channelSelect").val()
    }
}