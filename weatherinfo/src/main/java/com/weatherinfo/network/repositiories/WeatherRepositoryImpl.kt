package com.weatherinfo.network.repositiories

import com.weatherinfo.di.scope.WeatherInfoScope
import com.weatherinfo.network.response.current_weather.CurrentWeatherResponse
import com.weatherinfo.network.service.WeatherService
import io.reactivex.Single
import javax.inject.Inject

@WeatherInfoScope
class WeatherRepositoryImpl @Inject constructor() : WeatherRepository {

    @Inject
    lateinit var weatherService: WeatherService

    private val TAG="Weather"

    override fun getCurrentWeather(cityName: String): Single<CurrentWeatherResponse> {
        return weatherService.getCurrentWeather(cityName)
    }
}