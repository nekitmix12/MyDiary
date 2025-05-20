package com.example.mydiary.domain.usecase.emotions

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.QuestionModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllQuestionsUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool
    configuration: Configuration,
) :
    UseCase<GetAllQuestionsUseCase.Request, GetAllQuestionsUseCase.Response>(configuration) {

    data class Response(val questions: List<QuestionModel>) : UseCase.Response
    class Request : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.getAllQuestions())
    }.map { Response(it) }

}