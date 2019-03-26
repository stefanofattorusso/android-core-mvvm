package com.stefattorusso.coremvvm.ui.detail.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.stefattorusso.commons.lifecyclehelpers.SwipeImageTouchListener
import com.stefattorusso.commons.loadUrl
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.DetailFragmentBinding
import com.stefattorusso.domain.Image
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : BaseFragment<DetailFragment.FragmentCallback, DetailViewModel, DetailFragmentBinding>() {

    companion object {
        fun newInstance(bundle: Bundle?) = DetailFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    interface FragmentCallback : BaseFragment.BaseFragmentCallback{
        fun onAnimationEnd()
    }

    override val mViewModelClass: Class<DetailViewModel>
        get() = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeData()
    }

    private fun observeData() {
        mViewModel.selectedItemModel.observe(this, Observer {
            mViewDataBinding?.isLoading = false
            mViewDataBinding?.imageModel = it
            image_view.loadUrl(it.imageUrl) {
                startPostponedEnterTransition()
            }
        })
    }

    private fun setupViews() {
        mViewDataBinding?.isLoading = true
        val image = arguments?.get(Image::class.java.simpleName) as? Image
        if (image != null) {
            mViewModel.setImageItem(image)
        }
        image_container.setOnTouchListener(SwipeImageTouchListener(parent_view){
            mCallback.onAnimationEnd()
        })
    }
}