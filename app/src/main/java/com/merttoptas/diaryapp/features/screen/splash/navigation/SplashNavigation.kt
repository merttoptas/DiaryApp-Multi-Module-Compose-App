package com.merttoptas.diaryapp.features.screen.splash.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.merttoptas.diaryapp.features.screen.splash.SplashScreen
import com.merttoptas.diaryapp.features.screen.splash.SplashViewModel

/**
 * Created by mertcantoptas on 18.02.2023
 */

const val splashNavigationRoute = "splash_route"

fun NavGraphBuilder.splashRoute(
    modifier: Modifier,
    navigateToHome: () -> Unit,
    navigateToAuthentication: () -> Unit,
) {
    composable(route = splashNavigationRoute) {
        val viewModel = hiltViewModel<SplashViewModel>()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()
        val uiState by viewModel.authState.collectAsStateWithLifecycle()
        val someUIState by viewModel.authState.collectAsState()

        LaunchedEffect(someUIState) {
            if (uiState.navigateToHome) {
                navigateToHome()
            } else if (uiState.navigateToLogin) {
                navigateToAuthentication()
            }
        }

        SplashScreen(
            modifier = modifier,
            screenState = screenState
        )
    }
}