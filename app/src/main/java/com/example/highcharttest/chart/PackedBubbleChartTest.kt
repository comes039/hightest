package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCDataGradient
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.HIGradient
import com.highsoft.highcharts.common.HIStop
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class PackedBubbleChartTest {

    companion object {

        fun options(inputData: List<HCDataGradient>): HIOptions {
            val options = HIOptions()

            // export menu
            val exporting = HIExporting()
            exporting.enabled = false
            // html 태그 허용설정
            exporting.allowHTML = true
            options.exporting = exporting

            // credits
            val credits = HICredits()
            credits.enabled = false
            options.credits = credits

            val chart = HIChart()
            chart.type = "packedbubble"
            chart.style = HICSSObject()
//            chart.style.fontFamily = "dmsans_regular"
            chart.height = "100%"

//            chart.width = "100%"
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            val tooltip = HITooltip()
            tooltip.useHTML = true
            tooltip.backgroundColor = HIColor.initWithHexValue("000000")
            tooltip.borderWidth = 0
            tooltip.borderRadius = 8
            tooltip.shadow = HIShadowOptionsObject()
            tooltip.shadow.width = 0
            tooltip.style = HICSSObject()
            tooltip.style.width = 96
            tooltip.style.height = 48
            tooltip.style.color = HIColor.initWithHexValue("888888")
            tooltip.style.fontSize = "10px"
            tooltip.headerFormat = ""
//            tooltip.formatter
            tooltip.pointFormat = "<div>{point.name}</div><div style=\"text-align:center\">" +
                    "<span style=\"font-size:12px;color:#F55C5C\">• </span>" +
                    "<span style=\"font-size:12px;color:#FFFFFF\">{point.custom.value} seizures</span></div> "
            options.tooltip = tooltip

            val dataLabels = HIDataLabels()
            dataLabels.apply {
                enabled = true
                verticalAlign = "middle"
                align = "center"
                useHTML = true
                zIndex = 0
                format = "<div style=\"text-align:center;height:{point.custom.header_size}px\">" +
                        "<span style=\"font-size:{point.custom.header_size}px\">{point.name}</span>" +
                        "</div>" +
                        "<div style=\"text-align:center\">" +
                        "<span style=\"font-size:{point.custom.point_size}px;font-weight:500;\">" +
                        "{point.value:.0f}%</span>" +
                        "</div>"
                filter = HIFilter()
                filter.apply {
                    property = "value"
                    operator = ">"
                    value = 15
                }
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
            val minValue = inputData.map{ v->v.value}.minOf { it.toInt() }
            val maxValue = inputData.map{ v->v.value}.maxOf { it.toInt() }

            inputData.forEach {
                val data = HIData()
                data.name = it.name
                // 퍼센트
                data.value = (it.value.toFloat() / sumValue.toFloat()) * 100
                data.custom = HashMap<String,Any>()
                data.custom["value"] = it.value
                data.custom["header_size"] =  if (it.value == maxValue) 12 else 0
                data.custom["point_size"] = if (it.value == maxValue) 24 else if(data.value.toFloat()>15) 14 else 0
//                //값
//                data.x = it.value
//                // Aura confirmed 글자크기 설정값 -> 최대크기만 표시
//                data.q3 = if (it.value == maxValue) 12 else 0
//                // 퍼센트 글자크기 설정값
//                data.q1 = if (it.value == maxValue) 24 else if(data.value.toFloat()>15) 14 else 0
                val stops = LinkedList<HIStop>()
                stops.add(HIStop(0f, HIColor.initWithHexValue(it.color.start)))
                stops.add(HIStop(1f, HIColor.initWithHexValue(it.color.end)))
                data.color = HIColor.initWithLinearGradient(HIGradient(), stops)
                dataList.add(data)
            }
            packedBubble.data = dataList
            packedBubble.layoutAlgorithm = HILayoutAlgorithm()
            // width 대비 비율로 계산해야함
            packedBubble.maxSize = (maxValue.toFloat() / sumValue.toFloat()) * 300
            packedBubble.minSize = (minValue.toFloat() / sumValue.toFloat()) * 300
            packedBubble.marker = HIMarker()
            // 테두리 설정
            packedBubble.marker.lineWidth = 0

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