package com.example.mydiary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity("question", indices = [Index(value = ["text"], unique = true)])
data class QuestionEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("text") val text: String,
)