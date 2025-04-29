package com.example.mydiary.domain.usecase

import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetAllEmotionsUseCase(private val repository: Repository, configuration: Configuration) :
    UseCase<GetAllEmotionsUseCase.Request, GetAllEmotionsUseCase.Response>(configuration) {

    data class Response(val emotions: List<EmotionModel>) : UseCase.Response
    class Request : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.getAllEmotions())
    }.map { Response(it) }
}