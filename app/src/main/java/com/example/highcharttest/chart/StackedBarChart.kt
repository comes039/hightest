package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCData
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*

class StackedBarChart {

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
            chart.type = "column"
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            val xAxis = HIXAxis()
            xAxis.categories = arrayListOf(
                "Major trigger"
            )
            xAxis.visible = false
            xAxis.lineWidth = 0
            options.xAxis = ArrayList(Collections.singletonList(xAxis))

            val yAxis = HIYAxis()
            yAxis.visible = false
            yAxis.min = 0
            options.yAxis = arrayListOf(yAxis)

            val plotOptions = HIPlotOptions()
            plotOptions.series = HISeries()
            plotOptions.series.stacking = "percent"
            options.plotOptions = plotOptions

            val dataList = ArrayList<HISeries>()
            inputData.forEach {
                val data = HIBar()
                data.name = it.name
                data.data = arrayListOf(it.value)
                data.color = HIColor.initWithHexValue(it.color)
                dataList.add(data)
            }
            options.series = dataList

            return options
        }
    }
}