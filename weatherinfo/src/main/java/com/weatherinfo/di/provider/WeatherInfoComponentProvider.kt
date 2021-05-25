package com.weatherinfo.di.provider

import android.content.Context
import com.base.di.CoreComponentProvider
import com.weatherinfo.di.DaggerWeatherInfoComponent
import com.weatherinfo.di.WeatherInfoComponent

class WeatherInfoComponentProvider {

    companion object {

        @JvmStatic
        private var weatherInfoComponent: WeatherInfoComponent? = null

        @JvmStatic
        fun getBaseComponent(context: Context,): WeatherInfoComponent? {
            if (weatherInfoComponent == null) {
                weatherInfoComponent = DaggerWeatherInfoComponent
                    .builder()
                    .dependBaseComponent((context as CoreComponentProvider).provideBaseComponent()).build()
            }
            return weatherInfoComponent
        }
    }
}