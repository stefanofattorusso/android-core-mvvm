package com.stefattorusso.coremvvm.ui.camera.view

import androidx.fragment.app.Fragment
import com.stefattorusso.coremvvm.base.BaseFragmentModule
import com.stefattorusso.coremvvm.base.mvvm.BaseVMFragmentModule
import com.stefattorusso.coremvvm.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module(
    includes = arrayOf(
        BaseVMFragmentModule::class
    )
)
abstract class CameraFragmentModule {
    /**
     * As per the contract specified in [BaseFragmentModule]; "This must be included in all
     * fragment modules, which must provide a concrete implementation of [Fragment].
     *
     * @param fragment the SampleMapFragment
     * @return the fragment
     */
    @Binds
    @FragmentScope
    internal abstract fun fragment(fragment: CameraFragment): Fragment
}