package com.example.mydiary.data.data_source.local

import com.example.mydiary.data.dbo.AnswerWithActiveDbo
import com.example.mydiary.data.entity.AnswerEmotionCrossRef
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.data.entity.EmotionEntity
import com.example.mydiary.data.entity.RemindEntity

interface EmotionAndRemindDataSource {

    suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithActiveDbo>

    suspend fun getEmotionById(emotionId: String): EmotionEntity

    suspend fun getAllEmotions(): List<EmotionEntity>

    suspend fun deleteEmotion(emotion: EmotionEntity)

    suspend fun addEmotion(
        emotion: EmotionEntity,
        answerEmotionCrossRef: List<AnswerEmotionCrossRef>,
    )
    suspend fun addAnswer(answerEntity: AnswerEntity)

    suspend fun addRemind(remindEntity: RemindEntity)

    suspend fun deleteRemind(remindEntity: RemindEntity)

    suspend fun editRemind(remindEntity: RemindEntity)

    suspend fun getAllRemind(): List<RemindEntity>


}