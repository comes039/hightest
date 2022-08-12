package com.example.highcharttest.adaptor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.highcharttest.fragment.AuraAllRecordsFragment
import com.example.highcharttest.fragment.BubbleFragment
import com.example.highcharttest.fragment.NoReportAuraFragment

private const val NUM_TABS = 3

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return BubbleFragment()
            1 -> return AuraAllRecordsFragment()
            2 -> return NoReportAuraFragment()
        }
        return NoReportAuraFragment()
    }
}