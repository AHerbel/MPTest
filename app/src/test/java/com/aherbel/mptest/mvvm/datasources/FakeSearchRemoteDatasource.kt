package com.aherbel.mptest.mvvm.datasources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aherbel.mptest.model.Product
import com.aherbel.mptest.network.responses.SearchResponse

class FakeSearchRemoteDatasource : SearchDataSource {

    private val products: ArrayList<Product> = ArrayList()

    override fun searchByQuery(query: String): LiveData<SearchResult> {
        return MutableLiveData<SearchResult>().apply {
            if (query.isEmpty()) {
                postValue(SearchResult.Success(SearchResponse(results = ArrayList())))
            } else {
                val filteredResults = products.filter { it.title?.contains(query) == true }
                postValue(SearchResult.Success(SearchResponse(results = ArrayList(filteredResults))))
            }
        }
    }


    fun addProducts(products: List<Product>) = this.products.addAll(products)
}