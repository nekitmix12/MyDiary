package com.example.mydiary.presentation.features.add_emotion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.usecase.emotions.GetAllEmotionsWithTypeUseCase
import com.example.mydiary.presentation.models.EmotionElementModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEmotionViewModel @Inject constructor(private val getAllEmotionsWithTypeUseCase: GetAllEmotionsWithTypeUseCase) :
    ViewModel() {
    private var _emotions = MutableStateFlow<List<List<EmotionElementModel>>?>(null)
    val emotions: StateFlow<List<List<EmotionElementModel>>?> = _emotions

    fun getEmotions() {
        viewModelScope.launch {
            getAllEmotionsWithTypeUseCase.execute(GetAllEmotionsWithTypeUseCase.Request())
                .collect {
                    when (it) {
                        is Result.Success -> _emotions.emit(it.data.emotions)
                        is Result.Error -> {}
                        is Result.Loading -> {}
                    }
                }
        }
    }

}