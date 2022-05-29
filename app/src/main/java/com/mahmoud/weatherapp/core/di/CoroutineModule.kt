package com.mahmoud.weatherapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
