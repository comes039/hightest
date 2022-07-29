package com.example.highcharttest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.highcharttest.R
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView
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
        title.text = "test chart"
        options.title = title
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

        // chartView 옵션 추가
        chartView.options = options
    }
}