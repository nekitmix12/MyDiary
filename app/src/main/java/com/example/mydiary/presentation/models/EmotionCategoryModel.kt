package com.example.mydiary.presentation.models

import com.example.mydiary.presentation.adapters.Item

data class EmotionCategoryModel(
    val emotionsPercent: List<Pair<Float,Float>>,
): Item
