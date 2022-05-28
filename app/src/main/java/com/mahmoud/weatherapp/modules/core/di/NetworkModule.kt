package com.mahmoud.weatherapp.modules.core.di

import android.content.Context
import com.mahmoud.weatherapp.BuildConfig.DEBUG
import com.mahmoud.weatherapp.modules.core.data.WeatherKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .followRedirects(false)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()


    @Provides
    @Singleton
    fun providesOkhttpInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("appid", WeatherKeys.geAppId())
                .build()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        if (DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

    @Provides
    @Singleton
    fun cacheFile(@ApplicationContext context: Context): File =
        File(context.cacheDir, "okHttp_cache")

    @Provides
    @Singleton
    fun cache(file: File?): Cache =
        Cache(file!!, (10 * 1000 * 1000)) // 10MB cache
}
