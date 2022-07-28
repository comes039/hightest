package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCDataValueList
import com.example.highcharttest.chart.data.HCXAxis
import com.example.highcharttest.chart.data.HCYAxis
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*

class StackedColumnChart {

    companion object {
        fun options(x: HCXAxis, y: HCYAxis, inputData: List<HCDataValueList>): HIOptions {
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
            chart.marginBottom = 20
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            val tooltip = HITooltip()
            tooltip.shared = true
            options.tooltip = tooltip

            val xAxis = HIXAxis()
            xAxis.categories = x.category
            options.xAxis = ArrayList(Collections.singletonList(xAxis))

            val yAxis = HIYAxis()
            yAxis.visible = y.visible
            yAxis.opposite = y.opposite
            yAxis.title = HITitle()
            yAxis.title.text = y.titleText
            yAxis.stackLabels = HIStackLabels()
            yAxis.stackLabels.enabled = true
            options.yAxis = arrayListOf(yAxis)

            val plotOptions = HIPlotOptions()
            plotOptions.column = HIColumn()
            plotOptions.column.apply {
                stacking = "normal"
                val dataLabels = HIDataLabels()
                dataLabels.enabled = false
                setDataLabels(arrayListOf(dataLabels))
            }
            options.plotOptions = plotOptions

            val dataList = ArrayList<HISeries>()
            inputData.forEach {
                val column = HIColumn()
                column.name = it.name
                column.data = it.data
                column.color = HIColor.initWithHexValue(it.color)
                column.stack = "seizure"
                dataList.add(column)
            }
            options.series = dataList

            return options
        }
    }
}