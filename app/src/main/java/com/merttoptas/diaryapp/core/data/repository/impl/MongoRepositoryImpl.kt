package com.merttoptas.diaryapp.core.data.repository.impl

import android.util.Log
import com.merttoptas.diaryapp.core.common.DataState
import com.merttoptas.diaryapp.core.data.model.Diary
import com.merttoptas.diaryapp.core.data.repository.MongoRepository
import com.merttoptas.diaryapp.util.Constants
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

/**
 * Created by mertcantoptas on 05.03.2023
 */
class MongoRepositoryImpl @Inject constructor() : MongoRepository {
    private lateinit var realm: Realm
    override fun configureTheRealm() {
        val user = App.Companion.create(Constants.APP_ID).currentUser
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

    override fun getAllDiaries(): Flow<DataState<Map<LocalDate, List<Diary>>>> {
        TODO("Not yet implemented")
    }
}
