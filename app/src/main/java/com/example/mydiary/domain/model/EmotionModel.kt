package com.example.mydiary.domain.model

import com.example.mydiary.presentation.models.Emotion
import java.time.Instant

data class EmotionModel(
    val id: String,
    val emotion: Emotion,
    val name: String,
    val createDataTime: Instant,
    val imageRes: String,
)