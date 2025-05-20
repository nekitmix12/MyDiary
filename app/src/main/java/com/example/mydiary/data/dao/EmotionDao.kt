package com.example.mydiary.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.mydiary.data.dbo.AnswerWithActiveDbo
import com.example.mydiary.data.dbo.EmotionWithDetailsDbo
import com.example.mydiary.data.entity.AnswerEmotionCrossRef
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.data.entity.EmotionEntity
import com.example.mydiary.data.entity.QuestionEntity

@Dao
interface EmotionDao {
    @Transaction
    @Query(
        """
        SELECT 
             answer.id          AS ans_id,
             answer.text        AS ans_text,
             answer.question_id  AS ans_questionId,
             answer_emotion.is_active AS isActive,
             question.id        AS questionId
        FROM answer
        JOIN answer_emotion 
            ON answer.id = answer_emotion.answer_id
        JOIN question 
            ON answer.question_id = question.id
        WHERE answer_emotion.emotion_id = :emotionId
    """
    )
    suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithActiveDbo>

    @Query("""SELECT * FROM question""")
    suspend fun getAllQuestions(): List<QuestionEntity>

    @Query(
        """
            Select answer.id, answer.text, answer.question_id
            From answer 
            Join question 
                On answer.question_id = question.id
    """
    )
    suspend fun getAnswers():List<AnswerEntity>

    @Query(
        """SELECT * 
        FROM emotion
        WHERE emotion.id = :emotionId
        LIMIT 1
        """
    )
    suspend fun getEmotionById(emotionId: String): EmotionEntity

/*    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: QuestionEntity)*/

    @Query("""SELECT * FROM emotion""")
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