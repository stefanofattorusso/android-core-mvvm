package com.stefattorusso.coremvvm.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, gone: Boolean) {
        view.visibility = if (gone) GONE else VISIBLE
    }
}