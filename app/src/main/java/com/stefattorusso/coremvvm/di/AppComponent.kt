package com.stefattorusso.coremvvm.di

import com.stefattorusso.coremvvm.MVVMApplication
import com.stefattorusso.coremvvm.di.annotation.ApplicationScope
import com.stefattorusso.coremvvm.di.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        FragmentModules::class,
        NetworkServiceModule::class,
        ViewModelModule::class
    )
)
interface AppComponent : AndroidInjector<MVVMApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MVVMApplication>()
}