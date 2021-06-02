package com.testproject.home.di

import com.base.AppComponent
import com.testproject.home.HomeActivity
import dagger.Component
import javax.inject.Scope


@HomeActivityScope
@Component(dependencies = [AppComponent::class])
interface HomeActivityComponent {

    @Component.Builder
    interface Builder {
        fun dependAppComponent(appComponent: AppComponent): Builder
        fun build(): HomeActivityComponent
    }

    fun inject(mainActivity: HomeActivity)
}


@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class HomeActivityScope
