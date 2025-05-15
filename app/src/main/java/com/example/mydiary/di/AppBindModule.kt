package com.example.mydiary.di

import com.example.mydiary.data.data_source.local.DataStoreManager
import com.example.mydiary.data.data_source.local.EmotionAndRemindDataSource
import com.example.mydiary.data.data_source.local.EmotionAndRemindDataSourceImpl
import com.example.mydiary.data.data_source.local.SettingsDataSource
import com.example.mydiary.data.repository.RepositoryImpl
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import com.example.mydiary.domain.utils.StatisticCalculator
import com.example.mydiary.domain.utils.StatisticCalculatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
interface AppBindModule {

    @Binds
    fun bindRepository(repository: RepositoryImpl): Repository

    @Binds
    fun bindDataSource(dataSource: EmotionAndRemindDataSourceImpl): EmotionAndRemindDataSource

    @Binds
    fun bindCalculator(calculatorImpl: StatisticCalculatorImpl): StatisticCalculator

    @Binds
    fun dataStoreManager(dataStoreManager: DataStoreManager): SettingsDataSource

}