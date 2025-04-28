package com.example.mydiary.data.dao

import androidx.room.Dao

@Dao
class EmotionDao {

    fun getEmotionWithQuestion(id: String):EmotionQ

}