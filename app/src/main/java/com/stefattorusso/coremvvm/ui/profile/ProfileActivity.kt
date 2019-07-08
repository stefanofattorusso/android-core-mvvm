package com.stefattorusso.coremvvm.ui.profile

import android.view.View
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.profile.view.ProfileFragment
import kotlinx.android.synthetic.main.grid_activity.*

class ProfileActivity : BaseActivity<ProfileFragment>(), ProfileFragment.FragmentCallback{

    // Fragment Helper
    override fun onLoadFragmentContainer(): View = content

    override fun onCreateFragment(): ProfileFragment = ProfileFragment().newInstance(intent.extras)
}
