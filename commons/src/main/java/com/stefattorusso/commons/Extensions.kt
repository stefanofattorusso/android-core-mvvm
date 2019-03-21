package com.stefattorusso.commons

import android.app.Activity
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
//        .placeholder(R.drawable.icono_gastronosfera)
        .transition(DrawableTransitionOptions.withCrossFade())
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