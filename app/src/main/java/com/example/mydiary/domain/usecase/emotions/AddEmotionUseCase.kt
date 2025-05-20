package com.example.mydiary.domain.usecase.emotions

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.AnswerEmotionCrossRefModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddEmotionUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool configuration: Configuration,
) : UseCase<AddEmotionUseCase.Request, AddEmotionUseCase.Response>(configuration) {

    class Response : UseCase.Response

    data class Request(
        val emotion: EmotionModel,
        val answerEmotionCrossRef: List<AnswerEmotionCrossRefModel>,
    ) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.addEmotion(request.emotion, request.answerEmotionCrossRef))
    }.map { Response() }
}