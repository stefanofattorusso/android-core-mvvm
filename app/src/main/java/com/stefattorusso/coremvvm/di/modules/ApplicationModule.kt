package com.stefattorusso.coremvvm.di.modules

import android.app.Application
import android.content.Context
import com.stefattorusso.coremvvm.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: Application): Context = application
}