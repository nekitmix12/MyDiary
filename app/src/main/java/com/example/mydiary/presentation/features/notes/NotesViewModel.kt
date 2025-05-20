package com.example.mydiary.presentation.features.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.domain.model.AnswerWithStateModel
import com.example.mydiary.domain.model.QuestionModel
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.usecase.emotions.GetAllQuestionsUseCase
import com.example.mydiary.domain.usecase.emotions.GetAnswersWithActiveUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val getAnswersWithActiveUseCase: GetAnswersWithActiveUseCase,
    private val getAllQuestionsUseCase: GetAllQuestionsUseCase
) : ViewModel() {
    private var _answersWithState = MutableStateFlow<List<AnswerWithStateModel>?>(null)
    val answersWithState: StateFlow<List<AnswerWithStateModel>?> = _answersWithState

    private var _questions = MutableStateFlow<List<QuestionModel>?>(null)
    val questions: StateFlow<List<QuestionModel>?> = _questions

    fun getAnswers(emotionsId: String) {
        viewModelScope.launch {
            getAnswersWithActiveUseCase.execute(GetAnswersWithActiveUseCase.Request(emotionsId))
                .collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> _answersWithState.emit(it.data.answers)
                        is Result.Error -> {}
                    }
                }
        }
    }

    fun getQuestions() {
        viewModelScope.launch {
            getAllQuestionsUseCase.execute(GetAllQuestionsUseCase.Request()).collect {
                when (it) {
                    is Result.Success -> _questions.emit(it.data.questions)
                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }


}