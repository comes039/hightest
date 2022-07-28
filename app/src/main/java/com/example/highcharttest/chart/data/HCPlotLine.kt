package com.example.highcharttest.chart.data

class HCPlotLine(
    val dashStyle: String,  // https://jsfiddle.net/gh/get/library/pure/highcharts/highcharts/tree/master/samples/highcharts/plotoptions/series-dashstyle-all/
    val lineColor: String,  // 예> "46A0F4" RRGGBB
    val lineWidth: Number,  // 선 두께
    val lineValue: Number,  // 선이 표시될 위치 값
    val labelAlign: String, // "left", "center", "right"
    val labelText: String,  // 선에 표시될 텍스트
    val labelColor: String  // 예> "46A0F4" RRGGBB
)