package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCData
import com.example.highcharttest.chart.data.HCDataGradient
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.HIGradient
import com.highsoft.highcharts.common.HIStop
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIFunction
import java.util.*

class PieChart {

    companion object {
        fun options(inputData: List<HCDataGradient>): HIOptions {
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
            chart.type = "pie"
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
            tooltip.backgroundColor = HIColor.initWithHexValue("000000")
            tooltip.borderWidth = 0
            tooltip.borderRadius = 8
            tooltip.shadow = HIShadowOptionsObject()
            tooltip.shadow.width = 0
            tooltip.style = HICSSObject()
            tooltip.style.width = 101
            tooltip.style.height = 42
            tooltip.style.fontWeight = "500"
            tooltip.style.color = HIColor.initWithHexValue("FFFFFF")
            tooltip.style.fontSize = "12px"
//            tooltip.headerFormat = ""
//            tooltip.pointFormat = "{point.name}: <b>{point.percentage:.1f}%</b>"
            tooltip.formatter = HIFunction("function(){" +
                    "return '" +
                    "<div style=\"text-align:center\">'+this.point.name+'</div><div style=\"text-align:center\">" +
                    "<span>'+Math.round(this.point.percentage)+'%</span></div>" +
                    "'" +
                    "}")
            options.tooltip = tooltip

            val dataLabels = HIDataLabels()
            dataLabels.enabled = false

            val plotOptions = HIPlotOptions()
            plotOptions.pie = HIPie()
            plotOptions.pie.apply {
                setDataLabels(arrayListOf(dataLabels))
            }
            options.plotOptions = plotOptions

            val dataList = ArrayList<HIData>()
            inputData.forEach {
                val data = HIData();
                data.name = it.name
                data.y = it.value
                data.custom = HashMap<String, Any>()
                val stops = LinkedList<HIStop>();
                stops.add(HIStop(0f, HIColor.initWithHexValue(it.color.start)))
                stops.add(HIStop(1f, HIColor.initWithHexValue(it.color.end)))
                data.color = HIColor.initWithLinearGradient(HIGradient(),stops)
                dataList.add(data)
            }

            val pie = HIPie()
            pie.name = "Aura"
            pie.data = dataList

            options.series = ArrayList(Collections.singletonList(pie))

            return options
        }
    }
}