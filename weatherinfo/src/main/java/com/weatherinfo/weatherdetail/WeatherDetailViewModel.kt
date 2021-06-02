package com.weatherinfo.weatherdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.genericresponse.CommState
import com.example.genericresponse.NetworkResponse
import com.weatherinfo.network.repositiories.WeatherRepository
import com.weatherinfo.network.response.current_weather.CurrentWeatherResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class WeatherDetailViewModel constructor(var repository: WeatherRepository,var disposable:CompositeDisposable): ViewModel() {

    private val _weatherData = MutableLiveData<CommState<CurrentWeatherResponse>>()
    val weatherData: LiveData<CommState<CurrentWeatherResponse>>
        get() = _weatherData

    fun getWeather(cityName: String) {
        disposable.add(repository.getCurrentWeather(cityName)
            .subscribeOn(Schedulers.io())
            .subscribe({data->
                when (data) {
                    is NetworkResponse.Success -> {
                        _weatherData.postValue(CommState.Success(data.body))
                    }
                    else -> {
                        _weatherData.postValue(CommState.Error())
                    }
                }
            },{
                _weatherData.postValue(CommState.Error())
                it.printStackTrace()
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
