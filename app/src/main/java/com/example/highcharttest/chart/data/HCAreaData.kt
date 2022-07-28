package com.example.highcharttest.chart.data

import com.example.highcharttest.chart.data.GradientColor

class HCAreaData(
    val name: String,
    val data: ArrayList<Any>,
    val color: String,
    val fillColor: GradientColor? = null
)