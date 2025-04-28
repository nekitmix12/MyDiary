package com.example.mydiary.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["emotionId", "answerId"])
data class AnswerQuestionCrossRef(
    val emotionId: String,
    val answerId: String,
    val isActive: Boolean,
)