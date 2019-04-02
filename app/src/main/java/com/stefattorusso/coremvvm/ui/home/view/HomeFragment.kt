package com.stefattorusso.coremvvm.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.base.BaseListAdapter
import com.stefattorusso.coremvvm.data.models.MenuModel
import com.stefattorusso.coremvvm.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : BaseFragment<HomeFragment.FragmentCallback, HomeViewModel, HomeFragmentBinding>() {

    interface FragmentCallback : BaseFragment.BaseFragmentCallback {
        fun onMenuItemClicked(type: String)
    }

    override val mViewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    private fun setUpViews() {
        mViewDataBinding?.viewModel = mViewModel
        recycler_view.run {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = HomeAdapter()
        }
    }

    private fun observeData() {
        mViewModel.getSelectedItem().observe(viewLifecycleOwner, Observer {
            mCallback.onMenuItemClicked(it.type)
        })
    }

    inner class HomeAdapter : BaseListAdapter<MenuModel>() {
        override fun getViewHolderLayoutId(): Int = R.layout.row_home_view
    }
}
