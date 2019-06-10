package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("text") val text: String? = null,
    @SerializedName("plain_text") val plainText: String? = null
)