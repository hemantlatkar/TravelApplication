package com.example.travelapplication.data.TourModel


import com.google.gson.annotations.SerializedName

class ResponseObjectItem {
    @SerializedName("BILL_CURR")
    val bILLCURR: String? = null

    @SerializedName("bonanza_cost")
    val bonanzaCost: Any? = null

    @SerializedName("currency")
    val currency: String? = null

    @SerializedName("end_city")
    val endCity: String? = null

    @SerializedName("end_country")
    val endCountry: String? = null

    @SerializedName("F_BRAND")
    val fBRAND: String? = null

    @SerializedName("F_BUSTYPE")
    val fBUSTYPE: String? = null

    @SerializedName("F_HTLCATG")
    val fHTLCATG: String? = null

    @SerializedName("F_TYPE")
    val fTYPE: String? = null

    @SerializedName("F_VISIT")
    val fVISIT: String? = null

    @SerializedName("flags")
    val flags: List<Flag>? = null

    @SerializedName("generated_COST_2DAY")
    val generatedCOST2DAY: String? = null

    @SerializedName("generated_COST_CUR")
    val generatedCOSTCUR: String? = null

    @SerializedName("generated_COST_FRX")
    val generatedCOSTFRX: String? = null

    @SerializedName("generated_COST_INR")
    val generatedCOSTINR: String? = null

    @SerializedName("generated_DISC_2DAY")
    val generatedDISC2DAY: String? = null

    @SerializedName("generated_NETCOST")
    val generatedNETCOST: String? = null

    @SerializedName("generated_TM_DT1")
    val generatedTMDT1: String? = null

    @SerializedName("ITI_DAYS")
    val iTIDAYS: List<ITIDAYS>? = null

    @SerializedName("ITINERARY")
    val iTINERARY: String? = null

    @SerializedName("_id")
    val id: String? = null

    @SerializedName("images")
    val images: List<String>? = null

    @SerializedName("journeyCategory")
    val journeyCategory: String? = null

    @SerializedName("LST_2DEAL")
    val lST2DEAL: Int? = null

    @SerializedName("monthArray")
    val monthArray: List<String>? = null

    @SerializedName("NIGHTS")
    val nIGHTS: Int? = null

    @SerializedName("SUGG_AIRLN")
    val sUGGAIRLN: String? = null

    @SerializedName("start_city")
    val startCity: String? = null

    @SerializedName("start_country")
    val startCountry: String? = null

    @SerializedName("TM_FINSECT")
    val tMFINSECT: String? = null

    @SerializedName("TM_SDESC")
    val tMSDESC: String? = null

    @SerializedName("TM_ZONE")
    val tMZONE: String? = null

    @SerializedName("TOURCODE")
    val tOURCODE: String? = null

    @SerializedName("TOUR_ID")
    val tOURID: String? = null

    @SerializedName("TOURMAS0")
    val tOURMAS0: List<TOURMAS0>? = null

    @SerializedName("TOURNAME")
    val tOURNAME: String? = null

    @SerializedName("TOURNOSHOW")
    val tOURNOSHOW: String? = null

    @SerializedName("tour_join_leave_alerts")
    val tourJoinLeaveAlerts: List<Any>? = null

    @SerializedName("tour_month")
    val tourMonth: Any? = null

    @SerializedName("tour_series_info")
    val tourSeriesInfo: TourSeriesInfo? = null

    @SerializedName("tour_year")
    val tourYear: Any? = null

    @SerializedName("tourzoneBottomdesc")
    val tourzoneBottomdesc: String? = null

    @SerializedName("tourzonedescription")
    val tourzonedescription: String? = null

    @SerializedName("visa_alerts")
    val visaAlerts: VisaAlerts? = null

    @SerializedName("visit")
    val visit: String? = null

    @SerializedName("webBannerImage")
    val webBannerImage: String? = null
}