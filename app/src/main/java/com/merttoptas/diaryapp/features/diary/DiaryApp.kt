package com.merttoptas.diaryapp.features.diary

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.merttoptas.diaryapp.features.components.DiaryScaffold
import com.merttoptas.diaryapp.features.navigation.DiaryNavHost
import com.merttoptas.diaryapp.R
import com.merttoptas.diaryapp.core.data.util.NetworkMonitor

/**
 * Created by mertcantoptas on 04.02.2023
 */
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun DiaryApp(
    networkMonitor: NetworkMonitor,
    appState: DiaryAppState = rememberDiaryAppState(
        networkMonitor = networkMonitor,
    ),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    val notConnectedMessage = stringResource(R.string.not_network_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) snackbarHostState.showSnackbar(
            message = notConnectedMessage,
            duration = SnackbarDuration.Indefinite
        )
    }

    DiaryScaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackbarHostState) },

        ) {
        DiaryNavHost(
            navController = appState.navController,
            onBackClick = appState::onBackClick
        )
    }
}

