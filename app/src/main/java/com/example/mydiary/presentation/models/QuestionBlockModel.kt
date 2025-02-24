package com.example.mydiary.presentation.models

import com.example.mydiary.presentation.adapters.Item

data class QuestionBlockModel(
    val label: String,
    val answers:List<AnswerModel>
) : Item
