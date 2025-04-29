package com.example.mydiary.data.repository

import com.example.mydiary.data.data_source.local.EmotionDataSource
import com.example.mydiary.data.data_source.local.SettingsDataSource
import com.example.mydiary.domain.repository.Repository

class RepositoryImpl(
    private val emotionDataSource: EmotionDataSource,
    private val settingsDataSource: SettingsDataSource,
) : Repository {

}