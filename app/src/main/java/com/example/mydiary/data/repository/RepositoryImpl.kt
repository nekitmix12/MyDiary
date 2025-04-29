package com.example.mydiary.data.repository

import com.example.mydiary.data.data_source.local.EmotionDataSource
import com.example.mydiary.data.data_source.local.SettingsDataSource
import com.example.mydiary.data.local_model.SettingLocalModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val emotionDataSource: EmotionDataSource,
    private val settingsDataSource: SettingsDataSource,
) : Repository {
    override suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithStateModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmotionById(emotionId: String): EmotionModel {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEmotions(): List<EmotionModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmotion(emotion: EmotionModel) {
        TODO("Not yet implemented")
    }

    override suspend fun editEmotion(emotion: EmotionModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getSettings(): Flow<SettingsModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteImagePath() {
        TODO("Not yet implemented")
    }

    override suspend fun changeSettings(settings: SettingLocalModel) {
        TODO("Not yet implemented")
    }

}