package com.base.di.module

import androidx.lifecycle.ViewModelProvider
import com.base.vmfactory.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}



