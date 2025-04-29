package com.example.mydiary.domain.model

import com.example.mydiary.Settings
import com.example.mydiary.data.dbo.AnswerWithActiveDbo
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.data.entity.EmotionEntity
import com.example.mydiary.data.local_model.SettingLocalModel

fun AnswerModel.toAnswerEntity() = AnswerEntity(id, text, questionId)

fun AnswerEntity.toAnswerModel() = AnswerModel(id, text, questionId)

fun AnswerWithStateModel.toAnswerWithActiveDbo() =
    AnswerWithActiveDbo(answer.toAnswerEntity(), state)

fun AnswerWithActiveDbo.toAnswerWithStateModel() =
    AnswerWithStateModel(answer.toAnswerModel(), isActive)

fun Settings.toSettingsModel() = SettingsModel(url, isSendRemindOn, isUseFingerprint, name)

fun SettingsModel.toSettingsLocalModel() =
    SettingLocalModel(imageUrl, isSendRemindOn, isUseFingerprint, name)

fun EmotionModel.toEmotionEntity() = EmotionEntity(id, type, name, createDataType, imageRes)

fun EmotionEntity.toEmotionModel() = EmotionModel(id, type, name, createDataType, imageRes)