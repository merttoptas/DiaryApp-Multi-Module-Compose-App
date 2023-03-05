package com.merttoptas.diaryapp.features.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merttoptas.diaryapp.core.common.DataState
import com.merttoptas.diaryapp.core.data.model.Diary
import com.merttoptas.diaryapp.core.data.repository.MongoRepository
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.domain.usecase.login.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * Created by mertcantoptas on 18.02.2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val mongoRepository: MongoRepository
) : ViewModel() {

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

    init {
        setConfigureTheRealm()
        getAllDiaries()
    }

    private fun getAllDiaries() {
        viewModelScope.launch {
            mongoRepository.getAllDiaries().collect { allDiaries ->
                when (allDiaries) {
                    is DataState.Loading -> _screenState.update { ScreenState.Loading }
                    is DataState.Error -> _screenState.update { ScreenState.Error(allDiaries.exception.message.toString()) }
                    is DataState.Success -> {
                        _homeState.update { it.copy(diaries = allDiaries.result) }
                        _screenState.update { ScreenState.Success(_homeState.value) }
                    }
                }
            }
        }
    }

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

  private  fun setConfigureTheRealm() {
        viewModelScope.launch {
            mongoRepository.configureTheRealm()
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val navigateToLogout: Boolean = false,
    val isDialogDisplay: Boolean = false,
    val diaries: Map<LocalDate, List<Diary>> = mapOf()
)