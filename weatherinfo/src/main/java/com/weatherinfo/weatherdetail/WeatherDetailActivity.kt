package com.weatherinfo.weatherdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.base.CoreActivity
import com.example.genericresponse.CommState
import com.weatherinfo.R
import com.weatherinfo.databinding.ActivityMainBinding
import com.weatherinfo.di.provider.WeatherInfoComponentProvider
import javax.inject.Inject

const val ARG_CITY_NAME = "city_name"

class WeatherDetailActivity : CoreActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var context: Context

    private lateinit var viewModel: WeatherDetailViewModel

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProvider(this,viewModelFactory).get(WeatherDetailViewModel::class.java)
        addObserver()
        viewModel.getWeather(intent.getStringExtra(ARG_CITY_NAME) ?: "")
    }

    override fun setupActivityComponent() {
        WeatherInfoComponentProvider.getBaseComponent(applicationContext)?.inject(this)
    }

    private fun addObserver() {
        viewModel.weatherData.observe(this, {
                when (it) {
                    is CommState.Loading -> {
                        viewBinding.progressBar.visibility = View.VISIBLE
                    }
                    is CommState.Success -> {
                        viewBinding.progressBar.visibility = View.GONE
                        viewBinding.cityName.text = it.body.location.name
                        viewBinding.temperature.text =
                            it.body.current.temperature.toString().plus("\u2103")
                    }
                    is CommState.Error -> {
                        Toast.makeText(context, getText(R.string.generic_message_for_error), Toast.LENGTH_LONG)
                            .show()
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