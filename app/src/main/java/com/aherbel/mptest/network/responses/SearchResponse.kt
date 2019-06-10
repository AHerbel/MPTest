package com.aherbel.mptest.network.responses

import com.aherbel.mptest.model.Filter
import com.aherbel.mptest.model.Paging
import com.aherbel.mptest.model.Product
import com.aherbel.mptest.model.Sort
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("site_id") val siteId: String? = null,
    val query: String? = null,
    val paging: Paging? = null,
    val results: ArrayList<Product>? = null,
    @SerializedName("secondary_results") val secondaryResults: ArrayList<Product>? = null,
    @SerializedName("related_results") val relatedResults: ArrayList<Product>? = null,
    val sort: Sort? = null,
    @SerializedName("available_sorts") val availableSorts: ArrayList<Sort>? = null,
    val filters: ArrayList<Filter>? = null,
    @SerializedName("available_filters") val availableFilters: ArrayList<Filter>? = null
)