package com.example.travelapplication.data.TourModel


import com.google.gson.annotations.SerializedName

 class ITIDAYS {
    @SerializedName("airCraftType")
    val airCraftType: String? = null

    @SerializedName("airline")
    val airline: String? = null

    @SerializedName("airlineGrp")
    val airlineGrp: String? = null

    @SerializedName("airlineName")
    val airlineName: String? = null

    @SerializedName("altAirline")
    val altAirline: String? = null

    @SerializedName("attraction")
    val attraction: List<Attraction>? = null

    @SerializedName("dayCategory")
    val dayCategory: String? = null

    @SerializedName("day_no")
    val dayNo: Int? = null

    @SerializedName("day_note")
    val dayNote: String? = null

    @SerializedName("descItinerary")
    val descItinerary: String? = null

    @SerializedName("details")
    val details: String? = null

    @SerializedName("events")
    val events: List<Any>? = null

    @SerializedName("from_city")
    val fromCity: String? = null

    @SerializedName("header")
    val header: String? = null

    @SerializedName("_id")
    val id: String? = null

    @SerializedName("journeyHrs")
    val journeyHrs: String? = null

    @SerializedName("journeyKMS")
    val journeyKMS: String? = null

    @SerializedName("meals")
    val meals: Meals? = null

    @SerializedName("modeOfTrans")
    val modeOfTrans: String? = null

    @SerializedName("seasons")
    val seasons: String? = null

    @SerializedName("to_city")
    val toCity: String? = null

    @SerializedName("tourRoute")
    val tourRoute: String? = null

    @SerializedName("xtop")
    val xtop: String? = null
 }