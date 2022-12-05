package com.example.travelapplication.data.TourModel


import com.google.gson.annotations.SerializedName

 class CitiesCovered{
    @SerializedName("city_name")
    val cityName: String? = null
    @SerializedName("country_name")
    val countryName: String? = null
    @SerializedName("_id")
    val id: String? = null
    @SerializedName("iso_code_2")
    val isoCode2: String? = null
    @SerializedName("mode_of_transport")
    val modeOfTransport: String? = null
    @SerializedName("number_of_nights")
    val numberOfNights: Int? = null
}