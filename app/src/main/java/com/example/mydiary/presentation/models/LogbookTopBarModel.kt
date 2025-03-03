package com.example.mydiary.presentation.models

import com.example.mydiary.presentation.adapters.Item

data class LogbookTopBarModel(
    val logs: String,
    val logsInTime: Pair<String, String>,
    val streak: Pair<String, String>,
) : Item
