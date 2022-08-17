package com.example.highcharttest.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.activity.AllRecordActivity
import com.example.highcharttest.adaptor.PieListAdapter
import com.example.highcharttest.chart.PackedBubbleChartTest
import com.example.highcharttest.chart.PieChart
import com.example.highcharttest.chart.data.ReportAuraResponse
import com.example.highcharttest.chart.data.ReportAuraTagResponse
import com.example.highcharttest.data.*
import com.example.highcharttest.databinding.ReportAuraBinding
import kotlin.math.round

class AuraFragment : Fragment() {

    private lateinit var binding: ReportAuraBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportAuraBinding.inflate(layoutInflater)
        val context = this.context
        val adapter = PieListAdapter(pieListData(weekAuraReportTagData.totalTagInfoList), context)
        binding.listView.adapter = adapter
        val titlePercent = String.format("%.0f", round(weekReportAuraData.reportAuraInfoList[0].auraRate.toDouble())) + "%"
        // 기본값 week 로설정
        binding.date.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.pieDate.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.bubbleTitle.text = getString(R.string.seizures_text, titlePercent)
        binding.auraConfirmed.text = getString(
            R.string.aura_comment,
            weekReportAuraData.haveAuraTotalCount,
            weekReportAuraData.seizureTotalCount
        )
        binding.bubbleComment.text = getString(
            R.string.aura_comment,
            weekReportAuraData.haveAuraTotalCount,
            weekReportAuraData.seizureTotalCount
        )
        binding.auraConfirmed.text = getString(R.string.seizures, weekReportAuraData.reportAuraInfoList[0].count)
        binding.noAuraConfirmed.text = getString(R.string.seizures, weekReportAuraData.reportAuraInfoList[1].count)
        binding.auraUnknown.text = getString(R.string.seizures, weekReportAuraData.reportAuraInfoList[2].count)
        binding.noRecord.text = getString(R.string.seizures, weekReportAuraData.reportAuraInfoList[3].count)
        binding.pieTitle.text = getString(R.string.pie_string, weekAuraReportTagData.totalTagInfoList[0].auraTag)
        binding.viewAllRe.setOnClickListener {
            val intent = Intent(getContext(), AllRecordActivity::class.java)
            startActivity(intent)
        }
        packedBubbleChart(weekReportAuraData)
        pieChart(weekAuraReportTagData)
        return binding.root

    }

    private fun packedBubbleChart(response: ReportAuraResponse) {
        binding.bubbleChart.addFont(R.font.dmsansregular)
        binding.bubbleChart.options = PackedBubbleChartTest.options(packedBubbleChartData(response))
    }

    private fun pieChart(response: ReportAuraTagResponse) {
        binding.pieChart.addFont(R.font.dmsansregular)
        binding.pieChart.options = PieChart.options(pieChartData(response.totalTagInfoList))
    }

}