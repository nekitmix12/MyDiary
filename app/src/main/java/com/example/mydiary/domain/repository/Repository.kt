package com.example.mydiary.domain.repository

import com.example.mydiary.domain.model.AnswerEmotionCrossRefModel
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.presentation.models.RemindModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithStateModel>

    suspend fun getEmotionById(emotionId: String): EmotionModel

    suspend fun getAllEmotions(): List<EmotionModel>

    suspend fun deleteEmotion(emotion: EmotionModel)

    suspend fun eddEmotion(
        emotion: EmotionModel,
        answerEmotionCrossRef: List<AnswerEmotionCrossRefModel>,
    )

    suspend fun addRemind(remindModel: RemindModel)

    suspend fun deleteRemind(remindModel: RemindModel)

    suspend fun editRemind(remindModel: RemindModel)

    suspend fun getAllRemind(): List<RemindModel>

    suspend fun addAnswer(answerModel: AnswerModel)

    fun getSettings(): Flow<SettingsModel>

    suspend fun deleteImagePath()

    suspend fun changeSettings(settings: SettingsModel)
}