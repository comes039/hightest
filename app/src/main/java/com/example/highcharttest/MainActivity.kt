package com.example.highcharttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView
import java.util.*

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
        val series = HIColumn()
        series.data = ArrayList<Number>(
            listOf(
                49.9,
                71.5,
                106.4,
                129.2,
                144,
                176,
                135.6,
                148.5,
                216.4,
                194.1,
                95.6,
                54.4
            )
        )
        // 옵션에 시리즈 추가 -> 옵션에는 시리즈를 여러개 등록할수 있다.
        options.series = ArrayList(listOf(series))
        // chartView 옵션 추가
        chartView.options = options
    }
}