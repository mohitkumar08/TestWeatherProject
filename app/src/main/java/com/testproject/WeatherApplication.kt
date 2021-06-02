package com.testproject

import android.app.Application
import com.base.AppComponent
import com.base.DaggerAppComponent
import com.base.di.AppComponentProvider
import com.base.di.module.AppModule

class WeatherApplication : Application(), AppComponentProvider {

    private val baseComponent by lazy {
            DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun provideBaseComponent(): AppComponent {
        return  baseComponent
    }

}