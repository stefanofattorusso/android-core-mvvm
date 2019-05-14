package com.stefattorusso.coremvvm.ui.tutorial.view

import android.os.Bundle
import android.view.View
import com.stefattorusso.components.TutorialOverlayComponentView
import com.stefattorusso.components.TutorialOverlayComponentView.TutorialStep
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.TutorialFragmentBinding
import kotlinx.android.synthetic.main.tutorial_fragment.*

class TutorialFragment :
    BaseFragment<TutorialFragment.FragmentCallback, TutorialViewModel, TutorialFragmentBinding>() {

    private var steps: List<TutorialStep>? = null
    private var stepIndex = 0

    interface FragmentCallback : BaseFragmentCallback

    override val mViewModelClass: Class<TutorialViewModel>
        get() = TutorialViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        steps = arguments?.getParcelableArrayList(TutorialOverlayComponentView.TutorialStep::class.java.simpleName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel

        if (steps != null) {
            tutorial_overlay.setStep(steps!![stepIndex])
            tutorial_text.text = steps!![stepIndex].text

            stepIndex += 1

            next_button.setOnClickListener {
                val step = steps!![stepIndex]
                tutorial_text.text = step.text
                tutorial_overlay.setStep(step)

                if (stepIndex == steps?.size?.minus(1)) {
                    stepIndex = 0
                } else {
                    stepIndex += 1
                }
            }
        }
    }
}