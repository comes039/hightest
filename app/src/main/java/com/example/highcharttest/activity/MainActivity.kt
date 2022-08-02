package com.example.highcharttest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.highcharttest.R
import com.example.highcharttest.chart.StackedColumnChartGradient
import com.example.highcharttest.chart.data.*
import com.highsoft.highcharts.core.HIChartView
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chartView = findViewById<HIChartView>(R.id.hc)
        chartView.addFont(R.font.dmsans_regular)
        val category = ArrayList<String>(listOf("Nov 1","2","3","4","5","6","7"))
        val hcxAxis = HCXAxis(category)
        val hcyAxis = HCYAxis(true,true,"test",2,2,"solid")
        val generalized = ArrayList<Any>(listOf(5,1.7,3.9,3,2,3.1,3.1))
        val generalizedList = HCDataValueList("generalized",generalized, GradientColor("F55C5C","F08B8B"))
        val focal = ArrayList<Any>(listOf(0,0,0,0,0,1,0))
        val focalList = HCDataValueList("Focal",focal, GradientColor("9FA0AE","9FA0AE"))
        val combined = ArrayList<Any>(listOf(0,0,0,0,0,3.8,0))
        val combinedList = HCDataValueList("Combined",combined, GradientColor("CBCBD5","CBCBD5"))
        val other = ArrayList<Any>(listOf(0,0,0,3,3.2,2,0.9))
        val otherList = HCDataValueList("other",other, GradientColor("E9E9E9","E9E9E9"))
        val inputData :List<HCDataValueList> = listOf(generalizedList,focalList,combinedList,otherList).reversed()
        val option = StackedColumnChartGradient.options(hcxAxis,hcyAxis,inputData)
        chartView.options = option
    }

}