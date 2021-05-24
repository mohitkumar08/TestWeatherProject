package com.testproject

import android.app.Application
import com.base.DaggerBaseComponent
import com.base.di.module.AppModule
import com.testproject.di.AppComponent
import com.testproject.di.DaggerAppComponent

class WeatherApplication : Application()  {

    override fun onCreate() {
        super.onCreate()
    }

    fun initAppComponent(): AppComponent {
            return DaggerAppComponent.builder()
                .dependBaseComponent(
                    DaggerBaseComponent.builder().appModule(AppModule(this)).build())
                 .build()
        }
}