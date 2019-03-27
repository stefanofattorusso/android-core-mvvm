package com.stefattorusso.coremvvm.di.modules

import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.ui.detail.DetailActivity
import com.stefattorusso.coremvvm.ui.detail.DetailActivityModule
import com.stefattorusso.coremvvm.ui.home.HomeActivity
import com.stefattorusso.coremvvm.ui.home.HomeActivityModule
import com.stefattorusso.coremvvm.ui.main.MainActivity
import com.stefattorusso.coremvvm.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HomeActivityModule::class))
    abstract fun homeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(DetailActivityModule::class))
    abstract fun detailActivity(): DetailActivity
}