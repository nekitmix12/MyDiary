package com.example.mydiary.presentation.models

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import com.example.mydiary.domain.model.EmotionModel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun EmotionModel.toEmotionCardModel(context: Context): EmotionCardModel {
    val zoneId = ZoneId.systemDefault()
    val zonedDateTime = createDataTime.atZone(zoneId)
    val date = zonedDateTime.toLocalDate()
    val time = zonedDateTime.toLocalTime()

    val today = LocalDate.now(zoneId)
    val startOfWeek = today.with(java.time.DayOfWeek.MONDAY)
    val endOfWeek = today.with(java.time.DayOfWeek.SUNDAY)

    val emotionDate = if (date in startOfWeek..endOfWeek) {
        val formatter = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
        date.format(formatter)
    } else {
        val formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy", Locale.getDefault())
        date.format(formatter)
    }
    Log.d("parce", emotion.type.color.toString())
    return EmotionCardModel(
        id = id,
        background = AppCompatResources.getDrawable(
            context, emotion.type.background
        ) ?: throw IllegalArgumentException("Not found Drawable"),
        date = emotionDate,
        time = time.format(DateTimeFormatter.ofPattern("HH:mm")),
        emotion = context.getString(emotion.emotion),
        emotionColor = context.getColor(emotion.type.color),
        icon = AppCompatResources.getDrawable(
            context, emotion.iconRes
        ) ?: throw IllegalArgumentException("Not found Drawable"),
        emotionModel = this
    )
}
