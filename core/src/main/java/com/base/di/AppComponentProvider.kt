package com.base.di

import com.base.AppComponent

interface AppComponentProvider {
    fun provideBaseComponent(): AppComponent
}