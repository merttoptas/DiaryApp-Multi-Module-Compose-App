package com.merttoptas.diaryapp.features.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.domain.usecase.login.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by mertcantoptas on 18.02.2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase) : ViewModel() {

    private val _screenState =
        MutableStateFlow<ScreenState<HomeUiState>>(
            value = ScreenState.Success(
                HomeUiState()
            )
        )
    val screenState: StateFlow<ScreenState<HomeUiState>> = _screenState

    private val _homeState =
        MutableStateFlow(value = HomeUiState())
    val homeState: StateFlow<HomeUiState> = _homeState


    fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke().collect {
                _homeState.update { it.copy(navigateToLogout = true) }
            }
        }
    }
    fun onDisplayValueChange(isDisplay: Boolean) {
        _homeState.update { it.copy(isDialogDisplay = isDisplay) }
    }

    fun onDismissDialog() {
        _homeState.update { it.copy(isDialogDisplay = false) }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val navigateToLogout: Boolean = false,
    val isDialogDisplay: Boolean = false
)