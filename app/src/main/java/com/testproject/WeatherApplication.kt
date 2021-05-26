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

    private lateinit var baseComponent: BaseComponent

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

    private fun initBaseComponent() {
        baseComponent= DaggerBaseComponent.builder().appModule(AppModule(this)).build()
    }

    override fun provideBaseComponent(): BaseComponent {
        if (::baseComponent.isInitialized.not()) {
            initBaseComponent()
        }
        return baseComponent
    }

    override fun provideAppComponent(): AppComponent {
        if (::appComponent.isInitialized.not()) {
            initAppComponent()
        }
        return appComponent
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) = (context.applicationContext as WeatherApplication).appComponent
    }


}