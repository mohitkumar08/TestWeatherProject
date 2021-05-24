package com.testproject.di

import android.content.Context
import com.base.BaseComponent
import com.example.discope.AppScope
import com.testproject.home.HomeActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [BaseComponent::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun dependBaseComponent(baseComponent: BaseComponent): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: HomeActivity)


}