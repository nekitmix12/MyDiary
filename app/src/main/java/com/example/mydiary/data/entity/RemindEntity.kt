package com.example.mydiary.data.entity

import androidx.room.Entity

@Entity
data class RemindEntity(
    val id:String,
    val time: String,
)
