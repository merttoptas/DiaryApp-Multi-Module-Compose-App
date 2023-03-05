package com.merttoptas.diaryapp.core.common

/**
 * Created by mertcantoptas on 05.03.2023
 */

sealed class DataState<out T : Any> {
    object Loading : DataState<Nothing>()
    data class Success<out T : Any>(val result: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
}