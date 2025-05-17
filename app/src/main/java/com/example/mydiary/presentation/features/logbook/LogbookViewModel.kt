package com.example.mydiary.presentation.features.logbook

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.usecase.GetAllEmotionsUseCase
import com.example.mydiary.domain.usecase.GetCircleParamUseCase
import com.example.mydiary.domain.usecase.GetLogbookStatisticsUseCase
import com.example.mydiary.presentation.models.CircleButton
import com.example.mydiary.presentation.models.LogbookScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogbookViewModel @Inject constructor(
    private val getAllEmotionsUseCase: GetAllEmotionsUseCase,
    private val getLogbookStatisticsUseCase: GetLogbookStatisticsUseCase,
    private val getCircleParamUseCase: GetCircleParamUseCase,
) : ViewModel() {

    private var _screenModel = MutableStateFlow<Result<LogbookScreenModel>>(Result.Loading)
    val screenModel: StateFlow<Result<LogbookScreenModel>> = _screenModel


    private val emotions = MutableStateFlow<List<EmotionModel>?>(null)
    private val logsCountAndLogsStreak = MutableStateFlow<Pair<Int, Int>?>(null)
    private val circleParam =
        MutableStateFlow<Pair<List<Pair<Pair<Int, Int>, Float>>, Boolean>?>(null)


    fun loadScreen(context: Context) {
        viewModelScope.launch {
            launch {
                launchEmotions()
            }
            launch {
                getStatistics()
            }
            launch {
                getCircleParam()
            }
            launch {
                getScreenModel()
            }
        }
    }

    private suspend fun getScreenModel() {
        combine(emotions, logsCountAndLogsStreak, circleParam) { value1, value2, value3 ->
            Triple(value1, value2, value3)
        }.collect {
            Log.d(TAG, "${it.first} <- first; ${it.second} <- second; ${it.third} <- third")
            if (it.first != null && it.second != null && it.third != null) _screenModel.value =
                Result.Success(
                    LogbookScreenModel(
                        logsCount = it.second!!.first,
                        logsStreak = it.second!!.second,
                        loadingEmotions = CircleButton(it.third!!.first, it.third!!.second),
                        emotions = it.first!!,
                    )
                )
        }
    }

    private suspend fun getCircleParam() {
        emotions.collect {
            if (emotions.value != null) getCircleParamUseCase.execute(
                GetCircleParamUseCase.Request(
                    emotions.value!!
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        circleParam.value = it.data.data
                        Log.d(TAG, it.data.data.toString())
                    }

                    is Result.Error -> {
                        _screenModel.value = Result.Error(it.exception)
                        Log.d(TAG, it.exception)

                    }

                    is Result.Loading -> {}
                }
            }
        }
    }

    private suspend fun launchEmotions() {
        getAllEmotionsUseCase.execute(GetAllEmotionsUseCase.Request()).collect {
            when (it) {
                is Result.Success -> {
                    emotions.value = it.data.emotions
                    Log.d(TAG, it.data.emotions.toString())

                }

                is Result.Error -> {
                    _screenModel.value = Result.Error(it.exception)
                    Log.d(TAG, it.exception)
                }

                is Result.Loading -> {}
            }
        }
    }

    private suspend fun getStatistics() {
        emotions.collect {
            if (emotions.value != null) getLogbookStatisticsUseCase.execute(
                GetLogbookStatisticsUseCase.Request(
                    emotions.value!!
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        logsCountAndLogsStreak.value =
                            it.data.logbookStatisticModel.logsCount to it.data.logbookStatisticModel.logsStreak
                        Log.d(TAG, it.data.logbookStatisticModel.toString())
                    }

                    is Result.Error -> {
                        _screenModel.value = Result.Error(it.exception)
                        Log.d(TAG, it.exception)
                    }

                    is Result.Loading -> {}
                }
            }
        }
    }

    companion object {
        const val TAG = "LogbookViewModel"
    }
}