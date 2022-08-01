package com.example.highcharttest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.highcharttest.R
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.HIGradient
import com.highsoft.highcharts.common.HIStop
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chartView = findViewById<HIChartView>(R.id.hc)
        chartView.addFont(R.font.dmsans_regular)
        // HIOptions 클래스 의 인스턴스 만들기
        val options = HIOptions()
        // HIChart 차트 유형설정하기
        val chart = HIChart()
        chart.type = "column"
        options.chart = chart
        // HIChart 타이틀 설정
        val title = HITitle()
        title.style = HICSSObject();
        title.style.fontSize = "24px"
        title.style.lineWidth = 30
        title.text = ""
        options.title = title
        // HIChart 서브 타이틀 설정
        val subtitle = HISubtitle()
        subtitle.text = ""
        options.subtitle = subtitle
        // HIChart X축설정
        val xAxis = HIXAxis()
        xAxis.type = "category"
        options.xAxis = ArrayList(Collections.singletonList(xAxis))
        // HIChart Y축설정
        val yAxis = HIYAxis()
        yAxis.title = HITitle()
        yAxis.title.text = "Total percent market share"
        options.yAxis = ArrayList(Collections.singletonList(yAxis))
        // HILegend 설정
        val legend = HILegend()
        legend.enabled = false
        options.legend = legend
        // HIPlotOptions 추가
        val plotOptions = HIPlotOptions()
        plotOptions.series = HISeries()
        val dataLabels = HIDataLabels();
        dataLabels.enabled = true
        dataLabels.format = "{point.y:.1f}"
        plotOptions.series.dataLabels = ArrayList(Collections.singletonList(dataLabels))
        options.plotOptions = plotOptions
        // HITooltip 설정
        val tooltip = HITooltip()
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
        // 테스트 데이터 추가
        val series1 = HIColumn()
        val hiGradient = HIGradient(0F, 0F, 0F, 1F)
        val stops: LinkedList<HIStop> = LinkedList()
        stops.add(HIStop(0f, HIColor.initWithHexValue("F55C5C")))
        stops.add(HIStop(1f, HIColor.initWithHexValue("F08B8B")))
        series1.name = "Nov 6, 2022"
        series1.color = HIColor.initWithLinearGradient(hiGradient, stops)
        series1.colorByPoint = false

        val map1: HashMap<String, Any> = HashMap()
        map1["name"] = "Nov 1"
        map1["y"] = 56.33
        map1["drilldown"] = "Microsoft Internet Explorer"

        val map2: HashMap<String, Any> = HashMap()
        map2["name"] = "2"
        map2["y"] = 24.03
        map2["drilldown"] = "Chrome"

        val map3: HashMap<String, Any> = HashMap()
        map3["name"] = "3"
        map3["y"] = 10.38
        map3["drilldown"] = "Firefox"

        val map4: HashMap<String, Any> = HashMap()
        map4["name"] = "4"
        map4["y"] = 4.77
        map4["drilldown"] = "Safari"

        val map5: HashMap<String, Any> = HashMap()
        map5["name"] = "5"
        map5["y"] = 0.91
        map5["drilldown"] = "Opera"

        val map6: HashMap<String, Any?> = HashMap()
        map6["name"] = "6"
        map6["y"] = 0.2
        map6["drilldown"] = null

        val map7: HashMap<String, Any?> = HashMap()
        map7["name"] = "7"
        map7["y"] = 0.2
        map7["drilldown"] = null

        val series1Map: List<HashMap<String, out Any?>> =
            listOf(map1, map2, map3, map4, map5, map6, map7)
        // 옵션에 시리즈 추가 -> 옵션에는 시리즈를 여러개 등록할수 있다.
        series1.data = ArrayList(series1Map)
        options.series = ArrayList(listOf(series1))
        // chartView 옵션 추가
        chartView.options = options
    }
}