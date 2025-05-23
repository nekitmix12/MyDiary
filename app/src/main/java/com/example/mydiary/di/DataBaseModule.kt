package com.example.mydiary.di

import android.content.ContentValues
import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mydiary.R
import com.example.mydiary.data.AppDataBase
import com.example.mydiary.data.dao.EmotionDao
import com.example.mydiary.data.dao.RemindDao
import dagger.Module
import dagger.Provides
import java.util.UUID

@Module
class DataBaseModule {
    private val roomAssetsQuestions = listOf(
        UUID.randomUUID().toString() to R.string.what_were_you_doing,
        UUID.randomUUID().toString() to R.string.who_were_you_with,
        UUID.randomUUID().toString() to R.string.where_have_you_been
    )

    private val roomAssetsAnswers = listOf(
        roomAssetsQuestions[0] to R.string.food_intake,
        roomAssetsQuestions[0] to R.string.friends_meeting,
        roomAssetsQuestions[0] to R.string.hobby,
        roomAssetsQuestions[0] to R.string.trip,

        roomAssetsQuestions[1] to R.string.alon,
        roomAssetsQuestions[1] to R.string.friends,
        roomAssetsQuestions[1] to R.string.family,
        roomAssetsQuestions[1] to R.string.pet,

        roomAssetsQuestions[2] to R.string.home,
        roomAssetsQuestions[2] to R.string.work,
        roomAssetsQuestions[2] to R.string.outdoor,
        roomAssetsQuestions[2] to R.string.park,
    )


    @Provides
    fun dataBase(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "app_database")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    roomAssetsQuestions.forEach { (uuid, resId) ->
                        val cv = ContentValues().apply {
                            put("id", uuid)
                            put("text", context.getString(resId))
                        }
                        db.insert("question", OnConflictStrategy.IGNORE, cv)
                    }

                    roomAssetsAnswers.forEach { (questionPair, resId) ->
                        val answerId = UUID.randomUUID().toString()
                        val cv = ContentValues().apply {
                            put("id", answerId)
                            put("text", context.getString(resId))
                            put("question_id", questionPair.first)
                        }
                        db.insert("answer", OnConflictStrategy.IGNORE, cv)
                    }
                }
            }).build()

    @Provides
    fun provideEmotionDao(roomDatabase: AppDataBase): EmotionDao = roomDatabase.emotionDao()

    @Provides
    fun provideRemindDao(roomDatabase: AppDataBase): RemindDao = roomDatabase.remindDao()
}