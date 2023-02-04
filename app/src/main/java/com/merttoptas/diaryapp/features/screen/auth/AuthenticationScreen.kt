@file:OptIn(ExperimentalAnimationApi::class)

package com.merttoptas.diaryapp.features.screen.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.features.components.DiaryScaffold

/**
 * Created by mertcantoptas on 02.02.2023
 */

@Composable
fun AuthenticationScreen(viewModel: AuthenticationViewModel, navigateToHome: () -> Unit) {
    val authState by viewModel.screenState.collectAsStateWithLifecycle()
    var isLoading = authState is ScreenState.Loading

    DiaryScaffold(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
        content = {
            Column(Modifier.fillMaxSize()) {
                Text(text = "Authentication Screen")
            }

            AnimatedContent(targetState = authState) { state ->
                when (state) {
                    is ScreenState.Loading -> {
                    }
                    is ScreenState.Error -> {}
                    is ScreenState.Success -> {

                    }
                }
            }
        }
    )
}