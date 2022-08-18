package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.listAdapter
import com.example.highcharttest.chart.ColumnChart
import com.example.highcharttest.chart.data.HCColumnData
import com.example.highcharttest.chart.data.HCXAxis
import com.example.highcharttest.chart.data.HCYAxis
import com.example.highcharttest.chart.data.SampleData
import com.example.highcharttest.data.otherSymptomListData
import com.example.highcharttest.data.week
import com.example.highcharttest.data.weekReportOtherSymptomData
import com.example.highcharttest.databinding.ReportOtherSymptomsBinding

class OtherSymptomsFragment : Fragment() {

    private lateinit var binding: ReportOtherSymptomsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportOtherSymptomsBinding.inflate(layoutInflater)
        val context = this.context
        val otherSymptomsList: List<SampleData> = otherSymptomListData(weekReportOtherSymptomData)
        progressBarBinding(otherSymptomsList)
        val adapter = listAdapter(otherSymptomsList, context)
        binding.listView.adapter = adapter
        binding.date.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.title.text = getString(R.string.other_symptoms_text, weekReportOtherSymptomData.totalList[0].name)
        binding.barDate.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.boxNameOne.text = weekReportOtherSymptomData.totalList[0].name
        binding.boxNameTwo.text = weekReportOtherSymptomData.totalList[1].name
        binding.boxNameThree.text = weekReportOtherSymptomData.totalList[2].name
        binding.entryOne.text = weekReportOtherSymptomData.totalList[0].count.toString() + " entries"
        binding.entryTwo.text = weekReportOtherSymptomData.totalList[1].count.toString() + " entries"
        binding.entryThree.text = weekReportOtherSymptomData.totalList[2].count.toString() + " entries"
        binding.entiresCount.text = getString(R.string.entries_count, weekReportOtherSymptomData.totalCountAvg)
        binding.beforeEntiresCount.text = getString(R.string.entries_count, weekReportOtherSymptomData.beforeTotalCountAvg)
        binding.symptomsCount.text = getString(R.string.symptoms_count, weekReportOtherSymptomData.totalCount)
        binding.beforeSymptomsCount.text = getString(R.string.symptoms_count, weekReportOtherSymptomData.beforeTotalCount)

        columnChart()
        return binding.root
    }

    private fun progressBarBinding(otherSymptomList: List<SampleData>) {
        var sumPercent = 0
        for (i in otherSymptomList.indices) {
            sumPercent += otherSymptomList[i].percent.replace("%", "").toInt()
            println("sumPercent = $sumPercent")
            when (i) {
                0 -> binding.progress1.progress = sumPercent
                1 -> binding.progress2.progress = sumPercent
                2 -> binding.progress3.progress = sumPercent
                3 -> binding.progress4.progress = sumPercent
            }
        }
    }

    private fun columnChart() {
        val xAxis = HCXAxis(
            arrayListOf(
                "Nov 1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7"
            )
        )
        val yAxis = HCYAxis(
            true,
            true,
            "(entries)",
            120,
            1,
            "Solid",
            null
        )
        val inputData = arrayListOf(370, 230, 360, 120, 290, 370, 470)
        val columnData = HCColumnData(
            xAxis,
            yAxis,
            "46A0F4",
            "7A7A7A",
            "E0E0E0",
            inputData
        )

        binding.columnChart.options = ColumnChart.options(columnData)
    }
}