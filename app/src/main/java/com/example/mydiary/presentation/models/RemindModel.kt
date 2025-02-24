package com.example.mydiary.presentation.models

import com.example.mydiary.presentation.adapters.Item
import java.util.UUID

data class RemindModel(
    val uuid: UUID,
    val data: String,
) : Item