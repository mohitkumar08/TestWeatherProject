package com.testproject.home

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.base.CoreActivity
import com.base.di.CoreComponentProvider
import com.testproject.R
import com.testproject.WeatherApplication
import com.testproject.databinding.ActivityHomeBinding
import com.testproject.di.AppComponentProvider
import com.weatherinfo.weatherdetail.WeatherDetailActivity
import javax.inject.Inject

class HomeActivity : CoreActivity() {

    @Inject
    lateinit var context: Context

    private lateinit var viewBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewBinding.button.setOnClickListener {
            if (viewBinding.editTextNumber.text.isNullOrEmpty()) {
                Toast.makeText(context, getText(R.string.pincode_empty_message), Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            startWeatherActivity(viewBinding.editTextNumber.text.toString())
        }
    }

    override fun setupActivityComponent() {
        (application as AppComponentProvider).provideAppComponent().inject(this)
    }

    private fun startWeatherActivity(cityName:String) {
        WeatherDetailActivity.startWeatherActivity(this, cityName)
    }
}