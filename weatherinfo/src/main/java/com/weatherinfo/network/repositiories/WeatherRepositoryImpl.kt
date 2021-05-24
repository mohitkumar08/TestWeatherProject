package com.weatherinfo.network.repositiories

import com.example.genericresponse.NetworkResponse
import com.weatherinfo.di.scope.WeatherInfoScope
import com.weatherinfo.network.response.current_weather.CurrentWeatherResponse
import com.weatherinfo.network.service.WeatherService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@WeatherInfoScope
class WeatherRepositoryImpl @Inject constructor() : WeatherRepository {

    @Inject
    lateinit var weatherService: WeatherService

    private val TAG="Weather"

    override fun getCurrentWeather(cityName: String): Single<NetworkResponse<CurrentWeatherResponse>> {
        return Single.create { emitter ->
            weatherService
                .getCurrentWeather(cityName)
                .subscribeOn(Schedulers.io())
                /*.doOnSubscribe {
                    Timber.tag(TAG).d("doOnSubscribe")
                }
                .doFinally {
                    Timber.tag(TAG).d("doFinally")
                }
                .doAfterTerminate {
                    Timber.tag(TAG).d("doAfterTerminate")
                }*/
                .subscribe({ cw ->
                    Timber.tag(TAG).d("Success")
                    emitter.onSuccess(NetworkResponse.Success(cw))
                }, {
                    Timber.tag(TAG).d("Exception")
                    it.printStackTrace()
                    emitter.onError(it)
                })

        }
    }
}