package com.stefattorusso.coremvvm.ui.tutorial

import android.app.Activity
import com.stefattorusso.coremvvm.base.BaseActivityModule
import com.stefattorusso.coremvvm.base.BaseFragmentActivityModule
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.di.scope.FragmentScope
import com.stefattorusso.coremvvm.ui.tutorial.view.TutorialFragment
import com.stefattorusso.coremvvm.ui.tutorial.view.TutorialFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(BaseFragmentActivityModule::class))
abstract class TutorialActivityModule {

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
    internal abstract fun activity(activity: TutorialActivity): Activity

    /**
     * The main activity listens to the events in the [TutorialFragment].
     *
     * @param activity the activity
     * @return the main fragment mCallback
     */
    @Binds
    @ActivityScope
    internal abstract fun fragmentCallback(activity: TutorialActivity): TutorialFragment.FragmentCallback

    // =============================================================================================

    /**
     * Provides the injector for the [TutorialFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(TutorialFragmentModule::class))
    abstract fun fragmentInjector(): TutorialFragment
}