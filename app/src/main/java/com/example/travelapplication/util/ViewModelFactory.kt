package com.example.travelapplication.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelapplication.data.repository.PlaceListRepository
import com.example.travelapplication.viewmodel.PlacesViewModel
import javax.inject.Inject

class ViewModelFactory
    @Inject constructor(private val repository: PlaceListRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PlacesViewModel::class.java) -> PlacesViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
        }
    }
}