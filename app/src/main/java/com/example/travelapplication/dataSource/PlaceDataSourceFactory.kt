package com.example.travelapplication.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.travelapplication.data.TourModel.ResponseObjectItem
import io.reactivex.disposables.CompositeDisposable

class PlaceDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkAPIEndPoint: APIEndPoint,
    private val tourSeries: String,
    private val zone: String,
    private val tourID: String
) : DataSource.Factory<Int, ResponseObjectItem>() {

    private val newsDataSourceLiveData = MutableLiveData<PlaceDataSource>()

    override fun create(): DataSource<Int, ResponseObjectItem> {
        val newsDataSource = PlaceDataSource(networkAPIEndPoint, compositeDisposable, tourSeries, zone,tourID)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}