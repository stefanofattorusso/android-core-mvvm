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
import com.stefattorusso.coremvvm.ui.login.LoginActivity
import com.stefattorusso.coremvvm.ui.login.LoginActivityModule
import com.stefattorusso.coremvvm.ui.profile.ProfileActivity
import com.stefattorusso.coremvvm.ui.profile.ProfileActivityModule
import com.stefattorusso.coremvvm.ui.splash.SplashActivity
import com.stefattorusso.coremvvm.ui.splash.SplashActivityModule
import com.stefattorusso.coremvvm.ui.tutorial.TutorialActivity
import com.stefattorusso.coremvvm.ui.tutorial.TutorialActivityModule
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

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(LoginActivityModule::class))
    abstract fun loginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    abstract fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(ProfileActivityModule::class))
    abstract fun profileActivity(): ProfileActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(TutorialActivityModule::class))
    abstract fun tutorialActivity(): TutorialActivity
}