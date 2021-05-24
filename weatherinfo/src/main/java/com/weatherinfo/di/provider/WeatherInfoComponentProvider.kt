package com.weatherinfo.di.provider

import android.app.Application
import com.base.DaggerBaseComponent
import com.base.di.module.AppModule
import com.weatherinfo.di.DaggerWeatherInfoComponent
import com.weatherinfo.di.WeatherInfoComponent

class WeatherInfoComponentProvider {

    companion object {

        @JvmStatic
        private var weatherInfoComponent: WeatherInfoComponent? = null

        @JvmStatic
        fun getBaseComponent(application: Application): WeatherInfoComponent? {
            if (weatherInfoComponent == null) {
                weatherInfoComponent = DaggerWeatherInfoComponent
                    .builder()
                    .dependBaseComponent(
                        DaggerBaseComponent
                            .builder()
                            .appModule(AppModule(application))
                            .build()
                    ).build()
            }
            return weatherInfoComponent
        }
    }
}