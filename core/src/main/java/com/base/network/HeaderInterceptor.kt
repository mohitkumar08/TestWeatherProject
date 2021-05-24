package com.base.network

import com.base.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

 class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("access_key", BuildConfig.API_KEY)
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)
        return chain.proceed(requestBuilder.build());
    }

}