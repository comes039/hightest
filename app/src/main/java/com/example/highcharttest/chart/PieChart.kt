package com.example.highcharttest.chart

import com.example.highcharttest.chart.data.HCData
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*

class PieChart {

    companion object {
        fun options(inputData: List<HCData>): HIOptions {
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
            chart.type = "pie"
            options.chart = chart

            val title = HITitle()
            title.text = ""
            options.title = title

            val legend = HILegend()
            legend.enabled = false
            options.legend = legend

            val tooltip = HITooltip()
            tooltip.headerFormat = ""
            tooltip.pointFormat = "{point.name}: <b>{point.percentage:.1f}%</b>"
            options.tooltip = tooltip

            val dataLabels = HIDataLabels()
            dataLabels.enabled = false

            val plotOptions = HIPlotOptions()
            plotOptions.pie = HIPie()
            plotOptions.pie.apply {
                setDataLabels(arrayListOf(dataLabels))
            }
            options.plotOptions = plotOptions

            val dataList = ArrayList<HashMap<String, Any>>()
            inputData.forEach {
                val map = HashMap<String, Any>()
                map.put("name", it.name)
                map.put("y", it.value)
                map.put("color", "#${it.color}")
                dataList.add(map)
            }

            val pie = HIPie()
            pie.name = "Aura"
            pie.data = dataList

            options.series = ArrayList(Collections.singletonList(pie))

            return options
        }
    }
}