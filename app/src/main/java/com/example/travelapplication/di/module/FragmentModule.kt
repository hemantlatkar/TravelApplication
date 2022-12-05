package com.example.travelapplication.di.module

import com.example.travelapplication.ui.fragments.AddRecordFragment
import com.example.travelapplication.ui.fragments.RecordListFragment
import com.example.travelapplication.ui.fragments.UpdateRecordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeRecordListFragment(): RecordListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeAddRecordFragment(): AddRecordFragment

    @ContributesAndroidInjector
    internal abstract fun contributeUpdateRecordFragment(): UpdateRecordFragment
}