package com.stefattorusso.commons

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions().override(200, 200))
        .into(this)
}

fun ImageView.loadUrl(url: String, w: Int, h: Int) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions().override(w, h))
        .into(this)
}

fun ImageView.loadUrl(url: String, callback: () -> Unit) {
    Glide.with(context)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                callback()
                return false
            }

        })
        .into(this)
}


// Activity

fun <TFragment : Fragment> Activity.loadFragment(
    fragmentManager: FragmentManager,
    containerId: Int,
    fragment: TFragment
) {
    fragmentManager
        .beginTransaction()
        .replace(containerId, fragment, fragment.javaClass.simpleName)
        .commit()
}

fun <TFragment : Fragment> Activity.getFragment(fragmentManager: FragmentManager, containerId: Int): TFragment? =
    fragmentManager.findFragmentById(containerId) as TFragment