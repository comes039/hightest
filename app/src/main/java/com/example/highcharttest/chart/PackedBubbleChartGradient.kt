package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCDataGradient
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.HIGradient
import com.highsoft.highcharts.common.HIStop
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*

class PackedBubbleChartGradient {

    companion object {
        fun options(inputData: List<HCDataGradient>): HIOptions {
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
            chart.type = "packedbubble"
            chart.height = "100%"
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend
            val point = HIPoint()
            point.percentage
            val tooltip = HITooltip()
            tooltip.useHTML = true
            tooltip.pointFormat = "<b>{point.name}:</b> {point.value}"
            options.tooltip = tooltip

            val dataLabels = HIDataLabels()
            dataLabels.apply {
                enabled = true
                format = "{point.name}"
                align = "center"
                verticalAlign = "center"
                filter = HIFilter()
                filter.apply {
                    property = "y"
                    operator = ">"
                    value = 4
                }
                style = HIStyle()
                style.apply {
                    color = "black"
                    textOutline = "none"
                    fontWeight = "normal"
                }
            }

            val plotoptions = HIPlotOptions()
            plotoptions.packedbubble = HIPackedbubble()
//            plotoptions.packedbubble.minSize = "10%"
            plotoptions.packedbubble.maxSize = "100%"
            plotoptions.packedbubble.dataLabels = arrayListOf(dataLabels)
            options.plotOptions = plotoptions

            val packedBubble = HIPackedbubble()
            packedBubble.name = "Seizures"

            val dataList = ArrayList<HIData>()
            inputData.forEach {
                val data = HIData()
                data.name = it.name
                data.value = it.value
                val stops = LinkedList<HIStop>()
                stops.add(HIStop(0f, HIColor.initWithHexValue(it.color.start)))
                stops.add(HIStop(1f, HIColor.initWithHexValue(it.color.end)))
                data.color = HIColor.initWithLinearGradient(HIGradient(), stops)
                dataList.add(data)
            }
            packedBubble.data = dataList
            packedBubble.layoutAlgorithm = HILayoutAlgorithm()
            options.series = arrayListOf(packedBubble)

            val responsive = HIResponsive()
            val rule = HIRules()
            rule.condition = HICondition()
            rule.condition.maxHeight = 100
            val ruleOptions = HashMap<String, HashMap<String, String>>()
            val legendRules = HashMap<String, String>()
            legendRules.put("align", "left")
            legendRules.put("verticalAlign", "middle")
            legendRules.put("layout", "vertical")
            ruleOptions.put("legend", legendRules)
            rule.chartOptions = ruleOptions
            responsive.rules = arrayListOf(rule)
            options.responsive = responsive

            return options
        }
    }

}