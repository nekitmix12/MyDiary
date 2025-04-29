package com.example.mydiary.presentation.models

import com.example.mydiary.presentation.adapters.Item

data class RemindModel(
    val uuid: String,
    val data: String,
) : Item