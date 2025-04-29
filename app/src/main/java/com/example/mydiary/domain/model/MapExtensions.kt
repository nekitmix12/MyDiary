package com.example.mydiary.domain.model

import com.example.mydiary.Settings
import com.example.mydiary.data.dbo.AnswerWithActiveDbo
import com.example.mydiary.data.entity.AnswerEmotionCrossRef
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.data.entity.EmotionEntity
import com.example.mydiary.data.entity.RemindEntity
import com.example.mydiary.data.local_model.SettingLocalModel
import com.example.mydiary.presentation.models.RemindModel

fun AnswerModel.toAnswerEntity() = AnswerEntity(id, text, questionId)

fun AnswerEntity.toAnswerModel() = AnswerModel(id, text, questionId)

fun AnswerWithStateModel.toAnswerWithActiveDbo() =
    AnswerWithActiveDbo(answer.toAnswerEntity(), state, questionId)

fun AnswerWithActiveDbo.toAnswerWithStateModel() =
    AnswerWithStateModel(answer.toAnswerModel(), isActive, questionId)

fun Settings.toSettingsModel() = SettingsModel(url, isSendRemindOn, isUseFingerprint, name)

fun SettingsModel.toSettingsLocalModel() =
    SettingLocalModel(imageUrl, isSendRemindOn, isUseFingerprint, name)

fun EmotionModel.toEmotionEntity() = EmotionEntity(id, type, name, createDataTime, imageRes)

fun EmotionEntity.toEmotionModel() = EmotionModel(id, type, name, createDataTime, imageRes)

fun AnswerEmotionCrossRef.toAnswerEmotionCrossRefModel() =
    AnswerEmotionCrossRefModel(emotionId, answerId, isActive)

fun AnswerEmotionCrossRefModel.toAnswerEmotionCrossRef() =
    AnswerEmotionCrossRef(emotionId, answerId, isActive)

fun RemindEntity.toRemindModel() = RemindModel(id, time)

fun RemindModel.toRemindEntity() = RemindEntity(uuid, data)