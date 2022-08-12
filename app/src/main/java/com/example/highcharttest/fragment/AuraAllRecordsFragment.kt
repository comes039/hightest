package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.PieAllListAdapter
import com.example.highcharttest.data.pieAllListData
import com.example.highcharttest.data.week
import com.example.highcharttest.data.weekPieData
import com.example.highcharttest.data.weekReportAuraData
import com.example.highcharttest.databinding.ReportAuraAllRecordsBinding

class AuraAllRecordsFragment : Fragment() {

    private lateinit var binding: ReportAuraAllRecordsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportAuraAllRecordsBinding.inflate(layoutInflater)
        binding.allRecordDate.text = getString(R.string.all_record_date, week.startDate, week.endDate)
        val context = this.context
        val adapter = PieAllListAdapter(pieAllListData(weekPieData.totalTagInfoList), context)
        binding.device.text = getString(R.string.device, weekReportAuraData.firstRate) +"%"
        binding.auraListView.adapter = adapter
        return binding.root

    }


}