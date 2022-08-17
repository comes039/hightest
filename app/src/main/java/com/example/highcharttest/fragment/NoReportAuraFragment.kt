package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.data.week
import com.example.highcharttest.databinding.NoReportAuraBinding

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