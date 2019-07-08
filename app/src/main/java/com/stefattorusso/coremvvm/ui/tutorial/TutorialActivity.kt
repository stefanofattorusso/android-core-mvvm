package com.stefattorusso.coremvvm.ui.tutorial

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.fullscreen.FullScreenActivityLifecycle
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseFragmentActivity
import com.stefattorusso.coremvvm.ui.tutorial.view.TutorialFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class TutorialActivity : BaseFragmentActivity<TutorialFragment>(), TutorialFragment.FragmentCallback{

    @Inject
    lateinit var mFullScreenActivityLifecycle: FullScreenActivityLifecycle

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("TEST", "OK")
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(): View = content

    override fun onCreateFragment(): TutorialFragment = TutorialFragment().newInstance(intent.extras)

}
