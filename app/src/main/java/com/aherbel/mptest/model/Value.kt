package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName

data class Value(
	val name: String? = null,
	@SerializedName("path_from_root") val pathFromRoot: ArrayList<PathFromRoot?>? = null,
	val id: String? = null
)
