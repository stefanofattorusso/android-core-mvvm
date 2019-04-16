package com.stefattorusso.coremvvm.ui.detail.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.DetailFragmentBinding
import com.stefattorusso.domain.Image

class DetailFragment : BaseFragment<DetailFragment.FragmentCallback, DetailViewModel, DetailFragmentBinding>() {

    interface FragmentCallback : BaseFragmentCallback {
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

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
        val image = arguments?.get(Image::class.java.simpleName) as? Image
        image?.let { mViewModel.setImageItem(it) }
//        image_container.setOnTouchListener(SwipeImageTouchListener(parent_view) {
//            mCallback.onAnimationEnd()
//        })
    }

    private fun observeData() {
        mViewModel.getImageLoaded().observe(viewLifecycleOwner, Observer {
            if (it) startPostponedEnterTransition()
        })
    }
}