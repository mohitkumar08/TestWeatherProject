package com.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider
import androidx.activity.viewModels

/***
 * Magic function when using view models in dagger2
 * Ref - https://proandroiddev.com/dagger-dependencies-beyond-the-basics-53474e48f932
 */

inline fun <reified VM : ViewModel> AppCompatActivity.viewModels(crossinline producer: () -> Provider<VM>): Lazy<VM> =
    viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(c: Class<T>) = producer().get() as T
        }
    }