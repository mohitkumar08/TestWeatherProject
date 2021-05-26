package com.weatherinfo.di

import com.base.BaseComponent
import com.example.vmcore.VMFactoryModule
import com.weatherinfo.di.module.RepositoryModule
import com.weatherinfo.di.module.WeatherServiceModule
import com.weatherinfo.di.scope.WeatherInfoScope
import com.weatherinfo.network.repositiories.WeatherRepository
import com.weatherinfo.weatherdetail.WeatherDetailActivity
import dagger.Component


@WeatherInfoScope
@Component(
    modules = [WeatherServiceModule::class, RepositoryModule::class, VMModule::class,],
    dependencies = [BaseComponent::class]
)
interface WeatherInfoComponent {

    @Component.Builder
    interface Builder {
        fun dependBaseComponent(baseComponent: BaseComponent): Builder
        fun build(): WeatherInfoComponent
    }

    fun inject(mainActivity: WeatherDetailActivity)

    fun getWeatherRepository(): WeatherRepository



}