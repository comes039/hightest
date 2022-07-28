package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCColumnData
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*

class ColumnChart {

    companion object {
        fun options(columnData: HCColumnData): HIOptions {
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
            chart.marginTop = 20
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val xAxis = HIXAxis()
            xAxis.categories = columnData.xAxis.category
            xAxis.lineWidth = 0
            options.xAxis = ArrayList(Collections.singletonList(xAxis))


            val yAxis = HIYAxis()
            yAxis.visible = columnData.yAxis.visible
            yAxis.opposite = columnData.yAxis.opposite
            yAxis.title = HITitle()
            yAxis.title.apply {
                text = columnData.yAxis.titleText
                align = "high"
                offset = 0
                rotation = 0
                y = -10
            }
            yAxis.tickInterval = columnData.yAxis.tickInterval
            yAxis.gridLineWidth = columnData.yAxis.gridLineWidth
            yAxis.gridLineDashStyle = columnData.yAxis.gridLineDashStyle
            if (columnData.yAxis.plotLine != null) {
                val plotLine = HIPlotLines()
                plotLine.apply {
                    zIndex = 200
                    dashStyle = columnData.yAxis.plotLine.dashStyle
                    color = "#${columnData.yAxis.plotLine.lineColor}"
                    width = columnData.yAxis.plotLine.lineWidth
                    value = columnData.yAxis.plotLine.lineValue
                    label = HILabel()
                    label.apply {
                        align = columnData.yAxis.plotLine.labelAlign
                        text = columnData.yAxis.plotLine.labelText
                        style = HICSSObject()
                        style.color =
                            HIColor.initWithHexValue(columnData.yAxis.plotLine.labelColor)
                    }
                }
                yAxis.plotLines = arrayListOf(plotLine)
            }
            options.yAxis = ArrayList(Collections.singletonList(yAxis))

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            val tooltip = HITooltip()
            tooltip.shared = true
            tooltip.headerFormat = "<b>{point.x}</b><br/>"
            tooltip.pointFormat = "{point.y}"
            options.tooltip = tooltip

            val plotoptions = HIPlotOptions()
            plotoptions.column = HIColumn()
            options.plotOptions = plotoptions

            val minValue = columnData.inputData.minOrNull() ?: 0
            val minData = HIData()
            minData.y = minValue
            minData.color = HIColor.initWithHexValue(columnData.minColor)

            val maxValue = columnData.inputData.maxOrNull()
            val maxData = HIData()
            maxData.y = maxValue
            maxData.color = HIColor.initWithHexValue(columnData.maxColor)

            val dataList = java.util.ArrayList<Any>()
            columnData.inputData.forEach {
                when (it) {
                    minValue -> {
                        dataList.add(minData)
                    }
                    maxValue -> {
                        dataList.add(maxData)
                    }
                    else -> {
                        dataList.add(it)
                    }
                }
            }
            val series1 = HIColumn()
            series1.name = ""
            series1.data = dataList
            series1.color = HIColor.initWithHexValue(columnData.color)

            options.series = ArrayList(listOf(series1))

            return options
        }
    }
}