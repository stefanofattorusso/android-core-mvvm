package com.stefattorusso.coremvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stefattorusso.coremvvm.base.mvvm.ViewModelFactory
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.di.scope.ViewModelKey
import com.stefattorusso.coremvvm.ui.main.view.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ActivityScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}