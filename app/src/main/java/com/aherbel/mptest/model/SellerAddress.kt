package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName

data class SellerAddress (

	@SerializedName("id") val id : String? = null,
	@SerializedName("comment") val comment : String? = null,
	@SerializedName("address_line") val addressLine : String? = null,
	@SerializedName("zip_code") val zipCode : String? = null,
	@SerializedName("country") val country : Country? = null,
	@SerializedName("state") val state : State? = null,
	@SerializedName("city") val city : City? = null,
	@SerializedName("latitude") val latitude : String? = null,
	@SerializedName("longitude") val longitude : String? = null
)