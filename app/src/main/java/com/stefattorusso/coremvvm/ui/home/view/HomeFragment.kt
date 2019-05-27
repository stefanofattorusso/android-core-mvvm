package com.stefattorusso.coremvvm.ui.home.view

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.stefattorusso.components.TutorialOverlayComponentView
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.base.adapter.BaseListAdapter
import com.stefattorusso.coremvvm.data.models.MenuModel
import com.stefattorusso.coremvvm.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : BaseFragment<HomeFragment.FragmentCallback, HomeViewModel, HomeFragmentBinding>() {

    interface FragmentCallback : BaseFragmentCallback {
        fun onMenuItemClicked(type: String)
        fun showTutorial(steps: List<TutorialOverlayComponentView.TutorialStep>)
    }

    override val mViewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        container_view.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    setUpTutorialSteps()
                    container_view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        )
    }

    private fun setUpViews() {
        mViewDataBinding?.viewModel = mViewModel
        recycler_view.run {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = HomeAdapter(mViewModel)
        }
    }

    private fun observeData() {
        mViewModel.getSelectedItem().observe(viewLifecycleOwner, Observer {
            mCallback.onMenuItemClicked(it.type)
        })
    }

    private fun setUpTutorialSteps() {


        val steps = mutableListOf<TutorialOverlayComponentView.TutorialStep>()
        val locationArray = IntArray(2)

        fab_view.getLocationOnScreen(locationArray)
        val fabStep = TutorialOverlayComponentView.TutorialStep(
            locationArray[0].toFloat() + fab_view.width / 2,
            locationArray[1].toFloat() + fab_view.height / 2,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
        )
        steps.add(fabStep)
        icon1.getLocationOnScreen(locationArray)
        val icon1Step = TutorialOverlayComponentView.TutorialStep(
            locationArray[0].toFloat() + icon1.width / 2,
            locationArray[1].toFloat() + icon1.height / 2,
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"
        )
        steps.add(icon1Step)
        icon2.getLocationOnScreen(locationArray)
        val icon2Step = TutorialOverlayComponentView.TutorialStep(
            locationArray[0].toFloat() + icon2.width / 2,
            locationArray[1].toFloat() + icon2.height / 2,
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur"
        )
        steps.add(icon2Step)
        icon3.getLocationOnScreen(locationArray)
        val icon3Step = TutorialOverlayComponentView.TutorialStep(
            locationArray[0].toFloat() + icon3.width / 2,
            locationArray[1].toFloat() + icon3.height / 2,
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
        )
        steps.add(icon3Step)
        mCallback.showTutorial(steps)
    }

    inner class HomeAdapter(viewModel: HomeViewModel) : BaseListAdapter<MenuModel, HomeViewModel>(viewModel) {
        override fun getLayoutIdForPosition(position: Int): Int = R.layout.row_home_view
    }
}
