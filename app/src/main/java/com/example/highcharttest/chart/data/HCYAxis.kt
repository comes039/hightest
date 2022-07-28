package com.example.highcharttest.chart.data

import com.example.highcharttest.chart.data.HCPlotLine

class HCYAxis(
    val visible: Boolean,   // true: 표시함, false: 표시안함
    val opposite: Boolean,  // false: 좌측, true: 우측
    val titleText: String,
    val tickInterval: Number?,   // Y축 라벨 표시 간격
    val gridLineWidth: Number?,  // 가이드 라인 두께
    val gridLineDashStyle: String?,  // https://jsfiddle.net/gh/get/library/pure/highcharts/highcharts/tree/master/samples/highcharts/plotoptions/series-dashstyle-all/
    val plotLine: HCPlotLine? = null
)