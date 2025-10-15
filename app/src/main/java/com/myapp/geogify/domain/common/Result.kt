package com.myapp.geogify.domain.common

// Classe selada para representar o estado de uma operación assíncrona
sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}