package com.example.mydiary.data.dbo

import androidx.room.Embedded
import com.example.mydiary.data.entity.AnswerEntity

data class AnswerWithActiveDbo(
    @Embedded val answer: AnswerEntity,
    val isActive: Boolean,
    val questionId: String,
)