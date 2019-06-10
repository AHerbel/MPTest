package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName

data class Paging(
	val total: Int? = null,
	val offset: Int? = null,
	val limit: Int? = null,
	@SerializedName("primary_results") val primaryResults: Int? = null
)
