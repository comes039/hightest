package com.example.highcharttest.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.PieAllListAdapter
import com.example.highcharttest.data.pieListData
import com.example.highcharttest.data.weekPieData
import com.example.highcharttest.databinding.ReportAuraAllRecordsBinding

class AllRecordActivity : AppCompatActivity() {
    private lateinit var binding: ReportAuraAllRecordsBinding

    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_aura_all_records)
        binding = DataBindingUtil.setContentView(this, R.layout.report_aura_all_records)

        listView = findViewById(R.id.aura_list_view)
        val adapter = PieAllListAdapter(pieListData(weekPieData.totalTagInfoList), this)
        listView.adapter = adapter
    }
}