package com.example.mydiary.presentation.models

import com.example.mydiary.presentation.adapters.Item

data class CircleButton(
    val stroke: List<Pair<Pair<Int,Int>,Float>>,
    val rotate:Boolean
) :Item