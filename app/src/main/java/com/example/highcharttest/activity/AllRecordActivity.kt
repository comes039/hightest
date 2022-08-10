package com.example.highcharttest.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import com.example.highcharttest.R
import com.example.highcharttest.adaptor.PieAllListAdapter
import com.example.highcharttest.chart.data.SampleData
import com.example.highcharttest.databinding.ReportAuraAllRecordsBinding

public class AllRecordActivity : AppCompatActivity() {
    private lateinit var binding: ReportAuraAllRecordsBinding
//    lateinit var context:Context
    lateinit var listView: ListView
    val pieList = arrayListOf(
        SampleData("Double vision", 45, 1, "75%"),
        SampleData("Headache", 11, 2, "12%"),
        SampleData("Unknown", 8, 3, "5%"),
        SampleData("Anxiety", 5, 4, "4%"),
        SampleData("Upset stomach", 5, 4, "1%"),
        SampleData("Sleep issue", 5, 4, "1%"),
        SampleData("Tiredness", 5, 4, "0.5%")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_aura_all_records)

//        binding = ReportAuraAllRecordsBinding.inflate(layoutInflater)
        listView = findViewById(R.id.aura_list_view)
        val adapter = PieAllListAdapter(pieList, this)
        listView.adapter = adapter
//        binding.auraListView.adapter = adapter
    }
}