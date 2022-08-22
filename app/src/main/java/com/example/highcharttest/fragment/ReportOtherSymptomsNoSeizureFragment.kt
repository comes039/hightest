package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.R
import com.example.highcharttest.data.week
import com.example.highcharttest.databinding.ReportOtherSymptomsNoSeizureBinding

class ReportOtherSymptomsNoSeizureFragment : Fragment() {

    private lateinit var binding: ReportOtherSymptomsNoSeizureBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportOtherSymptomsNoSeizureBinding.inflate(layoutInflater)


        // 기본값 week 로설정
        binding.date.text = getString(R.string.report_aura_date, week.startDate, week.endDate)
        binding.add.setOnClickListener {

//            val intent = Intent(getContext(), AllRecordActivity::class.java)
//            startActivity(intent)

        }

        return binding.root

    }


}