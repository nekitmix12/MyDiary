package com.example.mydiary.data.data_source.local

import com.example.mydiary.data.dao.EmotionDao
import com.example.mydiary.data.entity.EmotionEntity

class EmotionDataSourceImpl(private val emotionDao: EmotionDao) : EmotionDataSource {
    override suspend fun getAnswersWithActive(emotionId: String) =
        emotionDao.getAnswersWithActive(emotionId)

    override suspend fun getEmotionById(emotionId: String) = emotionDao.getEmotionById(emotionId)

    override suspend fun getAllEmotions() = emotionDao.getAllEmotions()

    override suspend fun deleteEmotion(emotion: EmotionEntity) = emotionDao.deleteEmotion(emotion)

    override suspend fun editEmotion(emotion: EmotionEntity) = emotionDao.editEmotion(emotion)
}