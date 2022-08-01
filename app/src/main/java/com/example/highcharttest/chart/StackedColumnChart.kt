package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCDataValueList
import com.example.highcharttest.chart.data.HCXAxis
import com.example.highcharttest.chart.data.HCYAxis
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.HIGradient
import com.highsoft.highcharts.common.HIStop
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
            // chart
            val chart = HIChart()
            chart.marginTop = 20
            chart.marginBottom = 20
            options.chart = chart
            // title
            val title = HITitle()
            title.text = ""
            options.title = title
            // subtitle
            val subtitle = HISubtitle()
            subtitle.text = ""
            options.subtitle = subtitle
            // legend
            val legend = HILegend()
            legend.enabled = false
            options.legend = legend
            // tooltip
            val tooltip = HITooltip()
            tooltip.shared = true
            val toolTipBackgroundColor = HIColor.initWithHexValue("161b22")
            tooltip.borderColor = HIColor.initWithHexValue("00ff0000")
            tooltip.borderRadius = 8
            tooltip.shadow = HIShadowOptionsObject()
            tooltip.shadow.width = 0
            tooltip.style = HICSSObject()
            tooltip.style.width = 76
            tooltip.style.color = HIColor.initWithHexValue("FFFFFF")
            tooltip.backgroundColor = toolTipBackgroundColor
            tooltip.headerFormat =
                "<span style=\"color:#888888;font-size:10px\">{series.name}</span><br>"
            tooltip.pointFormat =
                "<span style=\"color:{point.color};font-size:12px;font-weight:bold\">• </span>" +
                        "<span style=\"color:#FFFFFF;font-size:12px;font-weight:500\">{point.y:.2f}</span>"

            options.tooltip = tooltip
            // xAxis
            val xAxis = HIXAxis()
            xAxis.categories = x.category
            options.xAxis = ArrayList(Collections.singletonList(xAxis))
            // yAxis
            val yAxis = HIYAxis()
            yAxis.visible = y.visible
            yAxis.opposite = y.opposite
            yAxis.title = HITitle()
            yAxis.title.text = y.titleText
            yAxis.stackLabels = HIStackLabels()
            yAxis.stackLabels.enabled = true
            options.yAxis = arrayListOf(yAxis)
            // plotOptions
            val plotOptions = HIPlotOptions()
            plotOptions.column = HIColumn()
            plotOptions.column.apply {
                stacking = "normal"
                val dataLabels = HIDataLabels()
                dataLabels.enabled = false
                setDataLabels(arrayListOf(dataLabels))
            }
            options.plotOptions = plotOptions
            // data
            val dataList = ArrayList<HISeries>()
            inputData.forEach {
                val column = HIColumn()
                column.name = it.name
                column.data = it.data
                val hiGradient = HIGradient(0F, 0F, 0F, 1F)
                val stops: LinkedList<HIStop> = LinkedList()
                stops.add(HIStop(0f, HIColor.initWithHexValue("F55C5C")))
                stops.add(HIStop(1f, HIColor.initWithHexValue("F08B8B")))
                column.color = HIColor.initWithLinearGradient(hiGradient, stops)
                column.colorByPoint = false
                column.stack = "seizure"
                dataList.add(column)
            }
            options.series = dataList

            return options
        }
    }
}