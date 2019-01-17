package com.stefattorusso.coremvvm.di.modules

import android.app.Application
import android.content.Context
import com.stefattorusso.coremvvm.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: Application): Context = application
}