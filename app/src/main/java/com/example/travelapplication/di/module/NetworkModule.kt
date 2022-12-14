package com.example.travelapplication.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.travelapplication.di.BaseUrl
import com.example.travelapplication.dataSource.APIEndPoint
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, @BaseUrl baseUrl: String): Retrofit {
        var retrofit: Retrofit? = null
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(StethoInterceptor()).build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): APIEndPoint {
        return retrofit.create(APIEndPoint::class.java)
    }
}
