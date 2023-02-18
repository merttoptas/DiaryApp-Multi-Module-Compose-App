package com.merttoptas.diaryapp.features.screen.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.domain.usecase.splash.SplashUseCase
import com.merttoptas.diaryapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy
import javax.inject.Inject

/**
 * Created by mertcantoptas on 18.02.2023
 */

@HiltViewModel
class SplashViewModel @Inject constructor(private val splashUseCase: SplashUseCase) : ViewModel() {

    private val _screenState =
        MutableStateFlow<ScreenState<SplashUiState>>(
            value = ScreenState.Success(
                SplashUiState()
            )
        )
    val screenState: StateFlow<ScreenState<SplashUiState>> = _screenState

    private val _authState =
        MutableStateFlow(value = SplashUiState())
    val authState: StateFlow<SplashUiState> = _authState

    init {
        checkUser()
    }

    private fun checkUser() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1500)
            splashUseCase.invoke().last().let { uiState ->
                Log.d("deneme1", "uiState: $uiState")
                _authState.value = uiState

            }
        }
    }
}

data class SplashUiState(
    val navigateToHome: Boolean = false,
    val navigateToLogin: Boolean = false,
)