package com.example.mydiary.data.data_source.local

import com.example.mydiary.data.dbo.AnswerWithActiveDbo
import com.example.mydiary.data.entity.EmotionEntity

interface EmotionDataSource {

    suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithActiveDbo>

    suspend fun getEmotionById(emotionId: String): EmotionEntity

    suspend fun getAllEmotions(): List<EmotionEntity>

    suspend fun deleteEmotion(emotion: EmotionEntity)

    suspend fun editEmotion(emotion: EmotionEntity)
}