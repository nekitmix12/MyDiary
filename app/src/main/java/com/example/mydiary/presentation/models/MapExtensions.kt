package com.example.mydiary.presentation.models

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.example.mydiary.R
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.EmotionType

fun EmotionModel.toEmotionCardModel(context: Context) = EmotionCardModel(
    id = id,
    background = when(type){
        EmotionType.ExcitedWell->{
            AppCompatResources.getDrawable(
                context, R.drawable.tools_card_background_blue
        ) ?: throw IllegalArgumentException("Not found Drawable")}
        EmotionType.ExitedBadly->{AppCompatResources.getDrawable(
            context, R.drawable.tools_card_background_blue
        ) ?: throw IllegalArgumentException("Not found Drawable")}
        EmotionType.CalmWell->{AppCompatResources.getDrawable(
            context, R.drawable.tools_card_background_blue
        ) ?: throw IllegalArgumentException("Not found Drawable")}
        EmotionType.CalmBadly->{AppCompatResources.getDrawable(
            context, R.drawable.tools_card_background_blue
        ) ?: throw IllegalArgumentException("Not found Drawable")}

    }, date = createDataTime
)