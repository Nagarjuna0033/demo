package com.arjun.demo.core.common

sealed class DataState<out T> {

    data class Success<T>(val data: T) : DataState<T>()

    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : DataState<Nothing>()

    object Loading : DataState<Nothing>()

    object Empty : DataState<Nothing>()
}