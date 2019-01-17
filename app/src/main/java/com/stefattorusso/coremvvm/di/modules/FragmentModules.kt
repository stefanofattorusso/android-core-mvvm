package com.stefattorusso.coremvvm.di.modules

import com.stefattorusso.coremvvm.di.annotation.FragmentScope
import com.stefattorusso.coremvvm.ui.main.MainFragment
import com.stefattorusso.coremvvm.ui.main.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModules{

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    abstract fun bindMainFragment(): MainFragment
}