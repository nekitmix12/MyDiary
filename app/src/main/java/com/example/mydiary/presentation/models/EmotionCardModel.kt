package com.example.mydiary.presentation.models

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.LinearGradient
import com.example.mydiary.presentation.adapters.Item

data class EmotionCardModel(
    val background: Drawable,
    val date: String,
    val time:String,
    val emotion:String,
    val emotionColor:Int,
    val icon:Drawable,
):Item