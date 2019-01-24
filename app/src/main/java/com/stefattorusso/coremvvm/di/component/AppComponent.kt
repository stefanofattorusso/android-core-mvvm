package com.stefattorusso.coremvvm.di.component

import android.app.Application
import com.stefattorusso.coremvvm.MVVMApplication
import com.stefattorusso.coremvvm.di.modules.*
import com.stefattorusso.coremvvm.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBuilderModule::class,
        NetworkServiceModule::class,
        ViewModelModule::class
    )
)
interface AppComponent : AndroidInjector<MVVMApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: MVVMApplication)
}