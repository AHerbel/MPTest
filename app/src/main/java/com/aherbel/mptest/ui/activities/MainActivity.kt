package com.aherbel.mptest.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.aherbel.mptest.R
import com.aherbel.mptest.databinding.ActivityMainBinding
import com.aherbel.mptest.mvvm.datasources.SearchResult
import com.aherbel.mptest.mvvm.viewmodels.MainViewModel
import com.aherbel.mptest.ui.adapters.ProductsListAdapter
import com.aherbel.mptest.ui.viewtypes.ViewTypes
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private var productsListAdapter: ProductsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)?.apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.searchByQuery(query.orEmpty())
                searchView?.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        val gridLayoutManager = rvProducts?.layoutManager as? GridLayoutManager
        gridLayoutManager?.apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (productsListAdapter?.getItemViewType(position) == ViewTypes.LOADING.ordinal) {
                        2
                    } else {
                        1
                    }
                }
            }
        }

        mainViewModel.apply {
            searchResult.observe(this@MainActivity, Observer { searchResult: SearchResult ->
                when (searchResult) {
                    is SearchResult.Success -> {
                        val products = searchResult.searchResponse?.results ?: ArrayList()
                        if (products.isEmpty()) {
                            tvError?.setText(R.string.no_products_found)
                            rvProducts?.visibility = View.GONE
                            groupError?.visibility = View.VISIBLE
                        } else {
                            rvProducts?.visibility = View.VISIBLE
                            groupError?.visibility = View.GONE
                            productsListAdapter = ProductsListAdapter(products)
                            rvProducts?.adapter = productsListAdapter
                        }

                        progress?.visibility = View.GONE
                    }
                    is SearchResult.Error -> {
                        progress?.visibility = View.GONE
                        searchResult.msg?.let {
                            toast(it)
                        } ?: searchResult.msgRes?.let {
                            tvError?.setText(it)
                            rvProducts?.visibility = View.GONE
                            groupError?.visibility = View.VISIBLE
                        }
                    }
                    is SearchResult.Loading -> {
                        groupError?.visibility = View.GONE
                        progress?.visibility = View.VISIBLE
                    }
                }
            })
        }
    }
}
