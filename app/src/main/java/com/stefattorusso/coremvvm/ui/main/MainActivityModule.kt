package com.stefattorusso.coremvvm.ui.main

import android.app.Activity
import com.stefattorusso.coremvvm.base.BaseActivityModule
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.di.scope.FragmentScope
import com.stefattorusso.coremvvm.ui.main.view.MainFragment
import com.stefattorusso.coremvvm.ui.main.view.MainFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = arrayOf(
        BaseActivityModule::class
    )
)
abstract class MainActivityModule {

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
    internal abstract fun activity(activity: MainActivity): Activity

    /**
     * The main activity listens to the events in the [MainFragment].
     *
     * @param activity the activity
     * @return the main fragment mCallback
     */
    @Binds
    @ActivityScope
    internal abstract fun fragmentCallback(activity: MainActivity): MainFragment.FragmentCallback

    // =============================================================================================

    /**
     * Provides the injector for the [MainFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    abstract fun fragmentInjector(): MainFragment
}