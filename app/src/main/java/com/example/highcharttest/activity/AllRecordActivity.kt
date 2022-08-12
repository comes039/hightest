package com.example.highcharttest.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.PieAllListAdapter
import com.example.highcharttest.data.pieAllListData
import com.example.highcharttest.data.week
import com.example.highcharttest.data.weekPieData
import com.example.highcharttest.data.weekReportAuraData
import com.example.highcharttest.databinding.ReportAuraAllRecordsBinding

class AllRecordActivity : AppCompatActivity() {
    private lateinit var binding: ReportAuraAllRecordsBinding

    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_aura_all_records)
        binding = DataBindingUtil.setContentView(this, R.layout.report_aura_all_records)
        binding.allRecordDate.text = getString(R.string.all_record_date, week.startDate, week.endDate)
        binding.device.text = getString(R.string.device, weekReportAuraData.firstRate) + "%"
        listView = findViewById(R.id.aura_list_view)
        binding.boxActive.setOnClickListener {
            onBackPressed()
        }
        binding.icoArrowL.setOnClickListener {
            onBackPressed()
        }
        val adapter = PieAllListAdapter(pieAllListData(weekPieData.totalTagInfoList), this)
        listView.adapter = adapter
    }
}