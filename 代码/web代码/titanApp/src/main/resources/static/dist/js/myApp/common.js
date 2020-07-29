var date_range = jQuery("#date-range").val();     // 日期范围
var condition = {"start_date": "2020-03-15", "end_date": "2020-06-19", "version_id": 1, "channel_id": 1};

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

// 根据一个 idname对象{id：..., name:...} 返回select选项的html语句
function getOpt(data) {
    return `<option value=${data.id}>${data.name}</option>`;
}
