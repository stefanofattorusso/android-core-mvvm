package com.stefattorusso.coremvvm.base

import android.view.View
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(){
    fun observeLoader(viewModel: BaseViewModel, loaderView: View) {
        viewModel.loader.observe(this, Observer {
            loaderView.visibility = if(it != null && it) View.VISIBLE else View.GONE
        })
    }
}