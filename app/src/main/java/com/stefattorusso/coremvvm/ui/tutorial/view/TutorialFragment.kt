package com.stefattorusso.coremvvm.ui.tutorial.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.stefattorusso.coremvvm.base.mvvm.BaseVMFragment
import com.stefattorusso.coremvvm.databinding.TutorialFragmentBinding
import com.stefattorusso.coremvvm.ui.components.TutorialOverlayComponentView
import kotlinx.android.synthetic.main.tutorial_fragment.*

class TutorialFragment :
    BaseVMFragment<TutorialFragment.FragmentCallback, TutorialViewModel, TutorialFragmentBinding>() {

    private var steps: List<TutorialOverlayComponentView.TutorialStep>? = null
    private var stepIndex = 0

    interface FragmentCallback : BaseVMFragmentCallback

    override val viewModelClass: Class<TutorialViewModel>
        get() = TutorialViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: TutorialViewModel) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        steps = arguments?.getParcelableArrayList(TutorialOverlayComponentView.TutorialStep::class.java.simpleName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        steps?.let { s ->
            tutorial_overlay.setStep(s[stepIndex])
            tutorial_text.text = s[stepIndex].text

            stepIndex += 1

            next_button.setOnClickListener {
                val step = s[stepIndex]
                tutorial_text.text = step.text
                tutorial_overlay.setStep(step)

                if (stepIndex == s.size.minus(1)) {
                    stepIndex = 0
                } else {
                    stepIndex += 1
                }
            }
        }
    }
}