package com.example.mydiary.data.dbo

import androidx.room.Embedded
import com.example.mydiary.data.entity.AnswerEntity

data class AnswerWithActive(
    @Embedded val answer: AnswerEntity,
    val isActive: Boolean
)