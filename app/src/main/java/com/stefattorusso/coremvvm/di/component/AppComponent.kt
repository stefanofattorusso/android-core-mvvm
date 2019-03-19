package com.stefattorusso.coremvvm.di.component

import com.stefattorusso.coremvvm.base.BaseApplication
import com.stefattorusso.coremvvm.base.BaseApplicationModule
import com.stefattorusso.coremvvm.di.modules.ActivityBuilderModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        BaseApplicationModule::class,
        ActivityBuilderModule::class
    )
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>()
}