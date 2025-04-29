package com.example.mydiary.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mydiary.domain.model.EmotionModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

class StatisticCalculatorImpl : StatisticCalculator {

    override fun getCountOfRemind(emotions: List<EmotionModel>) = emotions.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getStreak(emotions: List<EmotionModel>): Int {
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        var counter = 0
        var tempDate = ZonedDateTime.now()
        for (emotion in emotions.sortedBy { it.createDataTime }) {
            val emotionTime = ZonedDateTime.parse(emotion.createDataTime, formatter)
            val diff = ChronoUnit.DAYS.between(
                tempDate,
                emotionTime
            )
            if (abs(diff) == 1L) {
                counter++
                tempDate = emotionTime
            } else
                break
        }
        return counter
    }
}