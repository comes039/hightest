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
import com.example.highcharttest.databinding.ReportAuraBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.round

class BubbleFragment : Fragment() {

    private lateinit var binding: ReportAuraBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportAuraBinding.inflate(layoutInflater)
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

    private fun pieListData(response: List<TagInfoList>): List<SampleData> {
        var otherPercent = 100.0
        var sumOtherValue = 0
        val pieList: ArrayList<SampleData> = arrayListOf()
        for (i in response.indices) {
            if (i < 4) {
                val percent = String.format("%.0f", round(response[i].rate.toDouble())) + "%"
                otherPercent -= String.format("%.0f", round(response[i].rate.toDouble())).toDouble()
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

    class getDate(
        val startDate: String,
        val endDate: String
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
    val monthReportAuraData = ReportAuraResponse(
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
    val threeMonthReportAuraData = ReportAuraResponse(
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
    val weekPieData = ReportAuraTagResponse(
        1, 34.6, 7, listOf(
            TagInfoList(1, 78, "Double vision", 45, 57.6),
            TagInfoList(1, 78, "Headache", 11, 14.1),
            TagInfoList(1, 78, "Unknown", 8, 10.2),
            TagInfoList(1, 78, "Anxiety", 5, 6.4),
            TagInfoList(1, 78, "Upset stomach", 4, 5.1),
            TagInfoList(1, 78, "Sleep issue", 3, 3.8),
            TagInfoList(1, 78, "Tiredness", 2, 2.5),
        )
    )
    val monthPieData = ReportAuraTagResponse(
        1, 34.6, 7, listOf(
            TagInfoList(1, 78, "Double vision", 45 * 2, 57.6),
            TagInfoList(1, 78, "Headache", 11 * 2, 14.1),
            TagInfoList(1, 78, "Unknown", 8 * 2, 10.2),
            TagInfoList(1, 78, "Anxiety", 5 * 2, 6.4),
            TagInfoList(1, 78, "Upset stomach", 4 * 2, 5.1),
            TagInfoList(1, 78, "Sleep issue", 3 * 2, 3.8),
            TagInfoList(1, 78, "Tiredness", 2 * 2, 2.5),
        )
    )
    val threeMonthPieData = ReportAuraTagResponse(
        1, 34.6, 7, listOf(
            TagInfoList(1, 78, "Double vision", 45 * 6, 57.6),
            TagInfoList(1, 78, "Headache", 11 * 6, 14.1),
            TagInfoList(1, 78, "Unknown", 8 * 6, 10.2),
            TagInfoList(1, 78, "Anxiety", 5 * 6, 6.4),
            TagInfoList(1, 78, "Upset stomach", 4 * 6, 5.1),
            TagInfoList(1, 78, "Sleep issue", 3 * 6, 3.8),
            TagInfoList(1, 78, "Tiredness", 2 * 6, 2.5),
        )
    )
    val colorList = listOf(
        GradientColor("F16899","F4B2D5"),
        GradientColor("696A73","696A73"),
        GradientColor("A0A0A0","A0A0A0"),
        GradientColor("CCCCCC","CCCCCC"),
        GradientColor("E9E9E9","E9E9E9"),
    )

}