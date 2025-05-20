package com.example.mydiary.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("remind")
data class RemindEntity(
    @PrimaryKey val id:String,
    val time: String,
)
