package com.weatherinfo.di.module

import androidx.lifecycle.ViewModel
import com.example.vmcore.ViewModelKey
import com.weatherinfo.weatherdetail.WeatherDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class VMModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherDetailViewModel::class)
    abstract fun weatherDetailViewModel(viewModel: WeatherDetailViewModel): ViewModel

}
