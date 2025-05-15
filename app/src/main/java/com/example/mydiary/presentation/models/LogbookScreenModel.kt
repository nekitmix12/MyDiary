package com.example.mydiary.presentation.models

import com.example.mydiary.domain.model.EmotionModel

data class LogbookScreenModel(
    val logsCount: Int,
    val logInDay: Int = 2,
    val logsStreak: Int,
    val loadingEmotions: CircleButton,
    val emotions: List<EmotionModel>,
)