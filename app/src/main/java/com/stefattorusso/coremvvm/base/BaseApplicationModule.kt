package com.stefattorusso.coremvvm.base

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.di.modules.*
import com.stefattorusso.data.coroutines.CoroutineDispatchers
import com.stefattorusso.data.coroutines.CoroutineDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = arrayOf(
        CacheModule::class,
        ActivityBuilderModule::class,
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

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun modelMappers(context: Context): ImageModelMapper = ImageModelMapper(context)

        @JvmStatic
        @Provides
        @Singleton
        internal fun firebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        @JvmStatic
        @Provides
        @Singleton
        internal fun coroutineDispatchers(): CoroutineDispatchers = CoroutineDispatchersImpl()
    }

}