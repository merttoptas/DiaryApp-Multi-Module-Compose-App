@file:OptIn(ExperimentalMaterial3Api::class)

package com.merttoptas.diaryapp.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.merttoptas.diaryapp.features.components.DiaryScaffold

/**
 * Created by mertcantoptas on 02.02.2023
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DiaryApp(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination

    DiaryScaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = authNavigationRoute,
            Modifier.padding(innerPadding)
        ) {

        }
    }
}