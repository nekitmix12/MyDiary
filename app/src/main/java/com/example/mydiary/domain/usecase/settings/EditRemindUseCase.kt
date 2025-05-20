package com.example.mydiary.domain.usecase.settings

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import com.example.mydiary.presentation.models.RemindModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EditRemindUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool
    configuration: Configuration,
) :
    UseCase<EditRemindUseCase.Request, EditRemindUseCase.Response>(configuration) {

    class Response : UseCase.Response
    data class Request(val remindModel: RemindModel) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.editRemind(request.remindModel))
    }
        .map { Response() }

}