package com.stefattorusso.coremvvm.di.modules

import com.stefattorusso.domain.interactor.GetImageListUseCase
import com.stefattorusso.domain.interactor.HasSessionUseCase
import com.stefattorusso.domain.interactor.LoginUseCase
import com.stefattorusso.domain.interactor.impl.GetImageListUseCaseImpl
import com.stefattorusso.domain.interactor.impl.HasSessionUseCaseImpl
import com.stefattorusso.domain.interactor.impl.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun getImageListUseCase(useCase: GetImageListUseCaseImpl): GetImageListUseCase

    @Binds
    @Singleton
    internal abstract fun hasSessionUseCase(useCase: HasSessionUseCaseImpl): HasSessionUseCase

    @Binds
    @Singleton
    internal abstract fun loginUseCase(useCase: LoginUseCaseImpl): LoginUseCase
}