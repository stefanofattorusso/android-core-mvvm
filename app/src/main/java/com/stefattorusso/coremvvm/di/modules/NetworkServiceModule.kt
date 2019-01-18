package com.stefattorusso.coremvvm.di.modules

import com.stefattorusso.coremvvm.data.services.ApiService
import com.stefattorusso.coremvvm.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = arrayOf(NetworkModule::class))
class NetworkServiceModule {

    @Provides
    @ApplicationScope
    fun provideGithubService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}