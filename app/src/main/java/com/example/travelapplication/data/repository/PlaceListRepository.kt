package com.example.travelapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.travelapplication.data.TourModel.ResponseObjectItem
import com.example.travelapplication.dataSource.PlaceDataSourceFactory
import com.example.travelapplication.dataSource.APIEndPoint
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PlaceListRepository @Inject constructor(private val APIEndPoint: APIEndPoint) {

    lateinit var placeList: LiveData<PagedList<ResponseObjectItem>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private var newsDataSourceFactory: PlaceDataSourceFactory? = null
    
    fun getPlacesList(tourSeries: String, zone: String, tourID: String): LiveData<PagedList<ResponseObjectItem>> {
        newsDataSourceFactory =
            PlaceDataSourceFactory(compositeDisposable, APIEndPoint, tourSeries, zone,tourID)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        placeList = LivePagedListBuilder(newsDataSourceFactory!!, config).build()
        return placeList
    }
}
