package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCAreaData
import com.example.highcharttest.chart.data.HCXAxis
import com.example.highcharttest.chart.data.HCYAxis
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.HIGradient
import com.highsoft.highcharts.common.HIStop
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*

class AreaChart {
    companion object {
        fun options(x: HCXAxis, y: HCYAxis, inputData: List<HCAreaData>): HIOptions {
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
            chart.type = "area"
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val xAxis = HIXAxis()
            xAxis.categories = x.category
            xAxis.lineWidth = 0
            options.xAxis = ArrayList(Collections.singletonList(xAxis))

            val yAxis = HIYAxis()
            yAxis.visible = y.visible
            yAxis.tickInterval = y.tickInterval
            options.yAxis = ArrayList(Collections.singletonList(yAxis))

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            val tooltip = HITooltip()
            tooltip.shared = true
            tooltip.headerFormat = "<b>{point.x}</b><br/>"
            tooltip.pointFormat = "{series.name}: {point.y}<br/>"
            options.tooltip = tooltip

            val plotoptions = HIPlotOptions()
            plotoptions.area = HIArea()
            plotoptions.area.marker = HIMarker()
            plotoptions.area.marker.enabled = false
            options.plotOptions = plotoptions

            val dataList = java.util.ArrayList<HISeries>()
            val zIndex = 100
            inputData.forEachIndexed { index, hcAreaData ->
                val series = HIArea()
                series.name = hcAreaData.name
                series.data = hcAreaData.data
                series.color = HIColor.initWithHexValue(hcAreaData.color)
                hcAreaData.fillColor?.let {
                    val stops = LinkedList<HIStop>()
                    stops.add(
                        HIStop(
                            0f,
                            HIColor.initWithRGBA(it.start.r, it.start.g, it.start.b, it.start.a)
                        )
                    )
                    stops.add(
                        HIStop(
                            1f,
                            HIColor.initWithRGBA(it.end.r, it.end.g, it.end.b, it.end.a)
                        )
                    )
                    series.fillColor = HIColor.initWithLinearGradient(HIGradient(), stops)
                }
                series.zIndex = zIndex - index
                dataList.add(series)
            }
            options.series = dataList

            return options
        }
    }
}