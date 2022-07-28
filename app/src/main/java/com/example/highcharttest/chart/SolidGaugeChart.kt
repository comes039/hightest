package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCGaugeData
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*

class SolidGaugeChart {

    companion object {
        fun options(gaugeData: HCGaugeData): HIOptions {
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
            chart.type = "solidgauge"
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            /*val tooltip = HITooltip()
            tooltip.headerFormat = ""
            //tooltip.pointFormat = "{series.name}: <b>{point.percentage:.1f}%</b>"
            tooltip.pointFormat = "{point.name}: <b>{point.percentage:.1f}%</b>"
            options.tooltip = tooltip*/

            val paneBackground = HIBackground()
            paneBackground.apply {
                outerRadius = "100%"
                innerRadius = "0%"
                backgroundColor = HIColor.initWithHexValue(gaugeData.backgroundColor)
                borderWidth = 0
            }

            val pane = HIPane()
            pane.startAngle = 0
            pane.endAngle = 360
            pane.background = arrayListOf(paneBackground)
            options.pane = pane

            val yAxis = HIYAxis()
            yAxis.apply {
                visible = false
                min = 0
                max = 24 * 60
                lineWidth = 0
                setTickPositions()
            }
            options.yAxis = arrayListOf(yAxis)

            val dataLabels = HIDataLabels()
            dataLabels.enabled = false

            val plotOptions = HIPlotOptions()
            plotOptions.solidgauge = HISolidgauge()
            plotOptions.solidgauge.dataLabels = arrayListOf(dataLabels)
            options.plotOptions = plotOptions

            val data = HIData()
            data.color = HIColor.initWithHexValue(gaugeData.inputData.color)
            data.radius = "112%"
            data.innerRadius = "0%"
            data.y = gaugeData.inputData.value

            val solidGauge = HISolidgauge()
            solidGauge.name = gaugeData.inputData.name
            solidGauge.data = arrayListOf(data)

            options.series = arrayListOf(solidGauge)

            return options
        }
    }
}