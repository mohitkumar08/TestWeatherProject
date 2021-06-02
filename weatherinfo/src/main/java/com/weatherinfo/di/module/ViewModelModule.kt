package com.weatherinfo.di.module

import androidx.lifecycle.ViewModelProvider
import com.weatherinfo.network.repositiories.WeatherRepository
import com.weatherinfo.weatherdetail.WeatherDetailActivity
import com.weatherinfo.weatherdetail.WeatherDetailModelFactory
import com.weatherinfo.weatherdetail.WeatherDetailViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ViewModelModule {

    @Provides
    fun weatherDetailViewModel( activity:WeatherDetailActivity,factory: WeatherDetailModelFactory): WeatherDetailViewModel {
        return ViewModelProvider(activity, factory).get(WeatherDetailViewModel::class.java)
    }

    @Provides
    fun weatherDetailModelFactory(repository: WeatherRepository, disposable: CompositeDisposable) =  WeatherDetailModelFactory(repository,disposable)

}