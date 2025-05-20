package com.example.mydiary.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mydiary.data.data_source.local.EmotionAndRemindDataSource
import com.example.mydiary.data.data_source.local.SettingsDataSource
import com.example.mydiary.di.AppScope
import com.example.mydiary.domain.model.AnswerEmotionCrossRefModel
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.QuestionModel
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.model.tiQuestionModel
import com.example.mydiary.domain.model.toAnswerEmotionCrossRef
import com.example.mydiary.domain.model.toAnswerEntity
import com.example.mydiary.domain.model.toAnswerWithStateModel
import com.example.mydiary.domain.model.toEmotionEntity
import com.example.mydiary.domain.model.toEmotionModel
import com.example.mydiary.domain.model.toRemindEntity
import com.example.mydiary.domain.model.toRemindModel
import com.example.mydiary.domain.model.toSettingsLocalModel
import com.example.mydiary.domain.model.toSettingsModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.presentation.models.RemindModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class RepositoryImpl @Inject constructor(
    private val emotionDataSource: EmotionAndRemindDataSource,
    private val settingsDataSource: SettingsDataSource,
) : Repository {
    override suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithStateModel> =
        emotionDataSource.getAnswersWithActive(emotionId).map { it.toAnswerWithStateModel() }


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getEmotionById(emotionId: String): EmotionModel =
        emotionDataSource.getEmotionById(emotionId).toEmotionModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllEmotions(): List<EmotionModel> =
        emotionDataSource.getAllEmotions().map { it.toEmotionModel() }

    override suspend fun deleteEmotion(emotion: EmotionModel) =
        emotionDataSource.deleteEmotion(emotion.toEmotionEntity())

    override suspend fun addEmotion(
        emotion: EmotionModel,
        answerEmotionCrossRef: List<AnswerEmotionCrossRefModel>,
    ) = emotionDataSource.addEmotion(
        emotion.toEmotionEntity(),
        answerEmotionCrossRef.map { it.toAnswerEmotionCrossRef() })

    override suspend fun addRemind(remindModel: RemindModel) =
        emotionDataSource.addRemind(remindModel.toRemindEntity())

    override suspend fun deleteRemind(remindModel: RemindModel) =
        emotionDataSource.deleteRemind(remindModel.toRemindEntity())

    override suspend fun editRemind(remindModel: RemindModel) =
        emotionDataSource.editRemind(remindModel.toRemindEntity())


    override suspend fun addAnswer(answerModel: AnswerModel) =
        emotionDataSource.addAnswer(answerModel.toAnswerEntity())

    override fun getSettings(): Flow<SettingsModel> =
        settingsDataSource.getSettings().map { it.toSettingsModel() }

    override suspend fun deleteImagePath() =
        settingsDataSource.deleteImagePath()

    override suspend fun getAllRemind(): List<RemindModel> =
        emotionDataSource.getAllRemind().map { it.toRemindModel() }

    override suspend fun changeSettings(settings: SettingsModel) =
        settingsDataSource.changeSettings(settings.toSettingsLocalModel())

    override suspend fun getAllQuestions(): List<QuestionModel> =
        emotionDataSource.getAllQuestions().map { it.tiQuestionModel() }

}