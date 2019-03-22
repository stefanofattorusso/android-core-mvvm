package com.stefattorusso.coremvvm.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.MainFragmentBinding
import com.stefattorusso.coremvvm.ui.main.adapter.MainAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment<MainViewModel, MainFragmentBinding>() {

    private lateinit var mAdapter: MainAdapter

    override val mViewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSearchResults()
        setUpViews()

        mViewDataBinding?.isLoading = true
        mViewModel.loadData()
    }

    private fun observeSearchResults() {
        mViewModel.imageModelList.observe(this, Observer {
            mViewDataBinding?.isLoading = false
            mAdapter.setItems(it.first, it.second)
        })
    }

    private fun setUpViews() {
        mAdapter = MainAdapter()
        recycler_view.run {
            setHasFixedSize(true)
//            addItemDecoration(SpacesItemDecoration(R.dimen.grid_spacing, 2, true, 0))
            layoutManager = GridLayoutManager(context, 2)
            adapter = mAdapter
        }
    }
}
