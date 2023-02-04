package com.merttoptas.diaryapp.features.screen.auth

import androidx.lifecycle.ViewModel
import com.merttoptas.diaryapp.domain.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by mertcantoptas on 02.02.2023
 */
@HiltViewModel
class AuthenticationViewModel @Inject constructor() : ViewModel() {

    private val _screenState =
        MutableStateFlow<ScreenState<AuthenticationUiState>>(value = ScreenState.Loading)
    val screenState: StateFlow<ScreenState<AuthenticationUiState>> = _screenState

}


