package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.adaptor.PieAllListAdapter
import com.example.highcharttest.chart.data.SampleData
import com.example.highcharttest.data.pieListData
import com.example.highcharttest.data.weekPieData
import com.example.highcharttest.databinding.ReportAuraAllRecordsBinding

class AuraAllRecordsFragment : Fragment() {

    private lateinit var binding: ReportAuraAllRecordsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportAuraAllRecordsBinding.inflate(layoutInflater)
        val context = this.context
        val adapter = PieAllListAdapter(pieListData(weekPieData.totalTagInfoList), context)
        binding.auraListView.adapter = adapter
        return binding.root

    }


}