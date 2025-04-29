package com.example.mydiary.data.dbo

import com.example.mydiary.data.entity.EmotionEntity

data class EmotionWithDetailsDbo(
    val emotionEntity: EmotionEntity,
    val list: List<AnswerWithActive>,
)