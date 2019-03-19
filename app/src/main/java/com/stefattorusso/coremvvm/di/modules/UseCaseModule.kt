package com.stefattorusso.coremvvm.di.modules

import com.stefattorusso.domain.interactor.GetImageListUseCase
import com.stefattorusso.domain.interactor.impl.GetImageListUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun getImageListUseCase(useCase: GetImageListUseCaseImpl): GetImageListUseCase
}