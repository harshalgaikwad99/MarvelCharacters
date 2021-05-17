package com.globant.marvelcharacters.data.datasource.remote.config

import com.globant.marvelcharacters.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MarvelApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val httpUrl = originalRequest.url
        val commonApiParamProvider = CommonApiParamProvider()
        val timeStamp = commonApiParamProvider.getTimeStamp()
        val url = httpUrl.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_CONFIG_PUBLIC_KEY)
            .addQueryParameter(HASH, commonApiParamProvider.generateHashKey(timeStamp))
            .addQueryParameter(TIMESTAMP, timeStamp.toString()).build()
        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

    private companion object {
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
        private const val TIMESTAMP = "ts"
    }
}