package com.stefattorusso.coremvvm.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.stefattorusso.commons.loadUrl

object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, gone: Boolean) {
        view.visibility = if (gone) GONE else VISIBLE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        view.loadUrl(imageUrl)
    }

    @JvmStatic
    @BindingAdapter("imageUrl", "callback")
    fun loadImageCallback(view: ImageView, imageUrl: String, callback: () -> Unit) {
        view.loadUrl(imageUrl, callback)
    }
}