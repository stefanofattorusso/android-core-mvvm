package com.stefattorusso.coremvvm.ui.grid.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.base.adapter.BaseListAdapter
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.coremvvm.databinding.GridFragmentBinding
import com.stefattorusso.coremvvm.utils.GridSpacesItemDecoration
import com.stefattorusso.domain.Image
import kotlinx.android.synthetic.main.grid_fragment.*


class GridFragment : BaseFragment<GridFragment.FragmentCallback, GridViewModel, GridFragmentBinding>() {

    interface FragmentCallback : BaseFragmentCallback {
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
        mAdapter = GridAdapter(mViewModel, GridDiffCallback())
        recycler_view.run {
            setHasFixedSize(true)
            addItemDecoration(
                GridSpacesItemDecoration(
                    context.resources.getDimensionPixelSize(R.dimen.grid_spacing),
                    2,
                    true,
                    0
                )
            )
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
            adapter = mAdapter
        }
    }

    private fun observeData() {
        mViewModel.getSelectedItem().observe(viewLifecycleOwner, Observer {
            mCallback.onShowDetail(it.first, it.second)
        })
    }

    private class GridDiffCallback : DiffUtil.ItemCallback<ImageModel>() {
        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class GridAdapter(
        viewModel: GridViewModel,
        diffCallback: DiffUtil.ItemCallback<ImageModel>
    ) : BaseListAdapter<ImageModel, GridViewModel>(viewModel, diffCallback) {
        override fun getLayoutIdForPosition(position: Int): Int = R.layout.row_image_view
    }
}
