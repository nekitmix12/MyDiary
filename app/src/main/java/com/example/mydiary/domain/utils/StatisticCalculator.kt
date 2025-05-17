package com.example.mydiary.domain.utils

import com.example.mydiary.domain.model.CircleButtonModel
import com.example.mydiary.domain.model.EmotionModel

interface StatisticCalculator {

    fun getCountOfRemind(emotions: List<EmotionModel>): Int

    fun getStreak(emotions: List<EmotionModel>): Int

    fun getCircleParam(emotions: List<EmotionModel>):Pair<List<Pair<Pair<Int, Int>, Float>>,
            Boolean>
}