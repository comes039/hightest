package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.adaptor.PieListAdapter
import com.example.highcharttest.chart.data.SampleData
import com.example.highcharttest.databinding.ReportAuraAllRecordsBinding

class AuraAllRecordsFragment : Fragment() {

    private lateinit var binding: ReportAuraAllRecordsBinding

    val pieList = arrayListOf(
        SampleData("Double vision", 45, 1, "75%"),
        SampleData("Headache", 11, 2, "12%"),
        SampleData("Unknown", 8, 3, "5%"),
        SampleData("Anxiety", 5, 4, "4%"),
        SampleData("Upset stomach", 5, 4, "1%"),
        SampleData("Sleep issue", 5, 4, "1%"),
        SampleData("Tiredness", 5, 4, "0.5%")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportAuraAllRecordsBinding.inflate(layoutInflater)
        val context = this.context
        val adapter = PieListAdapter(pieList, context)

        return binding.root

    }


}