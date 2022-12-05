package com.example.travelapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.travelapplication.R
import com.example.travelapplication.databinding.ActivityDashboardBinding

class DashBoardActivity : AppCompatActivity() {

    private var _binding: ActivityDashboardBinding? = null
    private val binding : ActivityDashboardBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        binding.btnTaskOne.setOnClickListener {
            startActivity(Intent(this@DashBoardActivity, TaskOneActivity::class.java))
        }
        binding.btnTaskTwo.setOnClickListener {
            startActivity(Intent(this@DashBoardActivity, TaskTwoActivity::class.java))
        }
    }
}