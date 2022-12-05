package com.example.travelapplication.dataSource

import com.example.travelapplication.data.TourModel.RequestObject
import com.example.travelapplication.data.TourModel.ResponseObjectItem
import io.reactivex.Single
import retrofit2.http.*

interface APIEndPoint {

    @POST("/route/inventory/getTourPackageDataWeb")
    fun getSearchTourList(@Body request : RequestObject): Single<List<ResponseObjectItem>>

}
