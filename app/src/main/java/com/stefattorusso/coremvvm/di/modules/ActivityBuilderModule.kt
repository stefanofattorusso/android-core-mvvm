package com.stefattorusso.coremvvm.di.modules

import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.ui.camera.CameraActivity
import com.stefattorusso.coremvvm.ui.camera.CameraActivityModule
import com.stefattorusso.coremvvm.ui.detail.DetailActivity
import com.stefattorusso.coremvvm.ui.detail.DetailActivityModule
import com.stefattorusso.coremvvm.ui.grid.GridActivity
import com.stefattorusso.coremvvm.ui.grid.GridActivityModule
import com.stefattorusso.coremvvm.ui.home.HomeActivity
import com.stefattorusso.coremvvm.ui.home.HomeActivityModule
import com.stefattorusso.coremvvm.ui.location.LocationActivity
import com.stefattorusso.coremvvm.ui.location.LocationActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HomeActivityModule::class))
    abstract fun homeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(GridActivityModule::class))
    abstract fun mainActivity(): GridActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(DetailActivityModule::class))
    abstract fun detailActivity(): DetailActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CameraActivityModule::class))
    abstract fun cameraActivity(): CameraActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(LocationActivityModule::class))
    abstract fun locationActivity(): LocationActivity
}