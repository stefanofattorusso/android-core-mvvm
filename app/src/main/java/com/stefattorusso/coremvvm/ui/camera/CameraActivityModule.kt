package com.stefattorusso.coremvvm.ui.camera

import android.app.Activity
import com.stefattorusso.coremvvm.base.BaseActivityModule
import com.stefattorusso.coremvvm.base.BaseFragmentActivityModule
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.di.scope.FragmentScope
import com.stefattorusso.coremvvm.ui.camera.view.CameraFragment
import com.stefattorusso.coremvvm.ui.camera.view.CameraFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(BaseFragmentActivityModule::class))
abstract class CameraActivityModule {

    /**
     * As per the contract specified in [BaseActivityModule]; "This must be included in all
     * activity modules, which must provide a concrete implementation of [Activity]."
     *
     *
     * This provides the activity required to inject the dependencies into the
     * [BaseActivity].
     *
     * @param activity the ProjectListActivity
     * @return the activity
     */
    @Binds
    @ActivityScope
    internal abstract fun activity(activity: CameraActivity): Activity

    /**
     * The main activity listens to the events in the [CameraFragment].
     *
     * @param activity the activity
     * @return the main fragment mCallback
     */
    @Binds
    @ActivityScope
    internal abstract fun fragmentCallback(activity: CameraActivity): CameraFragment.FragmentCallback


    // =============================================================================================

    /**
     * Provides the injector for the [CameraFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(CameraFragmentModule::class))
    abstract fun fragmentInjector(): CameraFragment
}