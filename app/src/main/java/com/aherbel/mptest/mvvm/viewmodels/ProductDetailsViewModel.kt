package com.aherbel.mptest.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aherbel.mptest.mvvm.datasources.DescriptionResult
import com.aherbel.mptest.mvvm.datasources.ProductResult
import com.aherbel.mptest.mvvm.repositories.ProductRepository

class ProductDetailsViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var lastProductId: String = ""

    val product: LiveData<ProductResult> = productRepository.product
    val description: LiveData<DescriptionResult> = productRepository.description

    fun getProduct(productId: String) {
        productRepository.getProduct(productId)
        lastProductId = productId
    }
    fun retry() = productRepository.getProduct(lastProductId)
}