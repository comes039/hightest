package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCData
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*

class WideColumnChart {

    companion object {
        fun options(inputData: List<HCData>): HIOptions {
            val options = HIOptions()

            // export menu
            val exporting = HIExporting()
            exporting.enabled = false
            options.exporting = exporting

            // credits
            val credits = HICredits()
            credits.enabled = false
            options.credits = credits

            val chart = HIChart()
            chart.marginTop = 20
            chart.marginLeft = 20
            chart.marginRight = 20
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            val xAxis = HIXAxis()
            xAxis.type = "category"
            options.xAxis = arrayListOf(xAxis)

            val yAxis = HIYAxis()
            yAxis.visible = false
            options.yAxis = arrayListOf(yAxis)

            val dataList = ArrayList<Array<Any>>()
            val colorList = ArrayList<HIColor>()
            inputData.forEach {
                val obj = arrayOf<Any>(it.name, it.value, 1)
                dataList.add(obj)
                colorList.add(HIColor.initWithHexValue(it.color))
            }

            val series1 = HIVariwide()
            series1.data = dataList
            val dataLabels = HIDataLabels()
            dataLabels.enabled = true
            series1.dataLabels = arrayListOf(dataLabels)
            series1.colorByPoint = true
            series1.colors = colorList

            options.series = ArrayList(listOf(series1))

            return options
        }
    }
}