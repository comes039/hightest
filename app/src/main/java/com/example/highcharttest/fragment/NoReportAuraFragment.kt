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
import com.example.highcharttest.databinding.NoReportAuraBinding
import com.example.highcharttest.databinding.ReportAuraBinding
import kotlin.math.round

class NoReportAuraFragment : Fragment() {

    private lateinit var binding: NoReportAuraBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoReportAuraBinding.inflate(layoutInflater)




        // 기본값 week 로설정
        binding.date.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.boxActive.setOnClickListener {

//            val intent = Intent(getContext(), AllRecordActivity::class.java)
//            startActivity(intent)

        }

        return binding.root

    }




}