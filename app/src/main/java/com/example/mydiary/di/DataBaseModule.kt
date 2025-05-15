package com.example.mydiary.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydiary.data.AppDataBase
import com.example.mydiary.data.dao.EmotionDao
import com.example.mydiary.data.dao.EmotionDao_Impl
import com.example.mydiary.data.dao.RemindDao
import com.example.mydiary.data.dao.RemindDao_Impl
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    fun dataBase(context: Context): RoomDatabase =
        Room.databaseBuilder(context, AppDataBase::class.java, "app_database").build()

    @Provides
    fun provideEmotionDao(roomDatabase: RoomDatabase): EmotionDao =
        EmotionDao_Impl(
            roomDatabase
        )

    @Provides
    fun provideRemindDao(roomDatabase: RoomDatabase): RemindDao =
        RemindDao_Impl(
            roomDatabase
        )
}