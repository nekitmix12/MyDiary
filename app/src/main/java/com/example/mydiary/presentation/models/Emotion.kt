package com.example.mydiary.presentation.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mydiary.R
import com.example.mydiary.domain.model.EmotionType

enum class Emotion(
    @StringRes val emotion: Int,
    @DrawableRes val iconRes: Int,
    @StringRes val description:Int,
    val type: EmotionType,
) {
    Rage(R.string.rage, R.drawable.test_soft_flower,R.string.rageDepression, EmotionType.ExitedBadly),
    Voltage(R.string.Voltage, R.drawable.test_soft_flower,R.string.VoltageDepression, EmotionType.ExitedBadly),
    Envy(R.string.Envy, R.drawable.test_soft_flower,R.string.EnvyDescription, EmotionType.ExitedBadly),
    Anxiety(R.string.Anxiety, R.drawable.test_soft_flower,R.string.AnxietyDescription, EmotionType.ExitedBadly),

    Excitement(R.string.Excitement, R.drawable.test_soft_flower,R.string.ExcitementDescription, EmotionType.ExcitedWell),
    Confidence(R.string.Confidence, R.drawable.test_soft_flower,R.string.ConfidenceDescription, EmotionType.ExcitedWell),
    Delight(R.string.Delight, R.drawable.test_soft_flower,R.string.DelightDescription, EmotionType.ExcitedWell),
    Happiness(R.string.Happiness, R.drawable.test_soft_flower,R.string.HappinessDescription, EmotionType.ExcitedWell),

    Burnout(R.string.Burnout, R.drawable.test_soft_flower,R.string.BurnoutDescription, EmotionType.CalmBadly),
    Fatigue(R.string.Fatigue, R.drawable.test_soft_flower,R.string.FatigueDescription, EmotionType.CalmBadly),
    Depression(R.string.Depression, R.drawable.test_soft_flower,R.string.DepressionDescription, EmotionType.CalmBadly),
    Apathy(R.string.Apathy, R.drawable.test_soft_flower,R.string.ApathyDescription, EmotionType.CalmBadly),

    Calmness(R.string.Calmness, R.drawable.test_soft_flower,R.string.CalmnessDescription, EmotionType.CalmWell),
    Satisfaction(R.string.Satisfaction, R.drawable.test_soft_flower,R.string.SatisfactionDescription, EmotionType.CalmWell),
    Gratitude(R.string.Gratitude, R.drawable.test_soft_flower,R.string.GratitudeDescription, EmotionType.CalmWell),
    Security(R.string.Security, R.drawable.test_soft_flower,R.string.SecurityDescription, EmotionType.CalmWell),
}
