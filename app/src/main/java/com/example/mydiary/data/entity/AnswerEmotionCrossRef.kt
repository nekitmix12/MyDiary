package com.example.mydiary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "answer_emotion",
    primaryKeys = ["emotion_id", "answer_id"],
    foreignKeys = [
        ForeignKey(
            entity = EmotionEntity::class,
            parentColumns = ["id"],
            childColumns = ["emotion_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnswerEntity::class,
            parentColumns = ["id"],
            childColumns = ["answer_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnswerEmotionCrossRef(
    @ColumnInfo("emotion_id")val emotionId: String,
    @ColumnInfo("answer_id") val answerId: String,
    @ColumnInfo("is_active")val isActive: Boolean,
)