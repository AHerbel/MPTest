package com.aherbel.mptest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aherbel.mptest.databinding.RvItemLoadingBinding
import com.aherbel.mptest.databinding.RvItemProductBinding
import com.aherbel.mptest.model.Product
import com.aherbel.mptest.ui.viewholders.LoadingViewHolder
import com.aherbel.mptest.ui.viewholders.ProductViewHolder
import com.aherbel.mptest.ui.viewtypes.LoadingViewType
import com.aherbel.mptest.ui.viewtypes.ViewTypes

class ProductsListAdapter(products: ArrayList<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: ArrayList<Any> = ArrayList<Any>().apply { addAll(products) }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is Product -> ViewTypes.PRODUCT.ordinal
            is LoadingViewType -> ViewTypes.LOADING.ordinal
            else -> ViewTypes.PRODUCT.ordinal
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ViewTypes.PRODUCT.ordinal -> {
                val binding = RvItemProductBinding.inflate(inflater, parent, false)
                ProductViewHolder(binding)
            }
            ViewTypes.LOADING.ordinal -> {
                val binding = RvItemLoadingBinding.inflate(inflater, parent, false)
                LoadingViewHolder(binding)
            }
            else -> {
                val binding = RvItemProductBinding.inflate(inflater, parent, false)
                ProductViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = data[position]) {
            is Product -> (holder as ProductViewHolder).bind(item)
        }
    }

    fun addItems(products: ArrayList<Product>) {

    }
}