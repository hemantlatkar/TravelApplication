package com.example.travelapplication.dataSource

import androidx.paging.ItemKeyedDataSource
import com.example.travelapplication.data.TourModel.RequestObject
import com.example.travelapplication.data.TourModel.ResponseObjectItem
import io.reactivex.disposables.CompositeDisposable

class PlaceDataSource(
    private val networkAPIEndPoint: APIEndPoint,
    private val compositeDisposable: CompositeDisposable,
    private val tourSeries: String,
    private val zone: String,
    private val tourID: String
) : ItemKeyedDataSource<Int, ResponseObjectItem>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<ResponseObjectItem>) {
        val requestObj = RequestObject(tourSeries,zone,tourID)
        compositeDisposable.add(networkAPIEndPoint.getSearchTourList(requestObj).subscribe(
            { response ->
                callback.onResult(response)
            }, { throwable -> }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<ResponseObjectItem>) {
        /*val requestObj = RequestObject(tourSeries,zone,tourID)
        compositeDisposable.add(networkAPIEndPoint.getSearchTourList(requestObj).subscribe(
            { response ->
                callback.onResult(response)
        }, { throwable -> }))*/
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<ResponseObjectItem>) {

    }

    override fun getKey(item: ResponseObjectItem): Int {
        return 0
    }
}
