package com.stefattorusso.coremvvm.di.modules

import com.stefattorusso.data.repository.ImageRepositoryImpl
import com.stefattorusso.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun imageRepository(repository: ImageRepositoryImpl): ImageRepository
}