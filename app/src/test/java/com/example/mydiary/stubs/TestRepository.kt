package com.example.mydiary.stubs

import com.example.mydiary.domain.model.AnswerEmotionCrossRefModel
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.presentation.models.RemindModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class TestRepository : Repository {
    override suspend fun getAnswersWithActive(emotionId: String): List<AnswerWithStateModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmotionById(emotionId: String): EmotionModel {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEmotions(): List<EmotionModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmotion(emotion: EmotionModel) {
        TODO("Not yet implemented")
    }

    override suspend fun eddEmotion(
        emotion: EmotionModel, answerEmotionCrossRef: List<AnswerEmotionCrossRefModel>
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun addRemind(remindModel: RemindModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRemind(remindModel: RemindModel) {
        TODO("Not yet implemented")
    }

    override suspend fun editRemind(remindModel: RemindModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRemind(): List<RemindModel> {
        TODO("Not yet implemented")
    }

    override suspend fun addAnswer(answerModel: AnswerModel) {
        TODO("Not yet implemented")
    }

    override fun getSettings(): Flow<SettingsModel> {
        return flow {
            emit(
                SettingsModel(
                    "https://avatars.mds.yandex.net/i?id=fa83661396f15525e1f56fff075df025_l-9271382-images-thumbs&n=13",
                    isSendRemindOn = true,
                    isUseFingerprint = true,
                    name = "Oleg Tinkoff"
                )
            )
        }
    }

    override suspend fun deleteImagePath() {
        TODO("Not yet implemented")
    }

    override suspend fun changeSettings(settings: SettingsModel) {
        TODO("Not yet implemented")
    }
}