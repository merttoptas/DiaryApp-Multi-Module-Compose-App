@file:OptIn(ExperimentalAnimationApi::class)

package com.merttoptas.diaryapp.features.screen.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.features.components.DiaryScaffold
import com.merttoptas.diaryapp.R
import com.merttoptas.diaryapp.features.components.DiaryGoogleButton

/**
 * Created by mertcantoptas on 02.02.2023
 */

@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    val authState by viewModel.screenState.collectAsStateWithLifecycle()
    val isLoading = authState is ScreenState.Loading

    DiaryScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                AnimatedContent(targetState = authState) { state ->
                    when (state) {
                        is ScreenState.Loading -> Unit
                        is ScreenState.Error -> {}
                        is ScreenState.Success -> {
                            Content(onButtonClicked = {}, isLoading)
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun Content(onButtonClicked: () -> Unit, isLoading: Boolean = false) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth()
                .padding(all = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(weight = 10f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google Logo"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.auth_title),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
                Text(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    text = stringResource(id = R.string.auth_subtitle),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            }
            Column(
                modifier = Modifier.weight(weight = 2f),
                verticalArrangement = Arrangement.Bottom
            ) {
                DiaryGoogleButton(
                    onClick = onButtonClicked,
                    loadingState = isLoading
                )
            }
        }
    }
}
