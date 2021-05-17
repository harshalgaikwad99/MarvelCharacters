package com.globant.marvelcharacters.data.di

import com.globant.marvelcharacters.data.BuildConfig
import com.globant.marvelcharacters.data.datasource.remote.config.MarvelApiInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setLenient().create()

    @Provides
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun providesOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        marvelApiInterceptor: MarvelApiInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(marvelApiInterceptor)
            .build()

    @Provides
    fun providesMarvelApiIntercept(): MarvelApiInterceptor =
        MarvelApiInterceptor()

    @Provides
    fun providesBaseUrl(): HttpUrl {
        val url = BuildConfig.MARVEL_BASE_URL
        return url.toHttpUrl()
    }

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(providesBaseUrl())
            .addConverterFactory(providesGsonConverterFactory(provideGson()))
            .client(
                providesOkHttpClient(
                    providesHttpLoggingInterceptor(),
                    providesMarvelApiIntercept()
                )
            ).build()
}