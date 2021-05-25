package com.testproject

import android.app.Application
import android.content.Context
import com.base.BaseComponent
import com.base.DaggerBaseComponent
import com.base.di.CoreComponentProvider
import com.base.di.module.AppModule
import com.testproject.di.AppComponent
import com.testproject.di.AppComponentProvider
import com.testproject.di.DaggerAppComponent

class WeatherApplication : Application(), CoreComponentProvider, AppComponentProvider {

    private val baseComponent by lazy {
        DaggerBaseComponent.builder().appModule(AppModule(this)).build()
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
            appComponent= DaggerAppComponent.builder()
                .dependBaseComponent(provideBaseComponent())
                 .build()
        }

    override fun provideBaseComponent(): BaseComponent {
        return baseComponent
    }


    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as WeatherApplication).appComponent
    }

    override fun provideAppComponent(): AppComponent {
        if (::appComponent.isInitialized.not()) {
            initAppComponent()
        }
        return appComponent
    }

}