package com.aherbel.mptest.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aherbel.mptest.mvvm.datasources.SearchResult
import com.aherbel.mptest.mvvm.repositories.SearchRepository

class MainViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private var lastQuery: String = ""
    val searchResult: LiveData<SearchResult> = searchRepository.searchResult

    fun searchByQuery(query: String) {
        searchRepository.searchByQuery(query)
        lastQuery = query
    }

    fun retry() = searchRepository.searchByQuery(lastQuery)

}