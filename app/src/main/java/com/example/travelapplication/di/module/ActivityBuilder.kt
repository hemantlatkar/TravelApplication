package com.example.travelapplication.di.module

import com.example.travelapplication.ui.DashBoardActivity
import com.example.travelapplication.ui.TaskOneActivity
import com.example.travelapplication.ui.TaskTwoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindTaskDashBoardActivity(): DashBoardActivity

    @ContributesAndroidInjector()
    abstract fun bindTaskOneActivity(): TaskOneActivity

    @ContributesAndroidInjector()
    abstract fun bindTaskTwoActivity(): TaskTwoActivity
}