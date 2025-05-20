package com.example.mydiary.presentation.features.notes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.QuestionModel
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.usecase.emotions.GetAllQuestionsUseCase
import com.example.mydiary.domain.usecase.emotions.GetAnswersUseCase
import com.example.mydiary.domain.usecase.emotions.GetAnswersWithActiveUseCase
import com.example.mydiary.domain.usecase.emotions.GetEmotionByIdUseCase
import com.example.mydiary.domain.usecase.emotions.OverlayAnswerUseCase
import com.example.mydiary.presentation.models.EmotionCardModel
import com.example.mydiary.presentation.models.QuestionBlockModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val getAnswersWithActiveUseCase: GetAnswersWithActiveUseCase,
    private val getAllQuestionsUseCase: GetAllQuestionsUseCase,
    private val overlayAnswerUseCase: OverlayAnswerUseCase,
    private val getAnswersUseCase: GetAnswersUseCase,
    private val getEmotionByIdUseCase: GetEmotionByIdUseCase,
) : ViewModel() {
    private var answersWithState = MutableStateFlow<List<AnswerWithStateModel>?>(null)

    private var answers = MutableStateFlow<List<AnswerModel>?>(null)

    private var questions = MutableStateFlow<List<QuestionModel>?>(null)

    private var _questionBlocks = MutableStateFlow<List<QuestionBlockModel>?>(null)
    val questionBlocks: StateFlow<List<QuestionBlockModel>?> = _questionBlocks

    private var _emotion = MutableStateFlow<EmotionCardModel?>(null)
    val emotion: StateFlow<EmotionCardModel?> = _emotion


    private fun getAnswers(emotionsId: String) {
        viewModelScope.launch {
            getAnswersWithActiveUseCase.execute(GetAnswersWithActiveUseCase.Request(emotionsId))
                .collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> answersWithState.emit(it.data.answers)
                        is Result.Error -> {}
                    }
                }
        }
    }

    private fun getQuestions() {
        viewModelScope.launch {
            getAllQuestionsUseCase.execute(GetAllQuestionsUseCase.Request()).collect {
                when (it) {
                    is Result.Success -> questions.emit(it.data.questions)
                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    private fun getOverlay(
        allAnswers: List<AnswerModel>,
        emotionAnswers: List<AnswerWithStateModel>
    ) {
        viewModelScope.launch {
            overlayAnswerUseCase.execute(
                OverlayAnswerUseCase.Request(
                    emotionAnswers,
                    allAnswers
                )
            ).collect {
                when (it) {
                    is Result.Loading -> {}
                    is Result.Success -> answersWithState.emit(it.data.emotions)
                    is Result.Error -> {}
                }
            }
        }
    }

    private fun getAllAnswer() {
        viewModelScope.launch {
            getAnswersUseCase.execute(GetAnswersUseCase.Request()).collect {
                when (it) {
                    is Result.Success -> answers.emit(it.data.answers)
                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    private fun getAnswer() {
        viewModelScope.launch {
            getAnswersUseCase.execute(GetAnswersUseCase.Request()).collect {
                when (it) {
                    is Result.Success -> answersWithState.emit(it.data.answers.map { answersResponse ->
                        AnswerWithStateModel(
                            answer = answersResponse,
                            state = false,
                            questionId = answersResponse.questionId
                        )
                    })

                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }


    fun createQuestionBlocks(emotionsId: String?, context: Context) {
        getQuestions()
        if (emotionsId != null) {
            getAnswers(emotionsId)
            getAllAnswer()
            viewModelScope.launch {
                combine(answers, answersWithState) { answers, answersWithState ->
                    answers to answersWithState
                }.collect {
                    if (it.first != null && it.second != null) {
                        getOverlay(it.first!!, it.second!!)
                    }
                }
            }
        } else {
            getAnswer()
        }
        viewModelScope.launch {
            combine(answersWithState, questions) { first, second ->
                first to second
            }.collect {
                if (it.first != null && it.second != null) {
                    val newBlocks = mutableListOf<QuestionBlockModel>()
                    for (question in it.second!!) {
                        newBlocks.add(
                            QuestionBlockModel(
                                label = context.getString(question.ref),
                                answers = it.first!!.map { answer ->
                                    com.example.mydiary.presentation.models.AnswerModel(
                                        id = UUID.fromString(answer.answer.id),
                                        text = answer.answer.text,
                                        selected = answer.state
                                    )
                                }
                            )
                        )
                    }
                    _questionBlocks.emit(newBlocks)
                }
            }
        }

    }


    fun getEmotion(emotion: String,emotionId:String?) {
        if (emotionId != null) {

        } else {

        }
    }

}