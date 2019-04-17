package com.stefattorusso.coremvvm.ui.detail.view

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.stefattorusso.commons.SwipeImageTouchListener
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.DetailFragmentBinding
import com.stefattorusso.domain.Image
import kotlinx.android.synthetic.main.detail_bottom_sheet.*
import kotlinx.android.synthetic.main.detail_fragment.*


class DetailFragment : BaseFragment<DetailFragment.FragmentCallback, DetailViewModel, DetailFragmentBinding>() {

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<View>

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

    fun onBackPressed(): Boolean{
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            return false
        }
        return true
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
        val image = arguments?.get(Image::class.java.simpleName) as? Image
        image?.let { mViewModel.setImageItem(it) }
        image_container.setOnTouchListener(SwipeImageTouchListener(
            parent_view,
            {
                bottom_sheet_container.visibility = GONE
            },
            {
                bottom_sheet_container.visibility = VISIBLE
            })
        {
            mCallback.onAnimationEnd()
        })
        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_container)
        comment_list_button.setOnClickListener { toggleBottomSheet() }
        bottom_sheet_container.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    true
                }
                MotionEvent.ACTION_UP -> {
                   true
                }
                else -> true
            }
        }
    }

    private fun toggleBottomSheet() {
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun observeData() {
        mViewModel.getImageLoaded().observe(viewLifecycleOwner, Observer {
            if (it) startPostponedEnterTransition()
        })
    }
}