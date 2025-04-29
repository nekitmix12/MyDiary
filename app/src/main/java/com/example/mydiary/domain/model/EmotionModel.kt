package com.example.mydiary.domain.model

data class EmotionModel(
    val id: String,
    val type: EmotionType,
    val name: String,
    val createDataTime: String,
    val imageRes: String,
)