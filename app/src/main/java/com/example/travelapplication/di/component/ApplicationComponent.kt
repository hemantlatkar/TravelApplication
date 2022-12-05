package com.example.travelapplication.di.component

import com.example.travelapplication.MyApplication
import com.example.travelapplication.di.module.ActivityBuilder
import com.example.travelapplication.di.module.ApplicationModule
import com.example.travelapplication.di.module.FragmentModule
import com.example.travelapplication.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (NetworkModule::class),
        (ApplicationModule::class),
        (ActivityBuilder::class),
        (FragmentModule::class)]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MyApplication)
}
