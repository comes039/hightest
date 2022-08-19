package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCBasicColumnData
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.HIGradient
import com.highsoft.highcharts.common.HIStop
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*


class BasicColumnChart {

    companion object {
        fun options(columnData: HCBasicColumnData): HIOptions {
            val options = HIOptions()

            // export menu
            val exporting = HIExporting()
            exporting.enabled = false
            exporting.allowHTML = true
            options.exporting = exporting

            // credits
            val credits = HICredits()
            credits.enabled = false
            options.credits = credits

            val chart = HIChart()
            chart.type = "column"
            chart.marginTop = 50
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val xAxis = HIXAxis()
            xAxis.categories = columnData.category
            xAxis.lineWidth = 0
            options.xAxis = ArrayList(Collections.singletonList(xAxis))


            val yAxis = HIYAxis()
            yAxis.visible = true
            yAxis.opposite = true
            yAxis.title = HITitle()
            yAxis.title.apply {
                text = "(entries)"
                align = "high"
                offset = 0
                rotation = 0
                y = -20
            }
            yAxis.tickInterval = 1
            yAxis.gridLineWidth = 1
            yAxis.max = 2.5
            yAxis.gridLineDashStyle = "Solid"

            val plotLine = HIPlotLines()
            yAxis.plotLines = arrayListOf(plotLine)

            options.yAxis = ArrayList(Collections.singletonList(yAxis))

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            val tooltip = HITooltip()
            tooltip.shared = false
            tooltip.useHTML = true

            tooltip.backgroundColor = HIColor.initWithHexValue("000000")
            tooltip.borderColor = HIColor.initWithHexValue("00ff0000")

            tooltip.borderRadius = 8
            tooltip.shadow = HIShadowOptionsObject()
            tooltip.shadow.width = 0
            tooltip.style = HICSSObject()
//            tooltip.style.width = 100
//            tooltip.style.color = HIColor.initWithHexValue("FFFFFF")
//            tooltip.style.fontSize = "8px"
//            tooltip.headerFormat = "<b>{point.x}</b><br/>"
//            tooltip.pointFormat = "{point.y}</br>"
            options.tooltip = tooltip

            val plotOptions = HIPlotOptions()
            plotOptions.series = HISeries()
            options.plotOptions = plotOptions

            val dataList = ArrayList<HISeries>()
            columnData.columnData.forEach {
                val data = HISeries()
                val stops = LinkedList<HIStop>()
                stops.add(HIStop(0f, HIColor.initWithHexValue(it.gradientColor.start)))
                stops.add(HIStop(1f, HIColor.initWithHexValue(it.gradientColor.end)))
                data.color = HIColor.initWithLinearGradient(HIGradient(), stops)
                data.custom = HashMap<String, Any>()
                data.custom["value"] = it.color
                data.name = it.name
                data.data = it.inputData
                data.tooltip = HITooltip()
                data.tooltip.useHTML = true
                data.tooltip.backgroundColor = HIColor.initWithHexValue("000000")
                data.tooltip.headerFormat = ""
                data.tooltip.pointFormat = "<div style=\"font-size:12px;color:#FFFFFF\">{series.name}</div>" +
                        "<div>" +
                        "<li style=\"font-size:12px;color:#{series.options.custom.value}\">" +
                        "<span style=\"font-size:12px;color:#FFFFFF\">{point.y}</span>" +
                        "</li>" +
                        "</div>"
                dataList.add(data)
            }
            val spline = HISpline()
            spline.name = "Clinic day\n"
            spline.data = columnData.splineData
            spline.marker = HIMarker()
            spline.marker.lineWidth = 1
            spline.marker.fillColor = HIColor.initWithHexValue("CCCCCC")
            spline.color = HIColor.initWithHexValue("00ff0000")
            spline.tooltip = HITooltip()
            spline.tooltip.useHTML = true
            spline.tooltip.headerFormat = ""
            spline.tooltip.pointFormat =
                "<div style=\"width:49px;text-align:center\"><span style=\"font-size:10px;color:#FFFFFF\">{series.name}</span></div>"
            spline.tooltip.style = HICSSObject()
            spline.tooltip.style.width = 100
//            spline.marker.lineColor = "00ff0000"
            dataList.add(spline)
            options.series = dataList

            return options
        }
    }
}