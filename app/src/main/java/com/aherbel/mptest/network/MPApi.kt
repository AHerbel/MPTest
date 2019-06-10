package com.aherbel.mptest.network

import com.aherbel.mptest.model.Description
import com.aherbel.mptest.model.Product
import com.aherbel.mptest.network.responses.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MPApi {

    // SEARCH
    @GET("sites/MLA/search")
    fun searchByQuery(@Query("q") query: String): Call<SearchResponse>

    @GET("sites/MLA/search")
    fun searchByCategory(@Query("category") category: String): Call<SearchResponse>

    @GET("sites/MLA/search")
    fun searchBySellerId(@Query("seller_id") sellerId: String): Call<SearchResponse>

    @GET("sites/MLA/search")
    fun searchBySellerNickname(@Query("nickname") sellerNickname: String): Call<SearchResponse>

    @GET("sites/MLA/search")
    fun searchBySpecialFilter(@Query("special_filter") specialFilter: String): Call<SearchResponse>

    @GET("sites/MLA/search")
    fun searchByOrder(@Query("q") query: String, @Query("sort") sort: String): Call<SearchResponse>

    @GET("sites/MLA/search")
    fun searchByOrderId(@Query("q") query: String, @Query("sort_id") sortId: String): Call<SearchResponse>

    @GET("sites/MLA/search")
    fun searchByFilter(@Query("q") query: String, @Query("FilterID") filterValue: String): Call<SearchResponse>

    // PRODUCT
    @GET("/items/{Item_id}")
    fun getProduct(@Path("Item_id") productId: String): Call<Product>

    @GET("/items/{Item_id}/description")
    fun getProductDescription(@Path("Item_id") productId: String): Call<Description>
}