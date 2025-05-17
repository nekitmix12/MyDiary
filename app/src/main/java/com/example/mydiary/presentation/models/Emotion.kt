package com.example.mydiary.presentation.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mydiary.R
import com.example.mydiary.domain.model.EmotionType

enum class Emotion(
    @StringRes val emotion: Int,
    @DrawableRes val iconRes: Int,
    val type: EmotionType,
) {
    Rage(R.string.rage, R.drawable.test_soft_flower, EmotionType.ExitedBadly),
    Voltage(R.string.Voltage, R.drawable.test_soft_flower, EmotionType.ExitedBadly),
    Envy(R.string.Envy, R.drawable.test_soft_flower, EmotionType.ExitedBadly),
    Anxiety(R.string.Anxiety, R.drawable.test_soft_flower, EmotionType.ExitedBadly),

    Excitement(R.string.Excitement, R.drawable.test_soft_flower, EmotionType.ExcitedWell),
    Confidence(R.string.Confidence, R.drawable.test_soft_flower, EmotionType.ExcitedWell),
    Delight(R.string.Delight, R.drawable.test_soft_flower, EmotionType.ExcitedWell),
    Happiness(R.string.Happiness, R.drawable.test_soft_flower, EmotionType.ExcitedWell),

    Burnout(R.string.Burnout, R.drawable.test_soft_flower, EmotionType.CalmBadly),
    Fatigue(R.string.Fatigue, R.drawable.test_soft_flower, EmotionType.CalmBadly),
    Depression(R.string.Depression, R.drawable.test_soft_flower, EmotionType.CalmBadly),
    Apathy(R.string.Apathy, R.drawable.test_soft_flower, EmotionType.CalmBadly),

    Calmness(R.string.Calmness, R.drawable.test_soft_flower, EmotionType.CalmWell),
    Satisfaction(R.string.Satisfaction, R.drawable.test_soft_flower, EmotionType.CalmWell),
    Gratitude(R.string.Gratitude, R.drawable.test_soft_flower, EmotionType.CalmWell),
    Security(R.string.Security, R.drawable.test_soft_flower, EmotionType.CalmWell),
}
