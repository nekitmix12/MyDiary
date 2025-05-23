package com.example.mydiary.domain.usecase.emotions

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EditAnswerUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool
    configuration: Configuration,
) :
    UseCase<EditAnswerUseCase.Request, EditAnswerUseCase.Response>(configuration) {

    class Response : UseCase.Response
    data class Request(val answerModel: AnswerModel) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.editAnswer(request.answerModel))
    }.map { Response() }

}