package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.data.week
import com.example.highcharttest.data.weekReportAuraData
import com.example.highcharttest.databinding.ReportOtherSymptomsBinding

class OtherSymptomsFragment : Fragment() {

    private lateinit var binding: ReportOtherSymptomsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportOtherSymptomsBinding.inflate(layoutInflater)
        binding.date.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.title.text = getString(R.string.other_symptoms_text,)
        return binding.root
    }

}