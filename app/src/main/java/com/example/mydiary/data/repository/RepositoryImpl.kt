package com.example.mydiary.data.repository

import com.example.mydiary.data.data_source.local.EmotionDataSource
import com.example.mydiary.data.data_source.local.SettingsDataSource
import com.example.mydiary.data.local_model.SettingLocalModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.model.toAnswerWithStateModel
import com.example.mydiary.domain.model.toEmotionEntity
import com.example.mydiary.domain.model.toEmotionModel
import com.example.mydiary.domain.model.toSettingsLocalModel
import com.example.mydiary.domain.model.toSettingsModel
import com.example.mydiary.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val emotionDataSource: EmotionDataSource,
    private val settingsDataSource: SettingsDataSource,
) : Repository {
    override suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithStateModel> =
        emotionDataSource.getAnswersWithActive(emotionId).map { it.toAnswerWithStateModel() }


    override suspend fun getEmotionById(emotionId: String): EmotionModel =
        emotionDataSource.getEmotionById(emotionId).toEmotionModel()

    override suspend fun getAllEmotions(): List<EmotionModel> =
        emotionDataSource.getAllEmotions().map { it.toEmotionModel() }

    override suspend fun deleteEmotion(emotion: EmotionModel) =
        emotionDataSource.deleteEmotion(emotion.toEmotionEntity())

    override suspend fun editEmotion(emotion: EmotionModel) =
        emotionDataSource.editEmotion(emotion.toEmotionEntity())

    override suspend fun getSettings(): Flow<SettingsModel> =
        settingsDataSource.getSettings().map { it.toSettingsModel() }

    override suspend fun deleteImagePath()=
        settingsDataSource.deleteImagePath()

    override suspend fun changeSettings(settings: SettingsModel) =
        settingsDataSource.changeSettings(settings.toSettingsLocalModel())

}