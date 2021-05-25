package com.base.di

import com.base.BaseComponent

interface CoreComponentProvider {
    fun provideBaseComponent(): BaseComponent
}