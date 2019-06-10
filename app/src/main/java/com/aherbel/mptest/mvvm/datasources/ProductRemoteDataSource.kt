package com.aherbel.mptest.mvvm.datasources

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aherbel.mptest.R
import com.aherbel.mptest.model.Description
import com.aherbel.mptest.model.Product
import com.aherbel.mptest.network.MPApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class ProductRemoteDataSource(private val mpApi: MPApi) : ProductDataSource {

    private val logTag = "ProductMPTestAPI"

    override fun getProduct(productId: String): LiveData<ProductResult> {
        val result = MutableLiveData<ProductResult>()
        mpApi.getProduct(productId).enqueue(object : Callback<Product> {
            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.e(logTag, t.message, t)
                result.postValue(
                    if (t is UnknownHostException) {
                        ProductResult.Error(error = t, msgRes = R.string.no_internet_connection)
                    } else {
                        ProductResult.Error(error = t, msgRes = R.string.general_error)
                    }
                )
            }

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    val product = response.body()
                    result.postValue(ProductResult.Success(product))
                } else {
                    val errorMessage = response.message()
                    Log.e(logTag, errorMessage)
                    result.postValue(ProductResult.Error(msg = errorMessage))
                }
            }
        })
        return result
    }

    override fun getProductDescription(productId: String): LiveData<DescriptionResult> {
        val result = MutableLiveData<DescriptionResult>()
        mpApi.getProductDescription(productId).enqueue(object : Callback<Description> {

            override fun onFailure(call: Call<Description>, t: Throwable) {
                Log.e(logTag, t.message, t)
                result.postValue(
                    if (t is UnknownHostException) {
                        DescriptionResult.Error(error = t, msgRes = R.string.no_internet_connection)
                    } else {
                        DescriptionResult.Error(error = t, msgRes = R.string.general_error)
                    }
                )
            }

            override fun onResponse(call: Call<Description>, response: Response<Description>) {
                if (response.isSuccessful) {
                    val description = response.body()
                    result.postValue(DescriptionResult.Success(description))
                } else {
                    val errorMessage = response.message()
                    Log.e(logTag, errorMessage)
                    result.postValue(DescriptionResult.Error(msg = errorMessage))
                }
            }

        })
        return result
    }
}