package com.example.mydiary.domain.usecase

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeleteImagePathUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool configuration: Configuration,
) : UseCase<DeleteImagePathUseCase.Request, DeleteImagePathUseCase.Response>(configuration) {

    class Response : UseCase.Response
    class Request : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.deleteImagePath())
    }.map { Response() }
}