package com.weatherinfo.weatherdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weatherinfo.network.repositiories.WeatherRepository
import io.reactivex.disposables.CompositeDisposable

class WeatherDetailModelFactory constructor(
    private val repository: WeatherRepository,
    private val  disposable: CompositeDisposable
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != WeatherDetailViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return WeatherDetailViewModel(repository, disposable) as T
    }
}
