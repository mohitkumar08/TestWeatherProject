package com.weatherinfo.network.repositiories

import com.example.genericresponse.NetworkResponse
import com.weatherinfo.network.response.current_weather.CurrentWeatherResponse
import io.reactivex.Single

interface WeatherRepository {

    fun getCurrentWeather(cityName:String): Single<CurrentWeatherResponse>
}