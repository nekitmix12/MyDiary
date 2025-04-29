package com.example.mydiary.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

import com.example.mydiary.data.dbo.AnswerWithActive
import com.example.mydiary.data.dbo.EmotionWithDetailsDbo
import com.example.mydiary.data.entity.AnswerEmotionCrossRef
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.data.dbo.AnswerWithActiveDbo
import com.example.mydiary.data.entity.EmotionEntity

@Dao
interface EmotionDao {
    @Transaction
    @Query(
        """
        SELECT 
            AnswerEntity.*, 
            AnswerEmotionCrossRef.isActive 
        FROM AnswerEntity
        JOIN AnswerEmotionCrossRef 
            ON AnswerEntity.id = AnswerEmotionCrossRef.answerId
        JOIN QuestionEntity 
            ON AnswerEntity.questionId = QuestionEntity.id
        WHERE AnswerEmotionCrossRef.emotionId = :emotionId
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

    @Query("""SELECT * FROM EMOTIONENTITY""")
    suspend fun getAllEmotions(): List<EmotionEntity>

    @Delete
    suspend fun deleteEmotion(emotion: EmotionEntity)

    @Update
    suspend fun editEmotion(emotion: EmotionEntity)

    @Insert(AnswerEntity::class, OnConflictStrategy.REPLACE)
    suspend fun addAnswer(answerEntity: AnswerEntity)


    @Insert
    suspend fun insertEmotionAnswer(answerEmotionCrossRef: AnswerEmotionCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmotionAnswer(answerEmotionCrossRefs: List<AnswerEmotionCrossRef>)

    @Insert
    suspend fun insertEmotion(emotion: EmotionEntity)

    @Transaction
    suspend fun getEmotionWithDetails(emotionId: String) =
        EmotionWithDetailsDbo(getEmotionById(emotionId), getAnswersWithActive(emotionId))

    @Transaction
    suspend fun addEmotion(
        emotion: EmotionEntity,
        answerEmotionCrossRef: List<AnswerEmotionCrossRef>,
    ) {
        insertEmotion(emotion)
        insertEmotionAnswer(answerEmotionCrossRef)
    }
}