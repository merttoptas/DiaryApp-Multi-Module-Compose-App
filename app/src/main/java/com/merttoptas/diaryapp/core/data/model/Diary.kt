package com.merttoptas.diaryapp.core.data.model

import androidx.room.PrimaryKey
import com.merttoptas.diaryapp.util.toRealmInstant
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import java.time.Instant

/**
 * Created by mertcantoptas on 02.03.2023
 */
open class Diary : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var ownerId: String? = null
    var mood: String = Mood.Neutral.name
    var title: String? = null
    var description: String? = null
    var images: RealmList<String> = realmListOf()
    var date: RealmInstant = Instant.now().toRealmInstant()
}