package com.stefattorusso.coremvvm.ui.detail

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.fullscreen.FullScreenActivityLifecycle
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseFragmentActivity
import com.stefattorusso.coremvvm.ui.detail.view.DetailFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class DetailActivity : BaseFragmentActivity<DetailFragment>(), DetailFragment.FragmentCallback {

    @Inject
    lateinit var mFullScreenActivityLifecycle: FullScreenActivityLifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
    }

    override fun onBackPressed() {
        if (getFragment()?.onBackPressed() == true) {
            super.onBackPressed()
        }
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(): View = content

    override fun onCreateFragment(): DetailFragment = DetailFragment().newInstance(intent.extras)

    override fun onAnimationEnd() {
        finishAfterTransition()
    }
}
