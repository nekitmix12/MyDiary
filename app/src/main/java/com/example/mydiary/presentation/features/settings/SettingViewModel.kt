package com.example.mydiary.presentation.features.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.usecase.settings.AddRemindUseCase
import com.example.mydiary.domain.usecase.settings.ChangeSettingsUseCase
import com.example.mydiary.domain.usecase.settings.DeleteRemindUseCase
import com.example.mydiary.domain.usecase.settings.GetAllRemindsUseCase
import com.example.mydiary.domain.usecase.settings.GetSettingsUseCase
import com.example.mydiary.presentation.models.RemindModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val changeSettingsUseCase: ChangeSettingsUseCase,
    private val getAllRemindsUseCase: GetAllRemindsUseCase,
    private val deleteRemindUseCase: DeleteRemindUseCase,
    private val addRemindUseCase: AddRemindUseCase
) : ViewModel() {
    private var _settings = MutableStateFlow<SettingsModel?>(null)
    val settings: StateFlow<SettingsModel?> = _settings

    private var _reminds = MutableStateFlow<List<RemindModel>?>(null)
    val reminds: StateFlow<List<RemindModel>?> = _reminds
    fun getSettings() {
        viewModelScope.launch {
            getSettingsUseCase.execute(GetSettingsUseCase.Request()).collect {
                when (it) {
                    is Result.Success -> {
                        _settings.emit(it.data.setting)
                        Log.d(TAG, it.data.setting.toString())
                    }

                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    fun getAllReminds() {
        viewModelScope.launch {
            getAllRemindsUseCase.execute(GetAllRemindsUseCase.Request()).collect {
                when (it) {
                    is Result.Success -> {
                        _reminds.emit(it.data.reminds)
                        Log.d(TAG, it.data.reminds.toString())

                    }

                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    fun changeSettings(settingsModel: SettingsModel) {
        Log.d(
            TAG, "change settings $settingsModel"
        )
        viewModelScope.launch {
            changeSettingsUseCase.execute(ChangeSettingsUseCase.Request(settingsModel))
                .collect {
                    when (it) {
                        is Result.Success -> {}
                        is Result.Error -> {
                            Log.e(TAG, it.exception)
                        }

                        is Result.Loading -> {}
                    }
                }
        }
    }

    fun deleteRemind(remindModel: RemindModel) {
        viewModelScope.launch {
            deleteRemindUseCase.execute(DeleteRemindUseCase.Request(remindModel))
                .collect { remind ->
                    when (remind) {
                        is Result.Success -> getAllReminds()
                        is Result.Error -> {
                            Log.e(TAG, remind.exception)
                        }

                        is Result.Loading -> {}
                    }
                }
        }
    }

    fun createRemind(date: String) {
        if (date != ":")
            viewModelScope.launch {
                addRemindUseCase.execute(
                    AddRemindUseCase.Request(
                        RemindModel(
                            UUID.randomUUID().toString(), date
                        )
                    )
                ).collect {
                    when (it) {
                        is Result.Success -> getAllReminds()
                        is Result.Error -> {
                            Log.e(TAG, it.exception)
                        }

                        is Result.Loading -> {}
                    }
                }
            }
    }

    companion object {
        private const val TAG = "SettingViewModel"
    }
}