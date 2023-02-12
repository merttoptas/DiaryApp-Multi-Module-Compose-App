package com.merttoptas.diaryapp.domain.state

import androidx.annotation.StringRes

/**
 * Created by mertcantoptas on 02.02.2023
 */

sealed class ScreenState<out T : Any> {
    object Loading : ScreenState<Nothing>()
    data class Error(val message: String) : ScreenState<Nothing>()
    data class Success<out T : Any>(val uiData: T) : ScreenState<T>()
}
