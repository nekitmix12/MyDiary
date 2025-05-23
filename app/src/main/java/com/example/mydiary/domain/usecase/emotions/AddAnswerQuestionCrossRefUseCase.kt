package com.example.mydiary.domain.usecase.emotions

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.AnswerEmotionCrossRefModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddAnswerQuestionCrossRefUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool
    configuration: Configuration,
) :
    UseCase<AddAnswerQuestionCrossRefUseCase.Request, AddAnswerQuestionCrossRefUseCase.Response>(
        configuration
    ) {

    class Response : UseCase.Response
    data class Request(val answerQuestionsCross: List<AnswerEmotionCrossRefModel>) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.addEmotionAnswerState(request.answerQuestionsCross))
    }.map { Response() }

}