package com.example.mydiary.presentation.features.settings

import androidx.lifecycle.ViewModel
import com.example.mydiary.domain.usecase.GetSettingsUseCase
import javax.inject.Inject

class SettingViewModel @Inject constructor(private val getSettingsUseCase: GetSettingsUseCase,) : ViewModel() {
}