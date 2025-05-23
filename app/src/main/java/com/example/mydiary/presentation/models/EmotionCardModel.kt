package com.example.mydiary.presentation.models

import android.graphics.drawable.Drawable
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.presentation.adapters.Item

data class EmotionCardModel(
    val id: String,
    val background: Drawable,
    val date: String,
    val time: String,
    val emotion: String,
    val emotionColor: Int,
    val icon: Drawable,
    val emotionModel: EmotionModel
) : Item