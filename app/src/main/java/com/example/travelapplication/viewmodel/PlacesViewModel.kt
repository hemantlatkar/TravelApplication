package com.example.travelapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.travelapplication.data.TourModel.ResponseObjectItem
import com.example.travelapplication.data.repository.PlaceListRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class PlacesViewModel(private val repository: PlaceListRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getPlacesList(tourSeries: String, zone: String, tourID: String): LiveData<PagedList<ResponseObjectItem>> {
        return repository.getPlacesList(tourSeries, zone,tourID)
    }
    /*
       Adding disposables using extension function
     */
    operator fun CompositeDisposable.plusAssign(disposable: DisposableSingleObserver<ResponseObjectItem>) {
        add(disposable)
    }

    /*
       clearing all disposables
     */
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}