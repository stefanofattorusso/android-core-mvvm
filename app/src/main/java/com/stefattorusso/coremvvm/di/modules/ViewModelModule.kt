package com.stefattorusso.coremvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stefattorusso.coremvvm.base.mvvm.ViewModelFactory
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.di.scope.ViewModelKey
import com.stefattorusso.coremvvm.ui.detail.view.DetailViewModel
import com.stefattorusso.coremvvm.ui.main.view.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ActivityScope
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(DetailViewModel::class)
    abstract fun detailViewModel(viewModel: DetailViewModel): ViewModel
}