package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName

data class Picture(
    val id: String? = null,
    val url: String? = null,
    @SerializedName("secure_url") val secureUrl: String? = null,
    val size: String? = null,
    @SerializedName("max_size") val maxSize: String? = null,
    val quality: String? = null
)