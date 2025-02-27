package com.example.mydiary.presentation.models

import android.graphics.Color
import androidx.annotation.ColorInt

data class Gap (
    @ColorInt val color: Int = Color.TRANSPARENT,
    val height: Int = 0,
    val paddingStart: Int = 0,
    val paddingEnd: Int = 0,
    val onDivider:Int = 0,
    val underDivider:Int = 0,
)