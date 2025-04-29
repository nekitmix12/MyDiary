package com.example.mydiary.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.usecase.GetAllEmotionsUseCase
import com.example.mydiary.domain.usecase.GetLogbookStatisticsUseCase
import com.example.mydiary.presentation.models.LogbookScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogbookViewModel(
    private val getAllEmotionsUseCase: GetAllEmotionsUseCase,
    private val getLogbookStatisticsUseCase: GetLogbookStatisticsUseCase,
) : ViewModel() {

    private var _screenModel =
        MutableStateFlow<Result<LogbookScreenModel>>(Result.Loading)
    val screenModel: StateFlow<Result<LogbookScreenModel>> = _screenModel

    private var _error =
        MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    val emotions = MutableStateFlow<List<EmotionModel>>(listOf())

    fun loadScreen() {
        viewModelScope.launch {
            launch {
                emotions.collect {
                    if (it.isNotEmpty())
                        getLogbookStatisticsUseCase.execute(
                            GetLogbookStatisticsUseCase.Request(
                                emotions.value
                            )
                        )
                            .collect {
                                when (it) {
                                    is Result.Success -> {
                                        _screenModel.value =
                                            Result.Success(
                                                LogbookScreenModel(
                                                    logsCount = it.data.logbookStatisticModel.logsCount,
                                                    logsStreak = it.data.logbookStatisticModel.logsStreak,
                                                    emotions = emotions.value
                                                )
                                            )
                                    }

                                    is Result.Error -> {
                                        _error.value = it.exception
                                    }

                                    is Result.Loading -> {}
                                }
                            }

                }

            }
        }
    }
}