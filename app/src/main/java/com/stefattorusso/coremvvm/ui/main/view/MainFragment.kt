package com.stefattorusso.coremvvm.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.FragmentMainBinding
import timber.log.Timber

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override val mViewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSearchResults()

        mViewDataBinding?.isLoading = true
        mViewModel.loadData()
    }

    private fun observeSearchResults() {
        mViewModel.imageList.observe(this, Observer {
            mViewDataBinding?.isLoading = false
            Timber.i("repo count received  ${it?.size}")
        })
    }
}
