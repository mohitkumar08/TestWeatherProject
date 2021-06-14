package com.weatherinfo.weatherdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.base.CoreActivity
import com.base.di.AppComponentProvider
import com.example.genericresponse.CommState
import com.weatherinfo.R
import com.weatherinfo.databinding.ActivityMainBinding
import com.weatherinfo.di.DaggerWeatherInfoComponent
import javax.inject.Inject

const val ARG_CITY_NAME = "city_name"

class WeatherDetailActivity : CoreActivity() {

    @Inject
    lateinit var  viewModel :WeatherDetailViewModel

    @Inject
    lateinit var context: Context

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinding.weatherViewModel=viewModel
        addObserver()
        viewModel.getWeather(intent.getStringExtra(ARG_CITY_NAME) ?: "")
    }

    override fun setupActivityComponent() {
        DaggerWeatherInfoComponent
            .builder()
            .bindActivity(this)
            .dependAppComponent((applicationContext as AppComponentProvider).provideBaseComponent())
            .build()
            .inject(this)
    }

    private fun addObserver() {
        viewModel.weatherDataLive.observe(this, {
                when (it) {
                    is CommState.Loading -> {
                        viewBinding.progressBar.visibility = View.VISIBLE
                    }
                    is CommState.Success -> {
                        viewBinding.progressBar.visibility = View.GONE
                    }
                    is CommState.Error -> {
                        Toast.makeText(context, getText(R.string.generic_message_for_error), Toast.LENGTH_LONG)
                            .show()
                    }
                    is CommState.Complete -> {
                        viewBinding.progressBar.visibility = View.GONE
                    }
                }
            })
    }

    companion object {
        fun startWeatherActivity(context: Context, cityName: String) {
            context.startActivity(Intent(context, WeatherDetailActivity::class.java).apply {
                putExtra(ARG_CITY_NAME, cityName)
            })
        }
    }
}