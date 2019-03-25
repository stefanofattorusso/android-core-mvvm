package com.stefattorusso.coremvvm.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.MainFragmentBinding
import com.stefattorusso.coremvvm.ui.main.adapter.MainAdapter
import com.stefattorusso.coremvvm.ui.main.adapter.SpacesItemDecoration
import com.stefattorusso.domain.Image
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : BaseFragment<MainFragment.FragmentCallback, MainViewModel, MainFragmentBinding>() {

    companion object {
        fun newInstance(bundle: Bundle?) = MainFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    interface FragmentCallback : BaseFragment.BaseFragmentCallback {
        fun onShowDetail(view: ImageView, image: Image)
    }

    private lateinit var mAdapter: MainAdapter

    override val mViewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setUpViews()

        mViewDataBinding?.isLoading = true
        mViewModel.loadData()
    }

    private fun observeData() {
        mViewModel.imageModelList.observe(this, Observer {
            mViewDataBinding?.isLoading = false
            mAdapter.setItems(it.first, it.second)
        })
        mViewModel.selectedItem.observe(this, Observer {
            mCallback.onShowDetail(it.first, it.second)
        })
    }

    private fun setUpViews() {
        mAdapter = MainAdapter(mViewModel as MainAdapter.AdapterCallback)
        recycler_view.run {
            setHasFixedSize(true)
            addItemDecoration(
                SpacesItemDecoration(
                    context.resources.getDimensionPixelSize(R.dimen.grid_spacing),
                    2,
                    true,
                    0
                )
            )
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(context, 2)
            adapter = mAdapter
        }
    }
}
