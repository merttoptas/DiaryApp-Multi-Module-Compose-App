package com.merttoptas.diaryapp.domain.usecase.splash

import com.merttoptas.diaryapp.features.screen.splash.SplashUiState
import com.merttoptas.diaryapp.util.Constants
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by mertcantoptas on 18.02.2023
 */

class SplashUseCase @Inject constructor() {
    operator fun invoke(): Flow<SplashUiState> {
        return flow {
            var navigateToHome = false
            var navigateToLogin = false
            val user = App.Companion.create(Constants.APP_ID).currentUser

            if (user != null && user.loggedIn) {
                navigateToHome = true
            } else {
                navigateToLogin = true
            }
            emit(SplashUiState(navigateToHome, navigateToLogin))
        }
    }
}