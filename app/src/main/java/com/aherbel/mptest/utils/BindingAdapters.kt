package com.aherbel.mptest.utils

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.aherbel.mptest.R

class BindingAdapters {

    companion object {

        private const val LOG_TAG = "BindingAdapters"

        @JvmStatic
        @BindingAdapter("app:glideImage")
        fun setGlideImage(imageView: AppCompatImageView, url: String?) {
            url?.let {
                try {
                    GlideApp.with(imageView)
                        .load(url)
                        .error(R.drawable.ic_image_not_available)
                        .into(imageView)
                } catch (e: Exception) {
                    Log.e(LOG_TAG, e.message, e)

                    GlideApp.with(imageView)
                        .load(R.drawable.ic_image_not_available)
                        .into(imageView)
                }
            }
        }
    }
}