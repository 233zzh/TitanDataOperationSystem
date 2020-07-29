/*************************************************************************************/
// -->Template Name: Bootstrap Press Admin
// -->Author: Themedesigner
// -->Email: niravjoshi87@gmail.com
// -->File: c3_chart_JS
/*************************************************************************************/
$(function() {
    var n = c3.generate({
        bindto: "#column-oriented",
        size: { height: 400 },
        color: { pattern: ["#1e88e5", "#00acc1", "#fc4b6c"] },
        data: {
            columns: [
                ["option1", 50, 60, 40, 50, 20, 30],
                ["option2", 220, 130, 240, 90, 130, 200],
                ["option3", 250, 250, 400, 160, 200, 300]
            ]
        },
        grid: { y: { show: !0 } }
    });
});