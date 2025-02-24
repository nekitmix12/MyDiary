package com.example.mydiary.presentation.models

import java.util.UUID

data class AnswerModel(
    val id: UUID,
    val text: String,
    val selected:Boolean
)
