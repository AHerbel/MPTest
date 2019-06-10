package com.aherbel.mptest.mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.aherbel.mptest.mvvm.datasources.SearchDataSource
import com.aherbel.mptest.mvvm.datasources.SearchResult

class SearchRepository(private val searchRemoteDataSource: SearchDataSource) {

    private val _query: MutableLiveData<String> = MutableLiveData()

    val searchResult: LiveData<SearchResult> = Transformations.switchMap(_query) { query ->
        searchRemoteDataSource.searchByQuery(query)
    }

    fun searchByQuery(query: String) = _query.postValue(query)
}