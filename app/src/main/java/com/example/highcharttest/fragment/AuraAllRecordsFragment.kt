package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.allListAdapter
import com.example.highcharttest.data.auraAllListData
import com.example.highcharttest.data.week
import com.example.highcharttest.data.weekAuraReportTagData
import com.example.highcharttest.data.weekReportAuraData
import com.example.highcharttest.databinding.ReportAuraAllRecordsBinding

class AuraAllRecordsFragment : Fragment() {

    private lateinit var binding: ReportAuraAllRecordsBinding
    private lateinit var viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportAuraAllRecordsBinding.inflate(layoutInflater)
        binding.allRecordDate.text = getString(R.string.all_record_date, week.startDate, week.endDate)
        val context = this.context
        val adapter =
            allListAdapter(auraAllListData(weekAuraReportTagData.totalTagInfoList), context)
        binding.device.text = getString(R.string.device, weekReportAuraData.reportAuraInfoList[0].auraRate) +"%"
        binding.boxActive.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.icoArrowL.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.auraListView.adapter = adapter
        return binding.root

    }

}