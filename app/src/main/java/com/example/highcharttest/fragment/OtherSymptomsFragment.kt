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
import com.example.highcharttest.chart.data.*
import com.example.highcharttest.data.colorList
import com.example.highcharttest.data.week
import com.example.highcharttest.data.weekPieData
import com.example.highcharttest.data.weekReportAuraData
import com.example.highcharttest.databinding.ReportAuraBinding
import com.example.highcharttest.databinding.ReportOtherSymptomsBinding
import kotlin.math.round

class OtherSymptomsFragment : Fragment() {

    private lateinit var binding: ReportOtherSymptomsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportOtherSymptomsBinding.inflate(layoutInflater)
        val context = this.context
        val adapter = PieListAdapter(pieListData(weekPieData.totalTagInfoList), context)
        binding.listView.adapter = adapter
        val titlePercent = String.format("%.0f", weekReportAuraData.firstRate.toDouble()) + "%"
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
        binding.auraConfirmed.text = getString(R.string.seizures, weekReportAuraData.auraConfirmed)
        binding.noAuraConfirmed.text =
            getString(R.string.seizures, weekReportAuraData.noAuraConfirmed)
        binding.auraUnknown.text = getString(R.string.seizures, weekReportAuraData.auraUnknown)
        binding.noRecord.text = getString(R.string.seizures, weekReportAuraData.noRecord)
        binding.pieTitle.text = getString(R.string.pie_string, weekPieData.totalTagInfoList[0].auraTag)
        binding.viewAllRe.setOnClickListener {

            val intent = Intent(getContext(), AllRecordActivity::class.java)
            startActivity(intent)

        }
        packedBubbleChart(weekReportAuraData)
        pieChart(weekPieData)
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

    private fun pieChartData(response: List<TagInfoList>): List<HCDataGradient> {
        val inputData: ArrayList<HCDataGradient> = ArrayList()
        var sumOtherValue = 0
        for (i in response.indices) {
            if (i < 4) {
                inputData.add(HCDataGradient(response[i].auraTag, response[i].tagCount, colorList[i]))
            } else {
                sumOtherValue += response[i].tagCount.toInt()
            }
        }
        inputData.add(HCDataGradient("Other", sumOtherValue, colorList[4]))
        return inputData
    }

    fun pieListData(response: List<TagInfoList>): List<SampleData> {
        var otherPercent = 100.0
        var sumOtherValue = 0
        val pieList: ArrayList<SampleData> = arrayListOf()
        for (i in response.indices) {
            if (i < 4) {
                val percent = String.format("%.0f", round(response[i].rate.toDouble())) + "%"
                otherPercent -= String.format("%.0f", response[i].rate.toDouble()).toDouble()
                pieList.add(SampleData(response[i].auraTag, response[i].tagCount, i + 1, percent))
            } else {
                sumOtherValue += response[i].tagCount.toInt()
            }
        }
        pieList.add(SampleData("Other", sumOtherValue, 5, String.format("%.0f", round(otherPercent)) + "%"))
        return pieList
    }

    private fun packedBubbleChartData(response: ReportAuraResponse): List<HCPackedBubbleData> {
        return listOf(
            HCPackedBubbleData(
                "Aura confirmed", response.auraConfirmed.toInt(), GradientColor("F16899", "F4B2D5"), response.firstRate.toDouble()
            ),
            HCPackedBubbleData(
                "No aura confirmed", response.noAuraConfirmed.toInt(), GradientColor("9697A5", "9697A5"), response.secondRate.toDouble()
            ),
            HCPackedBubbleData(
                "Aura unknown", response.auraUnknown.toInt(), GradientColor("CBCBD5", "CBCBD5"), response.thirdRate.toDouble()
            ),
            HCPackedBubbleData(
                "No record", response.noRecord.toInt(), GradientColor("E9E9E9", "E9E9E9"), response.fourthRate.toDouble()
            )
        )
    }


}