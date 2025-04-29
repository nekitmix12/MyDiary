package com.example.mydiary.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydiary.data.dao.EmotionDao
import com.example.mydiary.data.dao.RemindDao
import com.example.mydiary.data.entity.AnswerEntity
import com.example.mydiary.data.entity.AnswerQuestionCrossRef
import com.example.mydiary.data.entity.EmotionEntity
import com.example.mydiary.data.entity.QuestionEntity
import com.example.mydiary.data.entity.RemindEntity

@Database(
    entities = [
        AnswerEntity::class,
        AnswerQuestionCrossRef::class,
        EmotionEntity::class,
        QuestionEntity::class,
        RemindEntity::class
    ], version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun emotionDao(): EmotionDao

    abstract fun remindDao(): RemindDao
}