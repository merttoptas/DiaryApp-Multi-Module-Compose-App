package com.merttoptas.diaryapp.features.screen.home.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.diaryapp.features.screen.home.HomeScreen
import com.merttoptas.diaryapp.features.screen.home.HomeViewModel
import kotlinx.coroutines.launch

/**
 * Created by mertcantoptas on 18.02.2023
 */
const val homeNavigationRoute = "home_route"

fun NavController.navigateHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeRoute(
    modifier: Modifier,
    navigateToAuthentication: () -> Unit,
) {
    composable(route = homeNavigationRoute) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel.homeState) {
            launch {
                viewModel.homeState.collect {
                    if (it.navigateToLogout) {
                        navigateToAuthentication()
                    }
                }
            }
        }

        HomeScreen(
            modifier = modifier,
            screenState = screenState,
            logout = viewModel::logout
        )
    }
}