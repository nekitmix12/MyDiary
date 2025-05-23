package com.example.mydiary.data.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.example.mydiary.data.entity.AnswerEntity

data class AnswerWithActiveDbo(
    @Embedded(prefix = "ans_") val answer: AnswerEntity,
    val isActive: Boolean,
    @ColumnInfo("question_id")val questionId: String,
)