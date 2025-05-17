package com.example.mydiary.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mydiary.domain.model.EmotionModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.abs

class StatisticCalculatorImpl @Inject constructor() : StatisticCalculator {

    override fun getCountOfRemind(emotions: List<EmotionModel>) = emotions.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getStreak(emotions: List<EmotionModel>): Int {
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        var counter = 0
        var tempDate = ZonedDateTime.now()
        for (emotion in emotions.sortedBy { it.createDataTime }) {
            val emotionTime = ZonedDateTime.parse(emotion.createDataTime.toString(), formatter)
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

    override fun getCircleParam(
        emotions: List<EmotionModel>,
    ): Pair<List<Pair<Pair<Int, Int>, Float>>,
            Boolean> {
        val groupedEmotions = emotions.groupBy { it.emotion.type }
        val totalCount = emotions.size.toFloat()

        val strikes = groupedEmotions.map { (_, list) ->
            val type = list.first().emotion.type
            val count = list.size.toFloat()
            val ratio = count / totalCount

            (type.gradientStart to type.gradientEnd) to ratio
        }.toList()
        return strikes to emotions.isNotEmpty()
    }
}