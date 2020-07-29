/**
 * Created by IntelliJ IDEA.
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/24
 * @Time: 11:03
 * @Version: 1.0
 * @Description: 检测按钮是否活跃
 */

$('#list-model-trend-activeUser-list').click(function () {
    console.log($('#list-model-trend-activeUser-list').attr('class'));
    console.log($('#list-model-activeTrend-day-list').attr('class'));
    $('#list-model-activeTrend-day-list').removeClass("active");
    $('#list-model-activeTrend-day-list').click();
});

$('#list-model-form-activeUser-list').click(function () {
    $('#list-model-activeForm-day-list').removeClass("active");
    $('#list-model-activeForm-day-list').click();
});

$('#list-model-viscosity-activeUser-list').click(function () {
    $('#list-model-activeVicosity-day-list').removeClass("active");
    $('#list-model-activeVicosity-day-list').click();
});

$('#list-model-weekActive-activeUser-list').click(function () {
    $('#list-model-weekActive-list').removeClass("active");
    $('#list-model-weekActive-list').click();

});

$('#list-model-monthActive-activeUser-list').click(function () {
    $('#list-model-monthActive-list').removeClass("active");
    $('#list-model-monthActive-list').click();
    console.log($('#list-model-monthActive-list').attr('class'))

});

