package com.merttoptas.diaryapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.merttoptas.diaryapp.features.screen.auth.navigation.authenticationNavigationRoute
import com.merttoptas.diaryapp.features.screen.auth.navigation.authenticationScreen

/**
 * Created by mertcantoptas on 02.02.2023
 */

@Composable
fun DiaryNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = authenticationNavigationRoute
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        authenticationScreen(navigateToHome = {})
    }

}