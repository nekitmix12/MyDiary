package com.example.mydiary.data.data_source.local

import com.example.mydiary.Settings
import com.example.mydiary.data.entity.RemindEntity
import com.example.mydiary.data.local_model.SettingLocalModel
import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
   fun getSettings(): Flow<Settings>

    suspend fun deleteImagePath()

    suspend fun changeSettings(settings: SettingLocalModel)

}