package com.stefattorusso.commons.lifecyclehelpers.injectfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.stefattorusso.commons.getFragment
import com.stefattorusso.commons.loadFragment

class InjectFragmentHelperImpl<TFragment : Fragment>(
    private val activity: AppCompatActivity,
    private val callback: InjectFragmentHelperCallback<TFragment>
) : LifecycleObserver, InjectFragmentHelper {

    private var mContentFragment: TFragment? = null

    private var savedInstanceState: Bundle? = null

    init {
        activity.lifecycle.addObserver(this)
    }

    override fun setSavedInstanceState(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        val contentView = callback.onLoadFragmentContainer(savedInstanceState)
        if (savedInstanceState == null) {
            mContentFragment = callback.onCreateFragment()
            mContentFragment?.let { activity.loadFragment(activity.supportFragmentManager, contentView.id, it) }
        } else {
            mContentFragment = activity.getFragment(activity.supportFragmentManager, contentView.id)
        }
        mContentFragment?.let { callback.onFragmentLoaded(it) }
    }
}