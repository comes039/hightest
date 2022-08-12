package com.example.highcharttest.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.highcharttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val content = this
        binding.auraButton.setOnClickListener {
            val intent = Intent(content, ReportActivity::class.java)
            startActivity(intent)
        }
    }

}