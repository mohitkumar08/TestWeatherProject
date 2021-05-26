package com.base

import android.content.Context
import com.base.di.module.AppModule
import com.base.di.module.NetworkModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface BaseComponent {

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder
        fun build(): BaseComponent
    }

    fun retrofitApi(): Retrofit
    fun requireContext():Context
    fun requireDisposable(): CompositeDisposable


}
