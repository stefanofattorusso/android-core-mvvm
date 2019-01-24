package com.stefattorusso.coremvvm.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.FragmentMainBinding
import com.stefattorusso.coremvvm.ui.main.viewmodel.MainViewModel

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override val mViewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSearchResults()

        //init a query
        mViewDataBinding?.isLoading = true
        mViewModel.searchRepos("android")
    }

    private fun observeSearchResults() {
        mViewModel.repoList.observe(this, Observer {
            mViewDataBinding?.isLoading = false
            Log.i("MVVM", "repo count received  ${it?.size}")
        })
    }
}
