package com.example.highcharttest.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.highcharttest.databinding.ActivityReportBinding
import com.example.highcharttest.fragment.AuraFragment
import com.example.highcharttest.fragment.OtherSymptomsFragment
import com.google.android.material.tabs.TabLayoutMediator

class ReportActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityReportBinding
    lateinit var context: Context
    val fragmentList = listOf(AuraFragment(), OtherSymptomsFragment())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager, lifecycle)
        val tabIndex = intent.getIntExtra("tab_index", 0)

        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "TAB $position"
        }.attach()

        println("tab_index = $tabIndex")
        if (tabIndex == 0) {
            viewPager.currentItem = 0
        } else if (tabIndex == 1) {
            viewPager.currentItem = 1
        }

    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }

    }

    /**
     * A simple pager adapter that represents 2 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int = fragmentList.size
        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }
}