package com.stefattorusso.coremvvm.base

import androidx.fragment.app.Fragment
import com.stefattorusso.commons.lifecyclehelpers.autoinflateview.AutoInflateViewHelper
import com.stefattorusso.commons.lifecyclehelpers.autoinflateview.AutoInflateViewHelperCallback
import com.stefattorusso.commons.lifecyclehelpers.autoinflateview.AutoInflateViewHelperImpl
import com.stefattorusso.coremvvm.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
abstract class BaseFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentScope
        internal fun autoInflateViewHelper(fragment: Fragment): AutoInflateViewHelper =
            AutoInflateViewHelperImpl(fragment, fragment as AutoInflateViewHelperCallback)
    }
}