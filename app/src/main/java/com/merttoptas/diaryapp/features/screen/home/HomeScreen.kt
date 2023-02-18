package com.merttoptas.diaryapp.features.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.features.components.DiaryButton
import com.merttoptas.diaryapp.features.components.DiaryScaffold
import com.merttoptas.diaryapp.features.screen.splash.SplashUiState

/**
 * Created by mertcantoptas on 18.02.2023
 */

@Composable
fun HomeScreen(
    modifier: Modifier,
    screenState: ScreenState<HomeUiState>,
    logout: () -> Unit
) {
    DiaryScaffold(modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize()) {
            when (screenState) {
                is ScreenState.Loading -> Unit
                is ScreenState.Success -> {
                    Content(logout)
                }
                is ScreenState.Error -> {
                    //TODO: Handle error
                }
            }
        }
    }
}

@Composable
private fun Content(logout: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DiaryButton(text = "Logout", onClick = { logout() })
        }
    }
}