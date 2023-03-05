package com.merttoptas.diaryapp.core.data.model

import com.merttoptas.diaryapp.util.toRealmInstant
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import java.time.Instant
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Created by mertcantoptas on 02.03.2023
 */
open class Diary : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var ownerId: String = ""
    var mood: String = Mood.Neutral.name
    var title: String = ""
    var description: String = ""
    var images: RealmList<String> = realmListOf()
    var date: RealmInstant = Instant.now().toRealmInstant()
}