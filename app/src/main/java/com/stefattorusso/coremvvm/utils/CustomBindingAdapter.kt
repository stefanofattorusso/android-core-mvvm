package com.stefattorusso.coremvvm.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stefattorusso.commons.loadUrl
import com.stefattorusso.coremvvm.base.adapter.BaseListAdapter

object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, gone: Boolean) {
        view.visibility = if (gone) GONE else VISIBLE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String?) {
        view.loadUrl(imageUrl)
    }

    @JvmStatic
    @BindingAdapter("imageUrlCallback", "callback")
    fun loadImageCallback(view: ImageView, imageUrl: String?, callback: () -> Unit) {
        view.loadUrl(imageUrl, callback)
    }

    @JvmStatic
    @BindingAdapter("setItems")
    fun <T, VM> setAdapterItems(view: RecyclerView, items: List<T>?){
        (view.adapter as BaseListAdapter<T, VM>).setItems(items)
    }
}