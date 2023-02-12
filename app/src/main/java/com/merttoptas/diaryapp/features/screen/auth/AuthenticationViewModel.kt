package com.merttoptas.diaryapp.features.screen.auth

import android.provider.UserDictionary.Words.APP_ID
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by mertcantoptas on 02.02.2023
 */
@HiltViewModel
class AuthenticationViewModel @Inject constructor() : ViewModel() {

    private val _screenState =
        MutableStateFlow<ScreenState<AuthenticationUiState>>(
            value = ScreenState.Success(
                AuthenticationUiState()
            )
        )
    val screenState: StateFlow<ScreenState<AuthenticationUiState>> = _screenState

    private val _authState =
        MutableStateFlow(value = AuthenticationUiState())
    val authState: StateFlow<AuthenticationUiState> = _authState


    fun signInWithMongoAtlas(
        tokenId: String,
        onSuccess: (Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                _screenState.value = ScreenState.Loading
                val result = withContext(Dispatchers.IO) {
                    App.create(Constants.APP_ID).login(
                        Credentials.jwt(tokenId)
                    ).loggedIn
                }
                withContext(Dispatchers.Main) {
                    if (result) {
                        delay(600)
                        _screenState.value = ScreenState.Success(
                            _authState.value.copy(authenticated = true)
                        )
                        onSuccess(true)
                    } else {
                        onError(Exception("User is not logged in."))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }
}


