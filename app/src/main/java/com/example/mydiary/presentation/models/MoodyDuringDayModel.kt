package com.example.mydiary.presentation.models

import com.example.mydiary.presentation.adapters.Item

data class MoodyDuringDayModel(
    val timePercent: List<List<Float>>,
    val time: List<Int>,
): Item
