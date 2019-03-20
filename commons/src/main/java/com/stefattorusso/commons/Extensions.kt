package com.stefattorusso.commons

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
//        .placeholder(R.drawable.icono_gastronosfera)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}