package com.stefattorusso.coremvvm.ui.grid

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.grid.view.GridFragment
import com.stefattorusso.domain.Image
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class GridActivity : BaseActivity(), InjectFragmentHelperCallback<GridFragment>, GridFragment.FragmentCallback {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper

    private var mGridFragment: GridFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): GridFragment = GridFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: GridFragment) {
        mGridFragment = fragment
    }

    // Fragment Callbacks

    override fun onShowDetail(view: ImageView, image: Image) {
        mNavigationHelper.launchDetailWithTransactionAnimation(view, image)
    }
}
