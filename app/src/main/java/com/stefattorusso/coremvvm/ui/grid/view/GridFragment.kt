package com.stefattorusso.coremvvm.ui.grid.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.GridFragmentBinding
import com.stefattorusso.coremvvm.ui.grid.adapter.GridAdapter
import com.stefattorusso.coremvvm.ui.grid.adapter.SpacesItemDecoration
import com.stefattorusso.domain.Image
import kotlinx.android.synthetic.main.grid_fragment.*


class GridFragment : BaseFragment<GridFragment.FragmentCallback, GridViewModel, GridFragmentBinding>() {

    interface FragmentCallback : BaseFragment.BaseFragmentCallback {
        fun onShowDetail(view: ImageView, image: Image)
    }

    private lateinit var mAdapter: GridAdapter

    override val mViewModelClass: Class<GridViewModel>
        get() = GridViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    private fun setUpViews() {
        mViewDataBinding?.viewModel = mViewModel
        mAdapter = GridAdapter(mViewModel as GridAdapter.AdapterCallback)
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

    private fun observeData() {
        mViewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            mCallback.onShowDetail(it.first, it.second)
        })
    }
}
