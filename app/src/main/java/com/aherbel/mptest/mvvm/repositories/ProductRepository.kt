package com.aherbel.mptest.mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.aherbel.mptest.mvvm.datasources.DescriptionResult
import com.aherbel.mptest.mvvm.datasources.ProductDataSource
import com.aherbel.mptest.mvvm.datasources.ProductResult

class ProductRepository(private val productRemoteDataSource: ProductDataSource) {

    private val _productId: MutableLiveData<String> = MutableLiveData()

    val product: LiveData<ProductResult> = Transformations.switchMap(_productId) { productId: String ->
        productRemoteDataSource.getProduct(productId)
    }

    val description: LiveData<DescriptionResult> = Transformations.switchMap(_productId) { productId: String ->
        productRemoteDataSource.getProductDescription(productId)
    }

    fun getProduct(productId: String) = _productId.postValue(productId)

}