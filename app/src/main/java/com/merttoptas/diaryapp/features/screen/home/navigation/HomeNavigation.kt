package com.merttoptas.diaryapp.features.screen.home.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.diaryapp.R
import com.merttoptas.diaryapp.features.components.DiaryAlertDialog
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
        val homeUiState by viewModel.homeState.collectAsStateWithLifecycle()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

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
            onLogoutClicked = {
                viewModel.onDisplayValueChange(true)
            },
            drawerState = drawerState,
            onMenuClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            diaryNotes = homeUiState.diaries
        )

        if (homeUiState.isDialogDisplay) {
            DiaryAlertDialog(
                title = stringResource(id = R.string.sign_out_dialog_title),
                content = stringResource(id = R.string.sign_out_dialog_message),
                onDismiss = viewModel::onDismissDialog,
                onYesClicked = {
                    viewModel.onDismissDialog()
                    viewModel.logout()
                })
        }
    }
}