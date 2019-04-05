package com.stefattorusso.commons

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
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
import timber.log.Timber

// Images

fun ImageView.loadUrl(url: String?) {
    if (url == null) return
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions().override(200, 200))
        .into(this)
}

fun ImageView.loadUrl(url: String?, w: Int, h: Int) {
    if (url == null) return
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions().override(w, h))
        .into(this)
}

fun ImageView.loadUrl(url: String?, callback: () -> Unit) {
    if (url == null) return
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

fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

// Fragment

fun <TFragment : Fragment> TFragment.newInstance(bundle: Bundle?): TFragment {
    return this.apply { arguments = bundle ?: Bundle() }
}


// Context

fun Context.getPackageName(): String {
    var packageName = ""
    try {
        val pInfo = packageManager.getPackageInfo(packageName, 0)
        packageName = pInfo.packageName
    } catch (e: PackageManager.NameNotFoundException) {
        Timber.e(e)
    }

    return packageName
}