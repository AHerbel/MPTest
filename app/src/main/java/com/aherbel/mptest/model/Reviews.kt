package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName


data class Reviews (

	@SerializedName("rating_average") val ratingAverage : Double? = null,
	@SerializedName("total") val total : Int? = null
)