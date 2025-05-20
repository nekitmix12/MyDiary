package com.example.mydiary.presentation.features.statistics

import androidx.lifecycle.ViewModel
import com.example.mydiary.domain.usecase.GetSettingsUseCase
import javax.inject.Inject

class StatisticViewModel @Inject constructor(private val getSettingsUseCase: GetSettingsUseCase,) : ViewModel() {
}