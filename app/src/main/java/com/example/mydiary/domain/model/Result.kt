package com.example.mydiary.domain.model

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    class Error(val exception: String) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}