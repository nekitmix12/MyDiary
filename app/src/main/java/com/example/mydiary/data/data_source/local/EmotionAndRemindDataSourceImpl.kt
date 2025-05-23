package com.example.mydiary.data.data_source.local

import com.example.mydiary.data.dao.EmotionDao
import com.example.mydiary.data.dao.RemindDao
import com.example.mydiary.data.entity.AnswerEmotionCrossRef
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.data.entity.EmotionEntity
import com.example.mydiary.data.entity.QuestionEntity
import com.example.mydiary.data.entity.RemindEntity
import javax.inject.Inject

class EmotionAndRemindDataSourceImpl @Inject constructor(
    private val emotionDao: EmotionDao,
    private val remindDao: RemindDao,
) : EmotionAndRemindDataSource {
    override suspend fun getAnswersWithActive(emotionId: String) =
        emotionDao.getAnswersWithActive(emotionId)

    override suspend fun getEmotionById(emotionId: String) = emotionDao.getEmotionById(emotionId)

    override suspend fun getAllEmotions() = emotionDao.getAllEmotions()

    override suspend fun deleteEmotion(emotion: EmotionEntity) = emotionDao.deleteEmotion(emotion)


    override suspend fun addAnswer(answerEntity: AnswerEntity) = emotionDao.addAnswer(answerEntity)

    override suspend fun addEmotion(
        emotion: EmotionEntity,
        answerEmotionCrossRef: List<AnswerEmotionCrossRef>,
    ) = emotionDao.addEmotion(emotion, answerEmotionCrossRef)

    override suspend fun getAnswers(): List<AnswerEntity> =
        emotionDao.getAnswers()


    override suspend fun getAllQuestions(): List<QuestionEntity> =
        emotionDao.getAllQuestions()

    override suspend fun addRemind(remindEntity: RemindEntity) =
        remindDao.addRemind(remindEntity)

    override suspend fun deleteRemind(remindEntity: RemindEntity) =
        remindDao.deleteRemind(remindEntity)

    override suspend fun editRemind(remindEntity: RemindEntity) =
        remindDao.editRemind(remindEntity)

    override suspend fun getAllRemind() =
        remindDao.getReminds()

    override suspend fun editAnswer(answerEntity: AnswerEntity) =
        emotionDao.editAnswer(answerEntity)

    override suspend fun addEmotionAnswerState(answerEmotionCrossRef: List<AnswerEmotionCrossRef>) =
        emotionDao.addEmotionAnswerState(answerEmotionCrossRef)

}