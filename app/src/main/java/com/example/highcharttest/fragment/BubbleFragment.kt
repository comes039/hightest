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
import com.example.highcharttest.chart.data.GradientColor
import com.example.highcharttest.chart.data.HCDataGradient
import com.example.highcharttest.chart.data.ReportAuraResponse
import com.example.highcharttest.chart.data.SampleData
import com.example.highcharttest.databinding.ReportAuraBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class BubbleFragment : Fragment() {

    private lateinit var binding: ReportAuraBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportAuraBinding.inflate(layoutInflater)
        val context = this.context
        val adapter = PieListAdapter(pieList, context)
        binding.listView.adapter = adapter
        // 기본값 week 로설정
        binding.date.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.pieDate.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.viewAllRe.setOnClickListener(View.OnClickListener {

            val intent = Intent(getContext(), AllRecordActivity::class.java)
            startActivity(intent)

        })
        packedBubbleChart(weekReportAuraData)
        pieChart()
        return binding.root

    }

    private fun packedBubbleChart(response:ReportAuraResponse) {
        binding.bubbleChart.addFont(R.font.dmsansregular)
        binding.bubbleChart.options = PackedBubbleChartTest.options(packedBubbleChartData(response))
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
    private fun packedBubbleChartData(response:ReportAuraResponse):List<HCDataGradient>{
        return listOf(
            HCDataGradient("Aura confirmed", response.auraConfirmed.toInt(), GradientColor("F16899", "F4B2D5")),
            HCDataGradient("No aura confirmed", response.noAuraConfirmed.toInt(), GradientColor("9697A5", "9697A5")),
            HCDataGradient("Aura unknown", response.auraUnknown.toInt(), GradientColor("CBCBD5", "CBCBD5")),
            HCDataGradient("No record", response.noRecord.toInt(), GradientColor("E9E9E9", "E9E9E9"))
        )
    }

    class getDate(
        val startDate: String,
        val endDate: String
    )

    val pieList = arrayListOf(
        SampleData("Double vision", 45, 1, "73%"),
        SampleData("Headache", 11, 2, "15%"),
        SampleData("Unknown", 8, 3, "7%"),
        SampleData("Not sure", 5, 4, "5%"),
        SampleData("other", 4, 4, "5%")
    )
    val format = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH)

    // 7days
    val week = getDate(
        LocalDate.now().minusWeeks(1).plusDays(1).format(format),
        LocalDate.now().format(format)
    )

    // 1month
    val month = getDate(
        LocalDate.now().minusMonths(1).format(format),
        LocalDate.now().format(format)
    )

    // 3 month
    val threeMonth = getDate(
        LocalDate.now().minusMonths(3).format(format),
        LocalDate.now().format(format)
    )
    val weekReportAuraData = ReportAuraResponse(
        26,
        9,
        null,
        null,
        null,
        1,
        "HA001",
        "Had Aura",
        "9",
        "34.6",
        "HA004",
        "Skip",
        "8",
        "30.8",
        "HA002",
        "No aura",
        "5",
        "19.2",
        "HA003",
        "Unknown",
        "4",
        "15.4"

    )
    val monthData = ReportAuraResponse(
        84,
        45,
        null,
        null,
        null,
        1,
        "HA001",
        "Had Aura",
        "45",
        "38.5",
        "HA004",
        "Skip",
        "31",
        "29.1",
        "HA002",
        "No aura",
        "22",
        "18.8",
        "HA003",
        "Unknown",
        "19",
        "13.6"

    )
    val threeMonthData = ReportAuraResponse(
        252,
        124,
        null,
        null,
        null,
        1,
        "HA001",
        "Had Aura",
        "124",
        "45.9",
        "HA004",
        "Skip",
        "73",
        "27.0",
        "HA002",
        "No aura",
        "41",
        "15.2",
        "HA003",
        "Unknown",
        "32",
        "11.9"

    )
}