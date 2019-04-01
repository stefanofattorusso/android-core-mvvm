package com.stefattorusso.coremvvm.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.HomeFragmentBinding
import com.stefattorusso.coremvvm.ui.home.adapter.HomeAdapter
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : BaseFragment<HomeFragment.FragmentCallback, HomeViewModel, HomeFragmentBinding>() {

    interface FragmentCallback : BaseFragment.BaseFragmentCallback {
        fun onMenuItemClicked(type: String)
    }

    private lateinit var mAdapter: HomeAdapter

    override val mViewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setUpViews()
    }

    private fun observeData() {
        mViewModel.selectedItem.observe(this, Observer {
            mCallback.onMenuItemClicked(it.type)
        })
    }

    private fun setUpViews() {
        mViewDataBinding?.viewModel = mViewModel
        mAdapter = HomeAdapter(mViewModel as HomeAdapter.AdapterCallback)
        recycler_view.run {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = mAdapter
        }
    }
}
