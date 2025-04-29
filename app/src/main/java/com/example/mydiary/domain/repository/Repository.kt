package com.example.mydiary.domain.repository

import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.SettingsModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithStateModel>

    suspend fun getEmotionById(emotionId: String): EmotionModel

    suspend fun getAllEmotions(): List<EmotionModel>

    suspend fun deleteEmotion(emotion: EmotionModel)

    suspend fun editEmotion(emotion: EmotionModel)

    suspend fun getSettings(): Flow<SettingsModel>

    suspend fun deleteImagePath()

    suspend fun changeSettings(settings: SettingsModel)
}