package com.stefattorusso.coremvvm.di.modules

import com.stefattorusso.coremvvm.di.annotation.ActivityScope
import com.stefattorusso.coremvvm.ui.main.MainActivity
import com.stefattorusso.coremvvm.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}