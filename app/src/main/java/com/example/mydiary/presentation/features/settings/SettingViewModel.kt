package com.example.mydiary.presentation.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.usecase.ChangeSettingsUseCase
import com.example.mydiary.domain.usecase.GetAllRemindsUseCase
import com.example.mydiary.domain.usecase.GetSettingsUseCase
import com.example.mydiary.presentation.models.RemindModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val changeSettingsUseCase: ChangeSettingsUseCase,
    private val getAllRemindsUseCase: GetAllRemindsUseCase
) : ViewModel() {
    private var _settings = MutableStateFlow<SettingsModel?>(null)
    val settings: StateFlow<SettingsModel?> = _settings

    private var _reminds = MutableStateFlow<List<RemindModel>?>(null)
    val reminds: StateFlow<List<RemindModel>?> = _reminds
    fun getSettings() {
        viewModelScope.launch {
            getSettingsUseCase.execute(GetSettingsUseCase.Request()).collect {
                when (it) {
                    is Result.Success -> _settings.emit(it.data.setting)
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
                    is Result.Success -> _reminds.emit(it.data.reminds)
                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    fun changeSettings(settingsModel: SettingsModel) {
        viewModelScope.launch {
            changeSettingsUseCase.execute(ChangeSettingsUseCase.Request(settingsModel))
                .collect {
                    when (it) {
                        is Result.Success -> _settings.emit(settingsModel)
                        is Result.Error -> {}
                        is Result.Loading -> {}
                    }
                }
        }
    }
}