package com.stefattorusso.commons.lifecyclehelpers.autoinflateview

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class AutoInflateViewHelperImpl(
    private val fragment: Fragment,
    private val callback: AutoInflateViewHelperCallback
) : LifecycleObserver, AutoInflateViewHelper {

    private var mLayoutId: Int = 0
    private var mInflateLayout: View? = null
    private val layoutName: String
        get() {
            return fragment.javaClass.simpleName
                .decapitalize()
                .split("(?=\\p{Upper})".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()
        }

    init {
        fragment.activity?.lifecycle?.addObserver(this)
    }

    override fun setLayoutId(layoutId: Int) {
        mLayoutId = layoutId
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        onCreateView(fragment.layoutInflater)?.let {
            callback.onViewInflated(it)
        }
    }

    private fun onCreateView(layoutInflater: LayoutInflater): View? = when {
        mLayoutId != 0 -> layoutInflater.inflate(mLayoutId, null)
        else -> getLayoutId(layoutName).takeIf { it != 0 }?.let {
            layoutInflater.inflate(it, null)
        }
    }

    private fun getLayoutId(name: String): Int {
        return name.takeIf { it.isNotBlank() }?.let {
            fragment.resources.getIdentifier(
                name.replace("R.layout.", ""),
                "layout",
                fragment.activity?.packageName
            )
        } ?: 0
    }

}