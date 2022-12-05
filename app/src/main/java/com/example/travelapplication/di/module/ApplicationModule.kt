package com.example.travelapplication.di.module

import com.example.travelapplication.di.BaseUrl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://login.kesari.in"
    }
}