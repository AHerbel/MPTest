package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName

data class State (

	@SerializedName("id") val id : String? = null,
	@SerializedName("name") val name : String? = null
)