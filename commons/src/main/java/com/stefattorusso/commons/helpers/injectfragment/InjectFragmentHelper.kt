package com.stefattorusso.commons.helpers.injectfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class InjectFragmentHelper<TFragment : Fragment>(
    private val activity: FragmentActivity,
    private val callback: InjectFragmentHelperCallback<TFragment>
) : LifecycleObserver {

    var savedInstanceState: Bundle? = null

    init {
        activity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
       /* var contentFragment: TFragment?
        val contentView = callback.onLoadFragmentContainer(savedInstanceState)
        if (savedInstanceState == null) {
            contentFragment = callback.onCreateFragment()
            activity.loadFragment(
                activity.supportFragmentManager,
                contentView.id,
                mContentFragment
            )
        } else {
            contentFragment =
                activity.getFragment(activity.supportFragmentManager, contentView.id)
        }
        callback.onFragmentLoaded(contentFragment)*/
    }
}