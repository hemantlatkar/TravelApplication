package com.example.travelapplication.data.TourModel


import com.google.gson.annotations.SerializedName

class VisaAlerts{
    @SerializedName("alerts")
    val alerts: List<Any>? = null
            
    @SerializedName("visa_require")
    val visaRequire: List<String>? = null
}