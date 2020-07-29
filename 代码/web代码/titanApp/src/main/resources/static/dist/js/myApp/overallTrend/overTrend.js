
/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/18
 * @Time: 21:30
 * @Version: 1.0
 * @Description: 设置整体趋势中的概况部分的数据
 */


function trendData(map)
{
      $('#installationAvgCurrent').text(map.installationAvg.currentNum);
      $('#installationAvgCompare').text("同比 " + map.installationAvg.compare);

      $('#activeUserAvgCurrent').text(map.activeUserAvg.currentNum);
      $('#activeUserAvgCompare').text("同比 " + map.activeUserAvg.compare);

      $('#activeUserSumCurrent_1').text(map.activeUserSum_1.currentNum);    //日期平均
      $('#activeUserSumCompare_1').text("同比 " + map.activeUserSum_1.compare);

      $('#activeUserSumCurrent_2').text(map.activeUserSum_2.currentNum);    //30 日平均
      $('#activeUserSumCompare_2').text("同比 " + map.activeUserSum_2.compare);

      $('#retentionAvgCurrent').text(map.retentionAvg.currentNum);
      $('#retentionAvgCompare').text(map.retentionAvg.compare);

      $('#userSum').text(map.userSum.currentNum)
}

$(function () {
   var url = "/getOverTrendData";
   var map = getData(url, null);
   console.log(map);

   trendData(map);
});

