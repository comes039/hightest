package com.example.highcharttest.chart.data

class ReportOtherSymptomResponse(
    val totalCount: Number,
    val beforeTotalCount : Number,
    val totalCountAvg: Number,
    val beforeTotalCountAvg: Number,
    val totalList: List<ReportOtherSymptomInfo>,
    val others: ReportOtherSymptomInfo
)