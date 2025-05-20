package com.example.mydiary.domain.usecase.emotions

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAnswersUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool
    configuration: Configuration,
) :
    UseCase<GetAnswersUseCase.Request, GetAnswersUseCase.Response>(configuration) {

    data class Response(val answers: List<AnswerModel>) : UseCase.Response
    class Request : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.getAnswers())
    }.map { Response(it) }

}