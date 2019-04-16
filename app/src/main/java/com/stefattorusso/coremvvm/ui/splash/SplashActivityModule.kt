package com.stefattorusso.coremvvm.ui.splash

import android.app.Activity
import com.stefattorusso.coremvvm.base.BaseActivityModule
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.di.scope.FragmentScope
import com.stefattorusso.coremvvm.ui.splash.view.SplashFragment
import com.stefattorusso.coremvvm.ui.splash.view.SplashFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = arrayOf(
        BaseActivityModule::class
    )
)
abstract class SplashActivityModule {

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
    internal abstract fun activity(activity: SplashActivity): Activity

    /**
     * The main activity listens to the events in the [SplashFragment].
     *
     * @param activity the activity
     * @return the main fragment mCallback
     */
    @Binds
    @ActivityScope
    internal abstract fun fragmentCallback(activity: SplashActivity): SplashFragment.FragmentCallback

    // =============================================================================================

    /**
     * Provides the injector for the [SplashFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(SplashFragmentModule::class))
    abstract fun fragmentInjector(): SplashFragment
}