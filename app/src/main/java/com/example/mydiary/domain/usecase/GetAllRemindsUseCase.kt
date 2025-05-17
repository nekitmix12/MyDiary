package com.example.mydiary.domain.usecase

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.presentation.models.RemindModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllRemindsUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool configuration: Configuration,
) : UseCase<GetAllRemindsUseCase.Request, GetAllRemindsUseCase.Response>(configuration) {

    data class Response(val reminds: List<RemindModel>) : UseCase.Response
    class Request : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.getAllRemind())
    }.map { Response(it) }

}