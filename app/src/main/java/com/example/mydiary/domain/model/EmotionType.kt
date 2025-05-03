package com.example.mydiary.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.mydiary.R

enum class EmotionType(@ColorRes val color: Int, @DrawableRes val background: Int) {
    ExcitedWell(R.color.card_yellow, R.drawable.tools_card_background_yellow),
    ExitedBadly(R.color.card_red, R.drawable.tools_card_background_red),
    CalmBadly(R.color.card_blue, R.drawable.tools_card_background_blue),
    CalmWell(R.color.card_green, R.drawable.tools_card_background_green)
}