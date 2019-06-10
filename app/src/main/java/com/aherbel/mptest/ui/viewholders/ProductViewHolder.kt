package com.aherbel.mptest.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aherbel.mptest.databinding.RvItemProductBinding
import com.aherbel.mptest.model.Product
import com.aherbel.mptest.ui.activities.ProductDetailsActivity

class ProductViewHolder(private val binding: RvItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.apply {
            this.product = product
            executePendingBindings()

            clRoot.setOnClickListener {
                ProductDetailsActivity.start(it.context, product.id.orEmpty())
            }
        }
    }
}