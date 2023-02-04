@file:OptIn(ExperimentalAnimationApi::class)

package com.merttoptas.diaryapp.features.screen.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.merttoptas.diaryapp.features.screen.auth.AuthenticationScreen

/**
 * Created by mertcantoptas on 03.02.2023
 */

const val authenticationNavigationRoute = "authentication_route"

fun NavController.navigateAuthenticationScreen(navOptions: NavOptions? = null) {
    this.navigate(authenticationNavigationRoute, navOptions)
}

fun NavGraphBuilder.authenticationScreen(navigateToHome: () -> Unit) {
    composable(route = authenticationNavigationRoute) {
        AuthenticationScreen(navigateToHome = navigateToHome)
    }
}