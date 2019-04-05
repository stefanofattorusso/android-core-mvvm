package com.stefattorusso.commons.lifecyclehelpers.autoinflate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class AutoInflateHelperImpl(
    private val activity: AppCompatActivity,
    private val callback: AutoInflateHelperCallback
) : LifecycleObserver, AutoInflateHelper {

    private var mLayoutId: Int = 0
    private var mInflateLayout: View? = null
    private var mSavedInstanceState: Bundle? = null
    private val layoutName: String
        get() {
            return activity.javaClass.simpleName
                .decapitalize()
                .split("(?=\\p{Upper})".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()
        }

    init {
        activity.lifecycle.addObserver(this)
    }

    override fun setSavedInstanceState(savedInstanceState: Bundle?) {
        mSavedInstanceState = savedInstanceState
    }

    override fun setLayoutId(layoutId: Int) {
        mLayoutId = layoutId
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        onCreateView(activity.layoutInflater)?.let {
            mInflateLayout = it
            activity.setContentView(it)
            onContentViewCreated(it, mSavedInstanceState)
        }
    }

    private fun onCreateView(layoutInflater: LayoutInflater): View? = when {
        mLayoutId != 0 -> layoutInflater.inflate(mLayoutId, null)
        else -> getLayoutId(layoutName).takeIf { it != 0 }?.let {
            layoutInflater.inflate(it, null)
        }
    }

    private fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        callback.onContentViewCreated(view, savedInstanceState)
    }

    private fun getLayoutId(name: String): Int {
        return name.takeIf { it.isNotBlank() }?.let {
            activity.resources.getIdentifier(
                name.replace("R.layout.", ""),
                "layout",
                activity.packageName
            )
        } ?: 0
    }
}