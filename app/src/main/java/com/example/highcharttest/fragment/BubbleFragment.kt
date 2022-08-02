package com.example.highcharttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.highcharttest.databinding.BubbleChartBinding

class BubbleFragment : Fragment() {
    private lateinit var binding: BubbleChartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BubbleChartBinding.inflate(layoutInflater)
        return binding.root

    }
}