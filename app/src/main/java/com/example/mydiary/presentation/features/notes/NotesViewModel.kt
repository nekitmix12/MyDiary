package com.example.mydiary.presentation.features.notes

import androidx.lifecycle.ViewModel
import com.example.mydiary.domain.usecase.GetSettingsUseCase
import javax.inject.Inject

class NotesViewModel @Inject constructor(private val getSettingsUseCase: GetSettingsUseCase,) : ViewModel() {
}