package com.example.mydiary.presentation.models

import android.graphics.drawable.Drawable

data class EmotionElementModel(
    val emotion: String,
    val description: String,
    val color: Int,
    val form: Drawable,
    val formAfter: Drawable,
    val emotionEnum: Emotion
)