package com.example.mydiary.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.mydiary.data.dbo.AnswerWithActive
import com.example.mydiary.data.entity.EmotionEntity
import retrofit2.http.GET

@Dao
interface EmotionDao {
    @Transaction
    @Query(
        """
        SELECT 
            AnswerEntity.*, 
            AnswerQuestionCrossRef.isActive 
        FROM AnswerEntity
        JOIN AnswerQuestionCrossRef 
            ON AnswerEntity.id = AnswerQuestionCrossRef.answerId
        JOIN QuestionEntity 
            ON AnswerEntity.questionId = QuestionEntity.id
        WHERE AnswerQuestionCrossRef.emotionId = :emotionId
    """
    )
    suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithActive>

    @Query(
        """SELECT * 
        FROM EmotionEntity
        WHERE EmotionEntity.id = :emotionId
        LIMIT 1
        """
    )
    suspend fun getEmotionById(emotionId: String): EmotionEntity

    @Query("""
        SELECT * FROM EMOTIONENTITY
    """)
    suspend fun getAllEmotions(): List<EmotionEntity>
}