package com.stefattorusso.coremvvm.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stefattorusso.commons.BaseRecyclerAdapterCallback
import com.stefattorusso.commons.loadUrl

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
    fun <T> setAdapterItems(view: RecyclerView, items: List<T>?){
        (view.adapter as BaseRecyclerAdapterCallback<T>).setItems(items)
    }

    @JvmStatic
    @BindingAdapter("setDataPair")
    fun <T> setAdapterItems(view: RecyclerView, data: Pair<List<T>, DiffUtil.DiffResult>?){
        (view.adapter as BaseRecyclerAdapterCallback<T>).setItems(data)
    }
}