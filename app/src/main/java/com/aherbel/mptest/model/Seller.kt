package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName


data class Seller(

    @SerializedName("id") val id: Int? = null,
    @SerializedName("power_seller_status") val powerSellerStatus: String? = null,
    @SerializedName("car_dealer") val carDealer: Boolean? = null,
    @SerializedName("real_estate_agency") val realEstateAgency: Boolean? = null,
    @SerializedName("tags") val tags: ArrayList<String>? = null
)