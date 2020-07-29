/*************************************************************************************/
// -->Template Name: Bootstrap Press Admin
// -->Author: Themedesigner
// -->Email: niravjoshi87@gmail.com
// -->File: c3_chart_JS
/*************************************************************************************/
$(function() {
    var o = c3.generate({
        bindto: "#row-oriented",
        size: { height: 400 },
        color: { pattern: ["#1e88e5", "#343a40", "#00acc1"] },
        data: {
            rows: [
                ["option1", "option2", "option3"],
                [300, 120, 90],
                [240, 160, 40],
                [290, 200, 50],
                [230, 160, 120],
                [300, 130, 80],
                [320, 220, 90]
            ]
        },
        grid: { y: { show: !0 } }
    });
});