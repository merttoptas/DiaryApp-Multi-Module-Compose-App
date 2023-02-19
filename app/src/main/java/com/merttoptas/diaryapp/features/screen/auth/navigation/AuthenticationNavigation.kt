package com.merttoptas.diaryapp.features.screen.auth.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.features.components.snackbarmessage.rememberMessageBarState
import com.merttoptas.diaryapp.features.screen.auth.AuthenticationScreen
import com.merttoptas.diaryapp.features.screen.auth.AuthenticationViewModel
import com.stevdzasan.onetap.rememberOneTapSignInState
import kotlinx.coroutines.launch

/**
 * Created by mertcantoptas on 03.02.2023
 */

const val authenticationNavigationRoute = "authentication_route"

fun NavController.navigateAuthenticationScreen(navOptions: NavOptions? = null) {
    this.navigate(authenticationNavigationRoute, navOptions)
}

fun NavGraphBuilder.authenticationRoute(
    modifier: Modifier,
    navigateToHome: () -> Unit,
) {
    composable(route = authenticationNavigationRoute) {
        val viewModel = hiltViewModel<AuthenticationViewModel>()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()
        val authState by viewModel.authState.collectAsStateWithLifecycle()
        val isLoading = authState.isLoading
        val onTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()

        LaunchedEffect(viewModel.authState) {
            launch {
                viewModel.authState.collect {
                    if (it.authenticated) {
                        navigateToHome()
                    }
                }
            }
        }

        AuthenticationScreen(
            modifier = modifier,
            viewModel = viewModel,
            messageBarState = messageBarState,
            authState = screenState,
            onTapState = onTapState,
            isLoading = isLoading
        )
    }
}