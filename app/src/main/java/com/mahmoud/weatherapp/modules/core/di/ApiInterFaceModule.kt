package com.mahmoud.weatherapp.modules.core.di

import com.mahmoud.weatherapp.modules.core.data.ApiService
import com.mahmoud.weatherapp.modules.core.data.WeatherKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiInterFaceModule {
    @Provides
    @Singleton
    fun getApiInterface(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideClient(
        okHttpClient: OkHttpClient?,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(WeatherKeys.geApiUrl())
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient!!)
        .build()
}
