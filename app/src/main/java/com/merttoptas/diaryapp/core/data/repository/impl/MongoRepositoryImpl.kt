package com.merttoptas.diaryapp.core.data.repository.impl

import android.security.keystore.UserNotAuthenticatedException
import android.util.Log
import com.merttoptas.diaryapp.core.common.DataState
import com.merttoptas.diaryapp.core.data.model.Diary
import com.merttoptas.diaryapp.core.data.repository.Diaries
import com.merttoptas.diaryapp.core.data.repository.MongoRepository
import com.merttoptas.diaryapp.util.Constants
import com.merttoptas.diaryapp.util.toInstant
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.time.ZoneId
import javax.inject.Inject

/**
 * Created by mertcantoptas on 05.03.2023
 */
class MongoRepositoryImpl @Inject constructor() : MongoRepository {
    private lateinit var realm: Realm
    private val user = App.Companion.create(Constants.APP_ID).currentUser
    override fun configureTheRealm() {
        try {
            if (user != null) {
                val config = SyncConfiguration.Builder(user, setOf(Diary::class))
                    .initialSubscriptions { sub ->
                        add(
                            query = sub.query("ownerId == $0", user.identity),
                            name = "User's Diaries"
                        )
                    }.log(LogLevel.ALL)
                    .build()
                try {
                    realm = Realm.open(config)
                } catch (e: Exception) {
                    Log.e("Realm", "Realm Error: ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e("Realm", "Realm Error: ${e.message}")
        }
    }

    override suspend fun getAllDiaries(): Flow<Diaries> {
        return flow<Diaries> {
            if (user != null) {
                try {
                    val diaries = realm.query<Diary>(query = "ownerId == $0", user.identity)
                        .sort(property = "date", sortOrder = Sort.DESCENDING)
                        .asFlow().map { result ->
                            DataState.Success(result.list.groupBy {
                                it.date.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                            })
                        }
                    diaries.collect { emit(it) }
                } catch (e: Exception) {
                    emit(DataState.Error(e))
                }
            } else {
                emit(DataState.Error(UserNotAuthenticatedException()))
            }
        }.catch { e ->
            emit(DataState.Error(java.lang.Exception(e)))
        }.onStart { emit(DataState.Loading) }.flowOn(Dispatchers.IO)
    }
}
