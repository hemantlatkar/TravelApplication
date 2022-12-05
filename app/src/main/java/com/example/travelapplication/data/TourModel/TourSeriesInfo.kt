package com.example.travelapplication.data.TourModel


import com.google.gson.annotations.SerializedName

class TourSeriesInfo {
    @SerializedName("airline_route")
    val airlineRoute: String? = null

    @SerializedName("airlines_included")
    val airlinesIncluded: List<Any>? = null

    @SerializedName("cities_covered")
    val citiesCovered: List<CitiesCovered>? = null

    @SerializedName("competitor_agent_used")
    val competitorAgentUsed: String? = null

    @SerializedName("competitor_company")
    val competitorCompany: String? = null

    @SerializedName("continents")
    val continents: String? = null

    @SerializedName("countries_covered")
    val countriesCovered: List<String>? = null

    @SerializedName("end_city_point")
    val endCityPoint: String? = null

    @SerializedName("end_country")
    val endCountry: String? = null

    @SerializedName("festival_months")
    val festivalMonths: List<Any>? = null

    @SerializedName("guest_potential_city")
    val guestPotentialCity: String? = null

    @SerializedName("guest_type")
    val guestType: String? = null

    @SerializedName("high_season_months")
    val highSeasonMonths: List<Any>? = null

    @SerializedName("hotel_suggested")
    val hotelSuggested: String? = null

    @SerializedName("journey_by_road")
    val journeyByRoad: String? = null

    @SerializedName("journey_hours")
    val journeyHours: Any? = null

    @SerializedName("low_season_months")
    val lowSeasonMonths: List<Any>? = null

    @SerializedName("off_season_months")
    val offSeasonMonths: List<Any>? = null

    @SerializedName("precautions")
    val precautions: String? = null

    @SerializedName("price_from")
    val priceFrom: Any? = null

    @SerializedName("price_to")
    val priceTo: Any? = null

    @SerializedName("start_city_point")
    val startCityPoint: String? = null

    @SerializedName("start_country")
    val startCountry: String? = null

    @SerializedName("static_map_image")
    val staticMapImage: String? = null

    @SerializedName("super_peak_season_months")
    val superPeakSeasonMonths: List<Any>? = null

    @SerializedName("things_to_carry")
    val thingsToCarry: String? = null

    @SerializedName("total_city")
    val totalCity: Int? = null

    @SerializedName("total_country")
    val totalCountry: Int? = null

    @SerializedName("total_groups_yearly")
    val totalGroupsYearly: Any? = null

    @SerializedName("total_nights")
    val totalNights: Any? = null

    @SerializedName("tour_operator")
    val tourOperator: String? = null

    @SerializedName("tour_route")
    val tourRoute: String? = null

    @SerializedName("transport_agent_suggested")
    val transportAgentSuggested: String? = null
}