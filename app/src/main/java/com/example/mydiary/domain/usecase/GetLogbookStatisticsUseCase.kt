package com.example.mydiary.domain.usecase

import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.LogbookStatisticModel
import com.example.mydiary.domain.utils.StatisticCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetLogbookStatisticsUseCase(
    private val calculator: StatisticCalculator,
    configuration: Configuration,
) :
    UseCase<GetLogbookStatisticsUseCase.Request, GetLogbookStatisticsUseCase.Response>(configuration) {
    data class Response(val logbookStatisticModel: LogbookStatisticModel) : UseCase.Response
    data class Request(val emotions: List<EmotionModel>) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(
            LogbookStatisticModel(
                calculator.getCountOfRemind(request.emotions),
                calculator.getStreak(request.emotions)
            )
        )
    }.map { Response(it) }
}