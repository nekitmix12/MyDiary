package com.example.mydiary.domain.usecase.emotions

import com.example.mydiary.di.DefaultPool
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OverlayAnswerUseCase @Inject constructor(
    @DefaultPool
    configuration: Configuration,
) :
    UseCase<OverlayAnswerUseCase.Request, OverlayAnswerUseCase.Response>(configuration) {

    data class Response(val emotions: List<AnswerWithStateModel>) :
        UseCase.Response

    data class Request(
        val oldAnswer: List<AnswerWithStateModel>,
        val allAnswer: List<AnswerModel>
    ) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(getCorrectEmotions(request.oldAnswer, request.allAnswer))
    }.map { Response(it) }

    private fun getCorrectEmotions(
        oldAnswer: List<AnswerWithStateModel>,
        allAnswer: List<AnswerModel>
    ): List<AnswerWithStateModel> {
        val result = mutableListOf<AnswerWithStateModel>()
        val oldAnswerMap = oldAnswer.groupBy { it.answer }
        allAnswer.forEach {
            if (oldAnswerMap[it] != null) {
                result.add(oldAnswerMap[it]!![0])
            } else
                result.add(AnswerWithStateModel(it, false, it.questionId))
        }

        return result
    }
}