package com.weatherinfo.di.module

import com.weatherinfo.network.repositiories.WeatherRepository
import com.weatherinfo.network.repositiories.WeatherRepositoryImpl
import com.weatherinfo.network.service.WeatherService
import com.weatherinfo.di.scope.WeatherInfoScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
        @WeatherInfoScope
        @Binds
        abstract fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}