package com.aherbel.mptest.di

import com.aherbel.mptest.mvvm.datasources.ProductDataSource
import com.aherbel.mptest.mvvm.datasources.ProductRemoteDataSource
import com.aherbel.mptest.mvvm.datasources.SearchDataSource
import com.aherbel.mptest.mvvm.datasources.SearchRemoteDataSource
import com.aherbel.mptest.mvvm.repositories.ProductRepository
import com.aherbel.mptest.mvvm.repositories.SearchRepository
import com.aherbel.mptest.mvvm.viewmodels.MainViewModel
import com.aherbel.mptest.mvvm.viewmodels.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModels
    viewModel { MainViewModel(get()) }
    viewModel { ProductDetailsViewModel(get()) }

    // Repositories
    single { SearchRepository(get()) }
    single { ProductRepository(get()) }

    // DataSources
    single { SearchRemoteDataSource(get()) as SearchDataSource }
    single { ProductRemoteDataSource(get()) as ProductDataSource }

}