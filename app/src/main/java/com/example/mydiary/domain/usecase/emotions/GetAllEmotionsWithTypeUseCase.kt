package com.example.mydiary.domain.usecase.emotions

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.example.mydiary.R
import com.example.mydiary.di.DefaultPool
import com.example.mydiary.domain.model.FeelType
import com.example.mydiary.domain.model.PositiveType
import com.example.mydiary.domain.usecase.UseCase
import com.example.mydiary.presentation.models.Emotion
import com.example.mydiary.presentation.models.EmotionElementModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllEmotionsWithTypeUseCase @Inject constructor(
    private val context: Context,
    @DefaultPool configuration: Configuration,
) : UseCase<GetAllEmotionsWithTypeUseCase.Request, GetAllEmotionsWithTypeUseCase.Response>(
    configuration
) {

    data class Response(val emotions: List<List<EmotionElementModel>>) : UseCase.Response
    class Request : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(getEmotions())
    }.map { Response(it) }

    private fun getEmotions(): List<List<EmotionElementModel>> {
        val feelTypes = FeelType.entries.toList()
        val positiveTypes = PositiveType.entries.toList()

        val result = mutableListOf<List<EmotionElementModel>>()
        val groupedByType = Emotion.entries.map {
            EmotionElementModel(
                emotion = context.getString(it.emotion),
                description = context.getString(it.description),
                color = context.getColor(it.type.color),
                form = AppCompatResources.getDrawable(
                    context, R.drawable.test_ellipse221
                ) ?: throw IllegalArgumentException("problem in Add logs"),
                formAfter = AppCompatResources.getDrawable(context, it.iconRes)
                    ?: throw IllegalArgumentException("problem in Add logs"),
                emotionEnum = it
            )
        }.groupBy { emotion ->
            Pair(emotion.emotionEnum.type.positiveType, emotion.emotionEnum.type.feelType)
        }
        positiveTypes.forEach { feelType ->
            feelTypes.chunked(2) { pair ->
                val firstGroup = groupedByType[Pair(feelType, pair[0])] ?: emptyList()
                val secondGroup =
                    groupedByType[Pair(feelType, pair.getOrElse(1) { pair[0] })] ?: emptyList()

                val (firstHalf, secondHalf) = firstGroup.splitHalf()
                val (secondGroupFirstHalf, secondGroupSecondHalf) = secondGroup.splitHalf()

                result.add(firstHalf + secondGroupFirstHalf)
                result.add(secondHalf + secondGroupSecondHalf)
            }
        }
        result.reverse()
        return result
    }

    private fun <T> List<T>.splitHalf(): Pair<List<T>, List<T>> {
        val midpoint = (size + 1) / 2
        return Pair(take(midpoint), drop(midpoint))
    }
}