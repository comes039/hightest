package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCData
import com.example.highcharttest.chart.data.HCDataGradient
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.HIGradient
import com.highsoft.highcharts.common.HIStop
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*

class PackedBubbleChartTest {

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

            val tooltip = HITooltip()
            tooltip.useHTML = true
            tooltip.pointFormat = "<b>{point.name}:</b> {point.y}"
            options.tooltip = tooltip

            val dataLabels = HIDataLabels()
            dataLabels.apply {
                enabled = true
                verticalAlign = "middle"
                align = "center"
                format = "Aura confirmed<br><span style=\"color:#000000;font-size:24px;font-weight:500\">{point.x:.1f}%</span>"

                style = HIStyle()
                style.apply {
                    color = "white"
                    textOutline = "none"
                    fontWeight = "normal"
                }
            }

            val plotoptions = HIPlotOptions()
            plotoptions.packedbubble = HIPackedbubble()
            plotoptions.packedbubble.stacking = "percent"
            plotoptions.packedbubble.dataLabels = arrayListOf(dataLabels)
            options.plotOptions = plotoptions

            val packedBubble = HIPackedbubble()
            packedBubble.name = "Seizures"

            val dataList = ArrayList<HIData>()
            val sumValue = inputData.map{ v->v.value}.sumOf { it.toInt() }



            inputData.forEach {
                val data = HIData()
                data.name = it.name
                data.value = it.value
//                data.x = (it.value / sumValue).
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
            rule.condition.maxHeight = 300
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