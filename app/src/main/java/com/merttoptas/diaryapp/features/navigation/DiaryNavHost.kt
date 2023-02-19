package com.merttoptas.diaryapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.merttoptas.diaryapp.features.screen.auth.navigation.authenticationNavigationRoute
import com.merttoptas.diaryapp.features.screen.auth.navigation.authenticationRoute
import com.merttoptas.diaryapp.features.screen.auth.navigation.navigateAuthenticationScreen
import com.merttoptas.diaryapp.features.screen.home.navigation.homeRoute
import com.merttoptas.diaryapp.features.screen.home.navigation.navigateHomeScreen
import com.merttoptas.diaryapp.features.screen.splash.navigation.splashNavigationRoute
import com.merttoptas.diaryapp.features.screen.splash.navigation.splashRoute

/**
 * Created by mertcantoptas on 02.02.2023
 */

@Composable
fun DiaryNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = splashNavigationRoute
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        splashRoute(
            modifier = modifier,
            navigateToHome = {
                navController.navigateHomeScreen()
            },
            navigateToAuthentication = { navController.navigateAuthenticationScreen() }
        )
        authenticationRoute(
            navigateToHome = { navController.navigateHomeScreen() },
            modifier = modifier
        )
        homeRoute(
            modifier = modifier,
            navigateToAuthentication = { navController.navigateAuthenticationScreen() })
    }
}