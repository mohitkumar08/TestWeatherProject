package com.weatherinfo.weatherdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.genericresponse.CommState
import com.example.genericresponse.NetworkResponse
import com.weatherinfo.network.repositiories.WeatherRepository
import com.weatherinfo.network.response.current_weather.CurrentWeatherResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class WeatherDetailViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var repository: WeatherRepository

    @Inject
    lateinit var disposables:CompositeDisposable


    private val _weatherData = MutableLiveData<CommState<CurrentWeatherResponse>>()
    val weatherData: LiveData<CommState<CurrentWeatherResponse>>
        get() = _weatherData

    fun getWeather(cityName: String) {
        disposables.add(repository.getCurrentWeather(cityName)
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
        disposables.clear()
    }
}
