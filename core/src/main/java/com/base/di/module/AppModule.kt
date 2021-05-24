package com.base.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule(private val context: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = context.applicationContext

    @Provides
    @Singleton
    fun provideApplicationContext(): Application = context

    @Singleton
    @Provides
    fun provideDisposable(): CompositeDisposable = CompositeDisposable()


}