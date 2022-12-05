package com.example.travelapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.travelapplication.R
import com.example.travelapplication.databinding.ActivityTaskTwoBinding

class TaskTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskTwoBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_two)

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.navhost)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navhost)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}