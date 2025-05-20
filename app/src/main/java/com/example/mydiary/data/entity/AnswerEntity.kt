package com.example.mydiary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("answer")
data class AnswerEntity(
    @PrimaryKey val id: String,
    val text: String,
    @ColumnInfo("question_id")val questionId: String,
)