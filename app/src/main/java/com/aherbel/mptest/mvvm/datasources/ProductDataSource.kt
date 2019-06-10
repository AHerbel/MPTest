package com.aherbel.mptest.mvvm.datasources

import androidx.lifecycle.LiveData
import com.aherbel.mptest.model.Description
import com.aherbel.mptest.model.Product
import retrofit2.http.Path

interface ProductDataSource {

    fun getProduct(@Path("Item_id") productId: String): LiveData<ProductResult>
    fun getProductDescription(@Path("Item_id") productId: String): LiveData<DescriptionResult>
}

sealed class ProductResult {
    data class Success(val product: Product? = null) : ProductResult()
    data class Error(val error: Throwable? = null, val msg: String? = null, val msgRes: Int? = null) : ProductResult()
    object Loading : ProductResult()
}

sealed class DescriptionResult {
    data class Success(val description: Description? = null) : DescriptionResult()
    data class Error(val error: Throwable? = null, val msg: String? = null, val msgRes: Int? = null) : DescriptionResult()
    object Loading : DescriptionResult()
}