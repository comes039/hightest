package com.example.highcharttest.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.allListAdapter
import com.example.highcharttest.data.auraAllListData
import com.example.highcharttest.data.week
import com.example.highcharttest.data.weekAuraReportTagData
import com.example.highcharttest.data.weekReportAuraData
import com.example.highcharttest.databinding.ReportAuraAllRecordsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AuraAllRecordsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: ReportAuraAllRecordsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportAuraAllRecordsBinding.inflate(layoutInflater)
        binding.allRecordDate.text = getString(com.example.highcharttest.R.string.all_record_date, week.startDate, week.endDate)
        val context = this.context
        val adapter =
            allListAdapter(auraAllListData(weekAuraReportTagData.totalTagInfoList), context)
        binding.device.text = getString(com.example.highcharttest.R.string.device, weekReportAuraData.reportAuraInfoList[0].auraRate) + "%"
        binding.boxActive.setOnClickListener {
            this.dismiss()
        }
        binding.icoArrowL.setOnClickListener {
            this.dismiss()
        }

        binding.auraListView.adapter = adapter
        return binding.root

    }
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

}