package com.merttoptas.diaryapp.domain.usecase.login

import com.merttoptas.diaryapp.util.Constants
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by mertcantoptas on 21.02.2023
 */

class LogoutUseCase @Inject constructor() {
    operator fun invoke(): Flow<Unit> {
        return flow {
            withContext(Dispatchers.IO) {
                val user = App.create(Constants.APP_ID).currentUser
                user?.logOut()
            }
            emit(Unit)
        }
    }
}