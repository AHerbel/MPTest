package com.aherbel.mptest.ui.activities

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.aherbel.mptest.R
import com.aherbel.mptest.databinding.ActivityProductDetailsBinding
import com.aherbel.mptest.mvvm.datasources.DescriptionResult
import com.aherbel.mptest.mvvm.datasources.ProductResult
import com.aherbel.mptest.mvvm.viewmodels.ProductDetailsViewModel
import com.aherbel.mptest.ui.adapters.AttributesListAdapter
import com.aherbel.mptest.utils.GlideApp
import kotlinx.android.synthetic.main.activity_product_details.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailsActivity : AppCompatActivity() {

    companion object {
        private const val PRODUCT_ID = "PRODUCT_ID"
        fun start(context: Context, productId: String) =
            context.startActivity<ProductDetailsActivity>(PRODUCT_ID to productId)
    }

    private val productDetailsViewModel: ProductDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityProductDetailsBinding>(this, R.layout.activity_product_details)

        tvOriginalPrice?.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        ivBack?.setOnClickListener {
            onBackPressed()
        }

        tvRetry?.setOnClickListener {
            productDetailsViewModel.retry()
        }

        productDetailsViewModel.apply {

            product.observe(this@ProductDetailsActivity, Observer { productResult: ProductResult ->
                when (productResult) {
                    is ProductResult.Success -> {
                        val product = productResult.product
                        binding.product = product
                        rvAttributes?.adapter = AttributesListAdapter(product?.attributes ?: arrayListOf())

                        if (product?.pictures?.isNotEmpty() == true) {
                            GlideApp.with(ivImage)
                                .load(product.pictures[0].url.orEmpty())
                                .error(R.drawable.ic_image_not_available)
                                .into(ivImage)
                        } else {
                            GlideApp.with(ivImage)
                                .load(R.drawable.ic_image_not_available)
                                .into(ivImage)
                        }

                        groupProduct?.visibility = View.VISIBLE
                        groupError?.visibility = View.GONE
                        progress?.visibility = View.GONE

                        runOnUiThread { scrollview?.smoothScrollTo(0, 0) }
                    }
                    is ProductResult.Error -> {
                        productResult.msg?.let {
                            toast(it)
                        } ?: productResult.msgRes?.let {
                            tvError?.setText(it)
                        }
                        groupError?.visibility = View.VISIBLE
                        groupProduct?.visibility = View.GONE
                        progress?.visibility = View.GONE
                    }
                    is ProductResult.Loading -> progress?.visibility = View.VISIBLE
                }
            })

            description.observe(this@ProductDetailsActivity, Observer { descriptionResult: DescriptionResult ->
                when (descriptionResult) {
                    is DescriptionResult.Success -> {
                        if (groupProduct?.visibility == View.VISIBLE) {
                            groupError?.visibility = View.GONE
                            groupDescription?.visibility = View.VISIBLE
                        }
                        binding.description = descriptionResult.description?.plainText
                        progress?.visibility = View.GONE
                    }
                    is DescriptionResult.Error -> {
                        descriptionResult.msg?.let {
                            toast(it)
                        } ?: descriptionResult.msgRes?.let {
                            tvError?.setText(it)
                        }
                        groupError?.visibility = View.VISIBLE
                        groupDescription?.visibility = View.GONE
                        progress?.visibility = View.GONE
                    }
                    DescriptionResult.Loading -> progress?.visibility = View.VISIBLE
                }
            })
        }

        val productId = intent?.extras?.getString(PRODUCT_ID).orEmpty()
        productDetailsViewModel.getProduct(productId)
    }
}
