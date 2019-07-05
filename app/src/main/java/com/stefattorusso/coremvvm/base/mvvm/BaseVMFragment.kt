package com.stefattorusso.coremvvm.base.mvvm

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.NoData
import javax.inject.Inject

abstract class BaseVMFragment<TCallback : BaseVMFragment.BaseVMFragmentCallback, VM : BaseViewModel, VDB : ViewDataBinding> :
        BaseFragment<VDB, TCallback>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val viewModelClass: Class<VM>

    lateinit var viewModel: VM

    interface BaseVMFragmentCallback : BaseFragmentCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass).also { it.onAttached() }
    }

    override fun onBindingCreated(binding: VDB) {
        binding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onViewModelAttached(viewLifecycleOwner, viewModel)
        observeViewModel()
    }

    abstract fun onViewModelAttached(owner: LifecycleOwner, viewModel: VM)

    protected open fun showEmptyView() {

    }

    private fun observeViewModel() {
        viewModel.getUiState().observe(viewLifecycleOwner, Observer {
            when (it) {
                Loading -> showProgress()
                HasData -> hideProgress()
                NoData -> {
                    hideProgress()
                    showEmptyView()
                }
            }
        })
        viewModel.getError().observe(viewLifecycleOwner, Observer {
            hideProgress()
            showError(it)
        })
    }
}