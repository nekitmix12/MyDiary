package com.example.mydiary.domain.usecase

import com.example.mydiary.di.DefaultPool
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.utils.StatisticCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCircleParamUseCase @Inject constructor(
    private val calculator: StatisticCalculator,
    @DefaultPool
    configuration: Configuration,
) :
    UseCase<GetCircleParamUseCase.Request, GetCircleParamUseCase.Response>(configuration) {

    data class Response(val data: Pair<List<Pair<Pair<Int, Int>, Float>>, Boolean>) :
        UseCase.Response

    data class Request(val emotions: List<EmotionModel>) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(calculator.getCircleParam(request.emotions))
    }.map { Response(it) }
}