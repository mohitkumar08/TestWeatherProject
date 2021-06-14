package com.weatherinfo.di

import com.base.AppComponent
import com.weatherinfo.di.module.RepositoryModule
import com.weatherinfo.di.module.ViewModelModule
import com.weatherinfo.di.module.WeatherServiceModule
import com.weatherinfo.di.scope.WeatherInfoScope
import com.weatherinfo.network.repositiories.WeatherRepository
import com.weatherinfo.weatherdetail.WeatherDetailActivity
import dagger.BindsInstance
import dagger.Component


@WeatherInfoScope
@Component(
    modules = [WeatherServiceModule::class, RepositoryModule::class, ViewModelModule::class],
    dependencies = [AppComponent::class]
)
interface WeatherInfoComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindActivity(activity: WeatherDetailActivity): Builder
        fun dependAppComponent(appComponent: AppComponent): Builder
        fun build(): WeatherInfoComponent
    }

    fun inject(mainActivity: WeatherDetailActivity)

    fun getWeatherRepository(): WeatherRepository

}