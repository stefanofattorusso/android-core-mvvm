package com.stefattorusso.coremvvm.ui.login

import android.app.Activity
import com.stefattorusso.coremvvm.base.BaseActivityModule
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.di.scope.FragmentScope
import com.stefattorusso.coremvvm.ui.login.view.LoginFragment
import com.stefattorusso.coremvvm.ui.login.view.LoginFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = arrayOf(
        BaseActivityModule::class
    )
)
abstract class LoginActivityModule {

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
    internal abstract fun activity(activity: LoginActivity): Activity

    /**
     * The main activity listens to the events in the [LoginFragment].
     *
     * @param activity the activity
     * @return the main fragment mCallback
     */
    @Binds
    @ActivityScope
    internal abstract fun fragmentCallback(activity: LoginActivity): LoginFragment.FragmentCallback


    // =============================================================================================

    /**
     * Provides the injector for the [LoginFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(LoginFragmentModule::class))
    abstract fun fragmentInjector(): LoginFragment
}