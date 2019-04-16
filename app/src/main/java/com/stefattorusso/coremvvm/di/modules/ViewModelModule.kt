package com.stefattorusso.coremvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stefattorusso.coremvvm.base.mvvm.ViewModelFactory
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.di.scope.ViewModelKey
import com.stefattorusso.coremvvm.ui.camera.view.CameraViewModel
import com.stefattorusso.coremvvm.ui.detail.view.DetailViewModel
import com.stefattorusso.coremvvm.ui.grid.view.GridViewModel
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel
import com.stefattorusso.coremvvm.ui.location.view.LocationViewModel
import com.stefattorusso.coremvvm.ui.login.view.LoginViewModel
import com.stefattorusso.coremvvm.ui.splash.view.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ActivityScope
    internal abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(GridViewModel::class)
    internal abstract fun mainViewModel(viewModel: GridViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun detailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(CameraViewModel::class)
    internal abstract fun cameraViewModel(viewModel: CameraViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(LocationViewModel::class)
    internal abstract fun locationViewModel(viewModel: LocationViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

}