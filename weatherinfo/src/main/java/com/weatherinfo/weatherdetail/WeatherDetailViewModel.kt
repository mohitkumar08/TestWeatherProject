package com.weatherinfo.weatherdetail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.genericresponse.CommState
import com.weatherinfo.network.repositiories.WeatherRepository
import com.weatherinfo.network.response.current_weather.CurrentWeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherDetailViewModel constructor(var repository: WeatherRepository,var disposable:CompositeDisposable): ViewModel() {

    private val _weatherDataLive = MutableLiveData<CommState<CurrentWeatherResponse>>()
    val weatherDataLive: LiveData<CommState<CurrentWeatherResponse>>
        get() = _weatherDataLive


    var weatherData: ObservableField<CurrentWeatherResponse> =  ObservableField()

    fun getWeather(cityName: String) {
        disposable.add(repository.getCurrentWeather(cityName)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _weatherDataLive.postValue(CommState.Loading())
            }
            .doFinally {
                _weatherDataLive.postValue(CommState.Complete())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({data->
                _weatherDataLive.postValue(CommState.Success(data))
                weatherData.set(data)
            },{
                _weatherDataLive.postValue(CommState.Error())
                it.printStackTrace()
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
