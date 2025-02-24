package com.example.mydiary.presentation.models

import android.graphics.drawable.Drawable
import com.example.mydiary.presentation.adapters.Item

data class SettingParamModel(
    val icon:Drawable,
    val test:String,
    val state:Boolean
):Item
