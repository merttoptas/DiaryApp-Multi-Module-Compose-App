package com.merttoptas.diaryapp.core.data.repository

import com.merttoptas.diaryapp.core.data.model.Diary
import com.merttoptas.diaryapp.util.Constants
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration

/**
 * Created by mertcantoptas on 02.03.2023
 */
object MongoDB : MongoRepository {
    private val app = App.Companion.create(Constants.APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm
    override fun configureTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(user, setOf(Diary::class))
                .initialSubscriptions { sub ->
                    add(
                        query = sub.query("ownerOd == $0 AND title == $1", user.identity),
                        name = "User's Diaries"
                    )
                }.log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }
    }

}