/*************************************************************************************/
// -->Template Name: Bootstrap Press Admin
// -->Author: Themedesigner
// -->Email: niravjoshi87@gmail.com
// -->File: c3_chart_JS
/*************************************************************************************/
$(function() {
    var n = c3.generate({
        bindto: "#spline-chart",
        size: { height: 400 },
        point: { r: 4 },
        color: { pattern: ["#1e88e5", "#00acc1"] },
        data: {
            columns: [
                ["option1", 30, 200, 100, 250, 100, 250],
                ["option2", 130, 300, 140, 200, 150, 50]
            ],
            type: "spline"
        },
        grid: { y: { show: !0 } }
    });
});