package com.example.mydiary.domain.usecase.emotions

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEmotionByIdUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool configuration: Configuration,
) : UseCase<GetEmotionByIdUseCase.Request, GetEmotionByIdUseCase.Response>(configuration) {

    data class Response(val emotion: EmotionModel) : UseCase.Response
    class Request(val emotionId: String) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.getEmotionById(request.emotionId))
    }.map { Response(it) }

}