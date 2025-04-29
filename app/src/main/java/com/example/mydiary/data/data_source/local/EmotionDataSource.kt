package com.example.mydiary.data.data_source.local

import com.example.mydiary.data.dbo.AnswerWithActive
import com.example.mydiary.data.entity.AnswerEmotionCrossRef
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.data.entity.EmotionEntity

interface EmotionDataSource {

    suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithActive>

    suspend fun getEmotionById(emotionId: String): EmotionEntity

    suspend fun getAllEmotions(): List<EmotionEntity>

    suspend fun deleteEmotion(emotion: EmotionEntity)

    suspend fun eddEmotion(
        emotion: EmotionEntity,
        answerEmotionCrossRef: List<AnswerEmotionCrossRef>,
    )

    suspend fun addAnswer(answerEntity: AnswerEntity)
}