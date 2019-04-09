package com.stefattorusso.coremvvm.ui.login.view

import androidx.fragment.app.Fragment
import com.stefattorusso.coremvvm.base.BaseFragmentModule
import com.stefattorusso.coremvvm.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module(
    includes = arrayOf(
        BaseFragmentModule::class
    )
)
abstract class LoginFragmentModule {
    /**
     * As per the contract specified in [BaseFragmentModule]; "This must be included in all
     * fragment modules, which must provide a concrete implementation of [Fragment].
     *
     * @param fragment the SampleMapFragment
     * @return the fragment
     */
    @Binds
    @FragmentScope
    internal abstract fun fragment(fragment: LoginFragment): Fragment
}