package com.example.mydiary.di

import com.example.mydiary.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
object AppProvidesModule {
    @IOPool
    @Provides
    fun providesIOConfiguration(): UseCase.Configuration =
        UseCase.Configuration(Dispatchers.IO)

    @DefaultPool
    @Provides
    fun providesDefaultConfiguration(): UseCase.Configuration =
        UseCase.Configuration(Dispatchers.Default)
}