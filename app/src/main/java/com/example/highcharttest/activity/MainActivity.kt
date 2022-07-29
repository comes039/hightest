package com.example.highcharttest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.highcharttest.R
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chartView = findViewById<HIChartView>(R.id.hc)
        // HIOptions 클래스 의 인스턴스 만들기
        val options = HIOptions()
        // HIChart 차트 유형설정하기
        val chart = HIChart()
        chart.type = "column"
        options.chart = chart
        // HIChart 타이틀 설정
        val title = HITitle()
        title.text = "Browser market shares. January, 2015 to May, 2015"
        options.title = title
        // HIChart 서브 타이틀 설정
        val subtitle = HISubtitle()
        subtitle.text =
            "Click the columns to view versions. 'Source': <a href=\"http://netmarketshare.com\">netmarketshare.com</a>."
        options.subtitle = subtitle
        // HIChart X축설정
        val xAxis = HIXAxis()
        xAxis.type = "category"
        options.xAxis = object : ArrayList<HIXAxis?>() {
            init {
                add(xAxis)
            }
        }
        // HIChart Y축설정
        val yAxis = HIYAxis()
        yAxis.title = HITitle()
        yAxis.title.text = "Total percent market share"
        options.yAxis = object : ArrayList<HIYAxis?>() {
            init {
                add(yAxis)
            }
        }
        // HILegend 설정
        val legend = HILegend()
        legend.enabled = false
        options.legend = legend
        // HIPlotOptions 추가
        val plotOptions = HIPlotOptions()
        plotOptions.series = HISeries()
        val dataLabels = HIDataLabels();
        dataLabels.enabled = true
        dataLabels.format = "{point.y:.1f}%"
        plotOptions.series.dataLabels = object : ArrayList<HIDataLabels>() {
            init {
                add(dataLabels)
            }
        }
        options.plotOptions = plotOptions
        // HITooltip 설정
        val tooltip = HITooltip()
        tooltip.headerFormat = "<span style=\"font-size:11px\">{series.name}</span><br>"
        tooltip.pointFormat =
            "<span style=\"color:{point.color}\">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>"
        options.tooltip = tooltip
        // 테스트 데이터 추가
        val series1 = HIColumn()
        series1.name = "Brands"
        series1.colorByPoint = true

        val map1: HashMap<String, Any> = HashMap()
        map1["name"] = "Microsoft Internet Explorer"
        map1["y"] = 56.33
        map1["drilldown"] = "Microsoft Internet Explorer"

        val map2: HashMap<String, Any> = HashMap()
        map2["name"] = "Chrome"
        map2["y"] = 24.03
        map2["drilldown"] = "Chrome"

        val map3: HashMap<String, Any> = HashMap()
        map3["name"] = "Firefox"
        map3["y"] = 10.38
        map3["drilldown"] = "Firefox"

        val map4: HashMap<String, Any> = HashMap()
        map4["name"] = "Safari"
        map4["y"] = 4.77
        map4["drilldown"] = "Safari"

        val map5: HashMap<String, Any> = HashMap()
        map5["name"] = "Opera"
        map5["y"] = 0.91
        map5["drilldown"] = "Opera"

        val map6: HashMap<String, Any?> = HashMap()
        map6["name"] = "Proprietary or Undetectable"
        map6["y"] = 0.2
        map6["drilldown"] = null

        val series1Map: List<HashMap<String, out Any?>> =
            listOf(map1, map2, map3, map4, map5, map6)
        // 옵션에 시리즈 추가 -> 옵션에는 시리즈를 여러개 등록할수 있다.
        series1.data = ArrayList(series1Map)
        options.series = ArrayList(listOf(series1))
        // 세부 데이터 추가
        val drilldown = HIDrilldown()

        val series2 = HIColumn()
        series2.name = "Microsoft Internet Explorer"
        series2.id = "Microsoft Internet Explorer"

        val object1 = arrayOf<Any>("v11.0", 24.13)
        val object2 = arrayOf<Any>("v8.0", 17.2)
        val object3 = arrayOf<Any>("v9.0", 8.11)
        val object4 = arrayOf<Any>("v10.0", 5.33)
        val object5 = arrayOf<Any>("v6.0", 1.06)
        val object6 = arrayOf<Any>("v7.0", 0.5)

        series2.data =
            ArrayList(listOf(object1, object2, object3, object4, object5, object6))

        val series3 = HIColumn()
        series3.name = "Chrome"
        series3.id = "Chrome"

        val object7 = arrayOf<Any>("v41.0", 4.32)
        val object8 = arrayOf<Any>("v42.0", 3.68)
        val object9 = arrayOf<Any>("v39.0", 2.96)
        val object10 = arrayOf<Any>("v36.0", 2.53)
        val object11 = arrayOf<Any>("v43.0", 1.45)
        val object12 = arrayOf<Any>("v31.0", 1.24)
        val object13 = arrayOf<Any>("v35.0", 0.85)
        val object14 = arrayOf<Any>("v38.0", 0.6)
        val object15 = arrayOf<Any>("v32.0", 0.55)
        val object16 = arrayOf<Any>("v37.0", 0.38)
        val object17 = arrayOf<Any>("v33.0", 0.19)
        val object18 = arrayOf<Any>("v34.0", 0.14)
        val object19 = arrayOf<Any>("v30.0", 0.14)
        val object20 = arrayOf<Any>("v40.0", 5)

        series3.data = ArrayList(
            listOf(
                object7,
                object8,
                object9,
                object10,
                object11,
                object12,
                object13,
                object14,
                object15,
                object16,
                object17,
                object18,
                object19,
                object20
            )
        )

        val series4 = HIColumn()
        series4.name = "Firefox"
        series4.id = "Firefox"

        val object21 = arrayOf<Any>("v35", 2.76)
        val object22 = arrayOf<Any>("v36", 2.32)
        val object23 = arrayOf<Any>("v37", 2.31)
        val object24 = arrayOf<Any>("v34", 1.27)
        val object25 = arrayOf<Any>("v38", 1.02)
        val object26 = arrayOf<Any>("v31", 0.33)
        val object27 = arrayOf<Any>("v33", 0.22)
        val object28 = arrayOf<Any>("v32", 0.15)

        series4.data = ArrayList(
            listOf(
                object21,
                object22,
                object23,
                object24,
                object25,
                object26,
                object27,
                object28
            )
        )

        val series5 = HIColumn()
        series5.name = "Safari"
        series5.id = "Safari"

        val object29 = arrayOf<Any>("v8.0", 2.56)
        val object30 = arrayOf<Any>("v7.1", 0.77)
        val object31 = arrayOf<Any>("v5.1", 0.42)
        val object32 = arrayOf<Any>("v5.0", 0.3)
        val object33 = arrayOf<Any>("v6.1", 0.29)
        val object34 = arrayOf<Any>("v7.0", 0.26)
        val object35 = arrayOf<Any>("v6.2", 0.17)

        series5.data = ArrayList(
            listOf(
                object29,
                object30,
                object31,
                object32,
                object33,
                object34,
                object35
            )
        )

        val series6 = HIColumn()
        series6.name = "Opera"
        series6.id = "Opera"

        val object36 = arrayOf<Any>("v12.x", 0.34)
        val object37 = arrayOf<Any>("v28", 0.24)
        val object38 = arrayOf<Any>("v27", 0.17)
        val object39 = arrayOf<Any>("v29", 0.16)

        series6.data = ArrayList(listOf(object36, object37, object38, object39))
        val seriesList = listOf(series2, series3, series4, series5, series6)
        drilldown.series = ArrayList<HIColumn>(seriesList)
        options.drilldown = drilldown

        // chartView 옵션 추가
        chartView.options = options
    }
}