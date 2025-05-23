package com.example.mydiary.presentation.features.notes

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.domain.model.AnswerEmotionCrossRefModel
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.EmotionModel
import com.example.mydiary.domain.model.QuestionModel
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.usecase.emotions.AddAnswerQuestionCrossRefUseCase
import com.example.mydiary.domain.usecase.emotions.AddAnswerUseCase
import com.example.mydiary.domain.usecase.emotions.AddEmotionUseCase
import com.example.mydiary.domain.usecase.emotions.EditAnswerUseCase
import com.example.mydiary.domain.usecase.emotions.GetAllQuestionsUseCase
import com.example.mydiary.domain.usecase.emotions.GetAnswersUseCase
import com.example.mydiary.domain.usecase.emotions.GetAnswersWithActiveUseCase
import com.example.mydiary.domain.usecase.emotions.GetEmotionByIdUseCase
import com.example.mydiary.domain.usecase.emotions.OverlayAnswerUseCase
import com.example.mydiary.presentation.models.Emotion
import com.example.mydiary.presentation.models.EmotionCardModel
import com.example.mydiary.presentation.models.QuestionBlockModel
import com.example.mydiary.presentation.models.toEmotionCardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.UUID
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val getAnswersWithActiveUseCase: GetAnswersWithActiveUseCase,
    private val getAllQuestionsUseCase: GetAllQuestionsUseCase,
    private val overlayAnswerUseCase: OverlayAnswerUseCase,
    private val getAnswersUseCase: GetAnswersUseCase,
    private val getEmotionByIdUseCase: GetEmotionByIdUseCase,
    private val editAnswerUseCase: EditAnswerUseCase,
    private val addAnswerUseCase: AddAnswerUseCase,
    private val addEmotionUseCase: AddEmotionUseCase,
    private val addAnswerQuestionCrossRefUseCase: AddAnswerQuestionCrossRefUseCase
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
        allAnswers: List<AnswerModel>, emotionAnswers: List<AnswerWithStateModel>
    ) {
        viewModelScope.launch {
            overlayAnswerUseCase.execute(
                OverlayAnswerUseCase.Request(
                    emotionAnswers, allAnswers
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


    fun createQuestionBlocks(emotionsId: String?) {
        getQuestions()
        if (emotionsId != null) {
            getAnswers(emotionsId)
            getAllAnswer()
            viewModelScope.launch {
                combine(answers, answersWithState) { answers, answersWithState ->
                    answers to answersWithState
                }.collect {
                    if (it.first != null && it.second != null) {
                        Log.i(TAG, "getOverlay: $answersWithState")
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
                if (it.first != null && it.second != null && it.first!!.isNotEmpty()) {
                    
                    val newBlocks = mutableListOf<QuestionBlockModel>()
                    Log.d(TAG, it.toString())
                    println(it.first)
                    Log.i(TAG, " combine: $answersWithState")

                    for (question in it.second!!) {
                        newBlocks.add(
                            QuestionBlockModel(questionId = question.id, label = question.text,
                                answers = it.first!!.filter { el -> el.questionId == question.id }
                                    .map { answer ->
                                        com.example.mydiary.presentation.models.AnswerModel(
                                            id = answer.answer.id,
                                            text = answer.answer.text,
                                            selected = answer.state
                                        )
                                    })
                        )
                    }
                    _questionBlocks.emit(newBlocks)
                }
            }
        }
    }


    @SuppressLint("NewApi", "UseCompatLoadingForDrawables")
    fun getEmotion(emotion: String, emotionId: String, context: Context) {
        if (emotionId != "") {
            viewModelScope.launch {
                getEmotionByIdUseCase.execute(GetEmotionByIdUseCase.Request(emotionId)).collect {
                    when (it) {
                        is Result.Success -> _emotion.emit(
                            it.data.emotion.toEmotionCardModel(
                                context
                            )
                        )

                        is Result.Error -> {}
                        is Result.Loading -> {}
                    }

                }
            }
        } else {
            val emotionPars = Emotion.valueOf(emotion)
            viewModelScope.launch {
                _emotion.emit(
                    EmotionModel(
                        id = UUID.randomUUID().toString(),
                        emotion = emotionPars,
                        name = emotionPars.name,
                        createDataTime = Instant.now(),
                        imageRes = emotionPars.iconRes
                    ).toEmotionCardModel(context)

                )
            }

        }
    }

    fun onAnswerClick(answerModel: AnswerModel) {
        viewModelScope.launch {
            answersWithState.emit(answersWithState.value!!.map { el ->
                if (el.answer == answerModel) el.copy(state = !el.state)
                else el
            })
        }
    }

    fun onAddButtonClick(questionId: String, text: String) {
        viewModelScope.launch {
            val answerModel = AnswerModel(
                UUID.randomUUID().toString(),
                text,
                questionId
            )
            addAnswerUseCase.execute(
                AddAnswerUseCase.Request(
                    answerModel
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        Log.i(TAG, "success add")
                    }

                    is Result.Error -> {
                        Log.e(TAG, it.exception)
                    }

                    is Result.Loading -> {}
                }
                answersWithState.emit(
                    answersWithState.value!! + AnswerWithStateModel(
                        answerModel,
                        false,
                        questionId
                    )
                )
            }
        }
    }

    fun onCreateClick(onCreate: () -> Unit) {
        viewModelScope.launch {
            addEmotionUseCase.execute(
                AddEmotionUseCase.Request(
                    emotion.value!!.emotionModel,
                    answersWithState.value!!.map {
                        AnswerEmotionCrossRefModel(
                            it.questionId,
                            it.answer.id,
                            it.state
                        )
                    })
            ).collect {
                if (it is Result.Error)
                    Log.d(TAG, it.exception)
                onCreate()
            }

            /*for (i in answersWithState.value!!) {
                addAnswerUseCase.execute(AddAnswerUseCase.Request(i.answer))
            }*/
        }
    }

    companion object {
        const val TAG = "NotesViewModel"
    }
}