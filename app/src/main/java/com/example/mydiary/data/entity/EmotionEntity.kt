package com.example.mydiary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("emotion")
data class EmotionEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "emotion") val emotion: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "create_data_time") val createDataTime: String,
    @ColumnInfo(name = "image") val imageRes: Int,
)