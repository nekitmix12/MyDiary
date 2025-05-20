package com.example.mydiary.domain.usecase.emotions

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAnswersWithActiveUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool configuration: Configuration,
) : UseCase<GetAnswersWithActiveUseCase.Request, GetAnswersWithActiveUseCase.Response>(configuration) {

    data class Response(val answers: List<AnswerWithStateModel>) : UseCase.Response
    data class Request(val emotionId: String) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.getAnswersWithActive(request.emotionId))
    }.map { Response(it) }

}