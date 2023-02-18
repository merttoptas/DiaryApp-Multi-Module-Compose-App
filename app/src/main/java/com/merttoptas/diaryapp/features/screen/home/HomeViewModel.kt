package com.merttoptas.diaryapp.features.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by mertcantoptas on 18.02.2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

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

    private val _viewEvent = MutableSharedFlow<HomeViewEvent>()
    val viewEvent: SharedFlow<HomeViewEvent> = _viewEvent


    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            App.create(Constants.APP_ID).currentUser?.logOut()
            _viewEvent.emit(HomeViewEvent.Logout)
        }
    }
}

data class HomeUiState(
   val isLoading : Boolean = false
)

sealed interface HomeViewEvent {
    object Logout : HomeViewEvent
}