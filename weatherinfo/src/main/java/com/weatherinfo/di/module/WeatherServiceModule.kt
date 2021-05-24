package com.weatherinfo.di.module

import com.weatherinfo.network.service.WeatherService
import com.weatherinfo.di.scope.WeatherInfoScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object WeatherServiceModule {

    @WeatherInfoScope
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}