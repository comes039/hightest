package com.example.highcharttest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.highcharttest.R
import com.example.highcharttest.chart.ColumnChart
import com.example.highcharttest.chart.data.HCColumnData
import com.example.highcharttest.chart.data.HCXAxis
import com.example.highcharttest.chart.data.HCYAxis
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chartView = findViewById<HIChartView>(R.id.hc)
        chartView.theme = "brand-light"
        val category = ArrayList<String>()
        category.add("category")
        category.add("category")
        category.add("category")
        category.add("category")
        category.add("category")
        category.add("category")
        category.add("category")
        val hcxAxis = HCXAxis(category)
        val hcyAxis = HCYAxis(true,true,"test",2,2,"solid");
        val inputData = ArrayList<Int>()
        inputData.add(100)
        inputData.add(10)
        inputData.add(20)
        inputData.add(30)
        inputData.add(40)
        inputData.add(50)
        inputData.add(60)
        val hcColumnData = HCColumnData(hcxAxis,hcyAxis,"#123456","#570a40","#3e992c",inputData)
        val option = ColumnChart.options(hcColumnData)
        chartView.options = option
    }
}