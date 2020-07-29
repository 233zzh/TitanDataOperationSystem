var date_range = jQuery("#date-range").val();     // 日期范围

$.ajax({
    url:"/getAllChannel",
    type: "get",
    dataType: "json",
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
