package com.aherbel.mptest.mvvm.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aherbel.mptest.model.Product
import com.aherbel.mptest.mvvm.LiveDataTestUtil
import com.aherbel.mptest.mvvm.datasources.FakeSearchRemoteDatasource
import com.aherbel.mptest.mvvm.datasources.SearchResult
import com.aherbel.mptest.mvvm.repositories.SearchRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setupViewModel() {
        val fakeSearchRemoteDataSource = FakeSearchRemoteDatasource().apply {
            addProducts(
                listOf(
                    Product(title = "Producto 1"),
                    Product(title = "Producto 2"),
                    Product(title = "Producto 3"),
                    Product(title = "Producto 4"),
                    Product(title = "Producto 5"),
                    Product(title = "Producto 6"),
                    Product(title = "Producto 7")
                )
            )
        }
        mainViewModel = MainViewModel(SearchRepository(fakeSearchRemoteDataSource))
    }

    @Test
    fun searchByQuery_emptyQuery() {

        mainViewModel.searchByQuery("")

        val result = LiveDataTestUtil.getValue(mainViewModel.searchResult)

        assertThat(result).isInstanceOf(SearchResult.Success::class.java)

        val resultSuccess = result as SearchResult.Success

        assertThat(resultSuccess.searchResponse?.results).isNotNull()
        assertThat(resultSuccess.searchResponse?.results).isEmpty()
    }

    @Test
    fun searchByQuery_noResults() {

        mainViewModel.searchByQuery("Producto 22")

        val result = LiveDataTestUtil.getValue(mainViewModel.searchResult)

        assertThat(result).isInstanceOf(SearchResult.Success::class.java)

        val resultSuccess = result as SearchResult.Success

        assertThat(resultSuccess.searchResponse?.results).isNotNull()
        assertThat(resultSuccess.searchResponse?.results).isEmpty()
    }

    @Test
    fun searchByQuery_allResults() {

        mainViewModel.searchByQuery("Producto")

        val result = LiveDataTestUtil.getValue(mainViewModel.searchResult)

        assertThat(result).isInstanceOf(SearchResult.Success::class.java)

        val resultSuccess = result as SearchResult.Success

        assertThat(resultSuccess.searchResponse?.results).isNotNull()
        assertThat(resultSuccess.searchResponse?.results).isNotEmpty()
    }

    @Test
    fun searchByQuery_filteredResults() {

        mainViewModel.searchByQuery("Producto 1")

        val result = LiveDataTestUtil.getValue(mainViewModel.searchResult)

        assertThat(result).isInstanceOf(SearchResult.Success::class.java)

        val resultSuccess = result as SearchResult.Success

        assertThat(resultSuccess.searchResponse?.results).isNotNull()
        assertThat(resultSuccess.searchResponse?.results?.size).isEqualTo(1)
    }
}