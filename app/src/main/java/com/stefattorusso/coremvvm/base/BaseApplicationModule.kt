package com.stefattorusso.coremvvm.base

import android.app.Application
import android.content.Context
import com.stefattorusso.coremvvm.di.modules.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = arrayOf(
        CacheModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        LifecycleHelpersModule::class
    )
)
abstract class BaseApplicationModule {

    /*
     * Singleton annotation isn't necessary since Application instance is unique but is here for
     * convention. In general, providing Activity, Fragment, BroadcastReceiver, etc does not require
     * them to be scoped since they are the components being injected and their instance is unique.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     */
    @Binds
    @Singleton
    internal abstract fun application(application: BaseApplication): Application

    @Binds
    @Singleton
    internal abstract fun context(application: Application): Context
}