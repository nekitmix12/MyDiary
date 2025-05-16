package com.example.mydiary.domain.usecase

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeleteEmotionUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool
    configuration: Configuration,
) :
    UseCase<DeleteEmotionUseCase.Request, DeleteEmotionUseCase.Response>(configuration) {

    class Response : UseCase.Response
    data class Request(val emotion: EmotionModel) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.deleteEmotion(request.emotion))
    }
        .map { Response() }

}