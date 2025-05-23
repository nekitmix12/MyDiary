package com.example.mydiary.domain.repository

import com.example.mydiary.data.entity.AnswerEmotionCrossRef
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.domain.model.AnswerEmotionCrossRefModel
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.QuestionModel
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.presentation.models.RemindModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithStateModel>

    suspend fun getEmotionById(emotionId: String): EmotionModel

    suspend fun getAllEmotions(): List<EmotionModel>

    suspend fun deleteEmotion(emotion: EmotionModel)

    suspend fun addEmotion(
        emotion: EmotionModel,
        answerEmotionCrossRef: List<AnswerEmotionCrossRefModel>,
    )

    suspend fun addRemind(remindModel: RemindModel)

    suspend fun deleteRemind(remindModel: RemindModel)

    suspend fun editRemind(remindModel: RemindModel)

    suspend fun getAllRemind(): List<RemindModel>

    suspend fun addAnswer(answerModel: AnswerModel)

    suspend fun getAnswers(): List<AnswerModel>

    fun getSettings(): Flow<SettingsModel>

    suspend fun deleteImagePath()

    suspend fun changeSettings(settings: SettingsModel)

    suspend fun getAllQuestions(): List<QuestionModel>

    suspend fun editAnswer(answerModel: AnswerModel)

    suspend fun addEmotionAnswerState(answerEmotionCrossRef: List<AnswerEmotionCrossRefModel>)


}