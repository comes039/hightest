package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.listAdapter
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
        val adapter = listAdapter(otherSymptomListData(weekReportOtherSymptomData), context)
        binding.listView.adapter = adapter
        binding.date.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.title.text = getString(R.string.other_symptoms_text,weekReportOtherSymptomData.totalList[0].name)

        return binding.root
    }

}