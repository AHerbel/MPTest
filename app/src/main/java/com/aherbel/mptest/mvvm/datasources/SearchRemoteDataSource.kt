package com.aherbel.mptest.mvvm.datasources

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aherbel.mptest.R
import com.aherbel.mptest.network.MPApi
import com.aherbel.mptest.network.responses.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class SearchRemoteDataSource(private val mpApi: MPApi) : SearchDataSource {

    private val logTag = "SearchMPTestAPI"

    override fun searchByQuery(query: String): LiveData<SearchResult> {
        val result = MutableLiveData<SearchResult>()

        mpApi.searchByQuery(query).enqueue(object : Callback<SearchResponse> {

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e(logTag, t.message, t)
                result.postValue(
                    if (t is UnknownHostException) {
                        SearchResult.Error(error = t, msgRes = R.string.no_internet_connection)
                    } else {
                        SearchResult.Error(error = t, msgRes = R.string.general_error)
                    }
                )
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val items = response.body()
                    result.postValue(SearchResult.Success(items))
                } else {
                    val errorMessage = response.message()
                    Log.e(logTag, errorMessage)
                    result.postValue(SearchResult.Error(msg = errorMessage))
                }
            }

        })

        result.postValue(SearchResult.Loading)

        return result
    }

}