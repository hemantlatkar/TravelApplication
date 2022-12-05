package com.example.travelapplication.data.TourModel


import com.google.gson.annotations.SerializedName

 class Flag {
    @SerializedName("country_name")
    val countryName: String? = null

    @SerializedName("iso_code")
    val isoCode: String? = null

    @SerializedName("number_of_nights")
    val numberOfNights: Int? = null
 }