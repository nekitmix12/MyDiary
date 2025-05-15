package com.example.mydiary.data.data_source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.mydiary.Settings
import com.example.mydiary.data.local_model.SettingLocalModel
import com.example.mydiary.data.serializer.SettingsSerializer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreManager @Inject constructor(private val context: Context) : SettingsDataSource {


    private val Context.settingStore: DataStore<Settings> by dataStore(
        fileName = DATA_STORE_FILE_NAME, serializer = SettingsSerializer
    )


    override suspend fun getSettings(): Flow<Settings> {
        return context.settingStore.data
    }

    override suspend fun changeSettings(settings: SettingLocalModel) {
        context.settingStore.updateData { preferences ->
            preferences.toBuilder().setUrl(settings.imageUrl).setName(settings.name)
                .setIsSendRemindOn(settings.isSendRemindOn)
                .setIsUseFingerprint(settings.isUseFingerprint).build()
        }
    }


    override suspend fun deleteImagePath() {
        context.settingStore.updateData { preferences ->
            preferences.toBuilder().setUrl(null).build()
        }
    }

    companion object {
        private const val SETTINGS_NAME = "settings"
        private const val DATA_STORE_FILE_NAME = "settings.pb"
    }
}