package com.aherbel.mptest.di

import com.aherbel.mptest.BuildConfig
import com.aherbel.mptest.network.MPApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataSourceModule = module {

    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    single<MPApi> { get<Retrofit>().create(MPApi::class.java) }
}