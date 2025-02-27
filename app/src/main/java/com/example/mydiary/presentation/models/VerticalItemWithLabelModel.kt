package com.example.mydiary.presentation.models

import com.example.mydiary.presentation.adapters.Item

data class VerticalItemWithLabelModel(
    val items: List<Item>,
    val label: String,
) : Item