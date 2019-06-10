package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName

data class Address (

	@SerializedName("state_id") val stateId : String? = null,
	@SerializedName("state_name") val stateName : String? = null,
	@SerializedName("city_id") val cityId : String? = null,
	@SerializedName("city_name") val cityName : String? = null
)