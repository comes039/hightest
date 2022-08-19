package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.listAdapter
import com.example.highcharttest.chart.BasicColumnChart
import com.example.highcharttest.chart.data.GradientColor
import com.example.highcharttest.chart.data.HCBasicColumnData
import com.example.highcharttest.chart.data.HCBasicColumnRow
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
        binding.boxNameOne.text = getString(R.string.box_name, weekReportOtherSymptomData.totalList[0].name)
        binding.boxNameTwo.text = getString(R.string.box_name, weekReportOtherSymptomData.totalList[1].name)
        binding.boxNameThree.text = getString(R.string.box_name, weekReportOtherSymptomData.totalList[2].name)
        binding.entryOne.text = getString(R.string.entries, weekReportOtherSymptomData.totalList[0].count.toString())
        binding.entryTwo.text = getString(R.string.entries, weekReportOtherSymptomData.totalList[1].count.toString())
        binding.entryThree.text = getString(R.string.entries, weekReportOtherSymptomData.totalList[2].count.toString())
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

        val columnData = listOf(
            HCBasicColumnRow(
                weekReportOtherSymptomData.totalList[0].name,
                GradientColor("FFB648", "FFD18A"),
                "FFBD59",
                arrayListOf(0.9, 0, 1.3, 2.2, 0.9, 0.75, 0.9)
            ),
            HCBasicColumnRow(
                weekReportOtherSymptomData.totalList[1].name,
                GradientColor("A0A0A0", "A0A0A0"),
                "A0A0A0",
                arrayListOf(0.9, 0, 1.3, 1.3, 0.9, 1.3, 1.3)
            ),
            HCBasicColumnRow(
                weekReportOtherSymptomData.totalList[2].name,
                GradientColor("CCCCCC", "CCCCCC"),
                "CCCCCC",
                arrayListOf(0.9, 0, 1.3, 2.2, 0.9, 0.75, 2.3)
            )
        )
        val splineData = arrayListOf<Number?>(2.8, null, null, 2.8, null, null, null)
        val category = arrayListOf("Nov 1", "2", "3", "4", "5", "6", "7")
        val hcBasicColumnData = HCBasicColumnData(columnData, splineData, category)

        binding.columnChart.options = BasicColumnChart.options(hcBasicColumnData)
    }
}