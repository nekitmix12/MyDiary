package com.example.mydiary.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.mydiary.R

enum class EmotionType(
    @ColorRes val color: Int,
    @DrawableRes val background: Int,
    @ColorRes val gradientStart: Int,
    @ColorRes val gradientEnd: Int,
    val positiveType: PositiveType,
    val feelType:FeelType
) {
    ExcitedWell(
        R.color.card_yellow,
        R.drawable.tools_card_background_yellow,
        R.color.logbook_gradient_yellow_1,
        R.color.logbook_gradient_yellow_2,
        PositiveType.Well,
        FeelType.Exited
    ),
    ExitedBadly(
        R.color.card_red,
        R.drawable.tools_card_background_red,
        R.color.logbook_gradient_red_1,
        R.color.logbook_gradient_red_2,
        PositiveType.Badly,
        FeelType.Exited
    ),
    CalmBadly(
        R.color.card_blue,
        R.drawable.tools_card_background_blue,
        R.color.logbook_gradient_blue_1,
        R.color.logbook_gradient_blue_2,
        PositiveType.Badly,
        FeelType.Calm
    ),
    CalmWell(
        R.color.card_green,
        R.drawable.tools_card_background_green,
        R.color.logbook_gradient_green_1,
        R.color.logbook_gradient_green_2,
        PositiveType.Well,
        FeelType.Calm
    )
}