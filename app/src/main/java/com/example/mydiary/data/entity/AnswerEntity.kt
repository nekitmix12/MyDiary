package com.example.mydiary.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnswerEntity(
    @PrimaryKey val id: String,
    val text: String,
    val questionId: String,
)