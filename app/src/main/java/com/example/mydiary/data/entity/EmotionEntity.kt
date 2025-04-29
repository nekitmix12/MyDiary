package com.example.mydiary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmotionEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "create_data_time") val createDataTime: String,
    @ColumnInfo(name = "image") val imageRes: String,
)