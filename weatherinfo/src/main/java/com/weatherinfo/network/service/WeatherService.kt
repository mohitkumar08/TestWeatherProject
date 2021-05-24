package com.weatherinfo.network.service

import com.weatherinfo.di.scope.WeatherInfoScope
import com.weatherinfo.network.response.current_weather.CurrentWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

@WeatherInfoScope
interface WeatherService {

    @GET("current")
    fun getCurrentWeather(@Query("query") cityName: String): Single<CurrentWeatherResponse>

}