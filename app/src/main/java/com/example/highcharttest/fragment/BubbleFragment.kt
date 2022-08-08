package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.chart.PackedBubbleChartTest
import com.example.highcharttest.chart.PieChart
import com.example.highcharttest.chart.data.GradientColor
import com.example.highcharttest.chart.data.HCDataGradient
import com.example.highcharttest.databinding.BubbleChartBinding

class BubbleFragment : Fragment() {
    private lateinit var binding: BubbleChartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BubbleChartBinding.inflate(layoutInflater)
        packedBubbleChartSample()
        pieChart()
        return binding.root

    }

    private fun packedBubbleChartSample() {
        val inputData = listOf(
            HCDataGradient("Aura confirmed", 11, GradientColor("F16899", "F4B2D5")),
            HCDataGradient("No aura confirmed", 4, GradientColor("9697A5", "9697A5")),
            HCDataGradient("Aura unknown", 3, GradientColor("CBCBD5", "CBCBD5")),
            HCDataGradient("No record", 3, GradientColor("E9E9E9", "E9E9E9"))
        )
        binding.bubbleChart.addFont(R.font.dmsansregular)
        binding.bubbleChart.options = PackedBubbleChartTest.options(inputData)
    }

    private fun pieChart() {
        val inputData = listOf(
            HCDataGradient("Double vision", 45, GradientColor("F16899", "F4B2D5")),
            HCDataGradient("Headache", 11, GradientColor("696A73", "696A73")),
            HCDataGradient("Unknown", 8, GradientColor("9FA0AE", "9FA0AE")),
            HCDataGradient("Not sure", 5, GradientColor("CBCBD5", "CBCBD5")),
            HCDataGradient("Others", 4, GradientColor("E9E9E9", "E9E9E9")),
        )
        binding.pieChart.addFont(R.font.dmsansregular)
        binding.pieChart.options = PieChart.options(inputData)
    }

}