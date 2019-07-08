package com.stefattorusso.coremvvm.ui.grid

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.grid.view.GridFragment
import com.stefattorusso.domain.Image
import kotlinx.android.synthetic.main.grid_activity.*

class GridActivity : BaseActivity<GridFragment>(), GridFragment.FragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(): View = content

    override fun onCreateFragment(): GridFragment = GridFragment().newInstance(intent.extras)

    // Fragment Callbacks

    override fun onShowDetail(view: ImageView, image: Image) {
        mNavigationHelper.launchDetailWithTransactionAnimation(view, image)
    }
}
