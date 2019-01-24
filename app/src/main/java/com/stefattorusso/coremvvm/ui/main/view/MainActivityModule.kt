package com.stefattorusso.coremvvm.ui.main.view

import com.stefattorusso.coremvvm.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    // =============================================================================================

    /**
     * Provides the injector for the [SplashFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    abstract fun fragmentInjector(): MainFragment
}