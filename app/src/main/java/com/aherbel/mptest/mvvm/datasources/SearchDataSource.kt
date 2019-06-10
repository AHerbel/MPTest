package com.aherbel.mptest.mvvm.datasources

import androidx.lifecycle.LiveData
import com.aherbel.mptest.network.responses.SearchResponse

interface SearchDataSource {

    fun searchByQuery(query: String): LiveData<SearchResult>

}

sealed class SearchResult {
    data class Success(val searchResponse: SearchResponse? = null) : SearchResult()
    data class Error(val error: Throwable? = null, val msg: String? = null, val msgRes: Int? = null) : SearchResult()
    object Loading : SearchResult()
}