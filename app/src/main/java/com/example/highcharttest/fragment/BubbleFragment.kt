package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.chart.PackedBubbleChart
import com.example.highcharttest.chart.PackedBubbleChartGradient
import com.example.highcharttest.chart.PackedBubbleChartTest
import com.example.highcharttest.chart.data.GradientColor
import com.example.highcharttest.chart.data.HCData
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
        return binding.root

    }

    private fun packedBubbleChartSample() {
        val inputData = listOf(
            HCDataGradient("Aura confirmed", 60, GradientColor("F16899","F4B2D5")),
            HCDataGradient("No aura confirmed", 20, GradientColor("9697A5","9697A5")),
            HCDataGradient("Aura unknown", 15, GradientColor("CBCBD5","CBCBD5")),
            HCDataGradient("No record", 5, GradientColor("E9E9E9","E9E9E9"))
        )
        binding.bubbleChart.options = PackedBubbleChartTest.options(inputData)
    }
}