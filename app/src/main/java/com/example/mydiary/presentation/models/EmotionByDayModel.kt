package com.example.mydiary.presentation.models

import android.graphics.drawable.Drawable
import com.example.mydiary.presentation.adapters.Item

data class EmotionByDayModel(
    val data: String,
    val emotionType:String,
    val listDrawable:List<Drawable>,
    val imageSize:Int,
) : Item
