package com.example.mydiary.presentation.models

import android.graphics.drawable.Drawable
import com.example.mydiary.presentation.adapters.Item

data class EmotionIndicatorModel (
    val icon:Drawable,
    val emotion:String,
    val partOf:Float,
    val background:Drawable,
    val count:String,
) : Item