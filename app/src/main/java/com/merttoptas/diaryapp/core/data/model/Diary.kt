package com.merttoptas.diaryapp.core.data.model

import androidx.room.PrimaryKey
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

/**
 * Created by mertcantoptas on 02.03.2023
 */
class Diary : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId.create()
    var ownerId: String? = null
    var title: String? = null
    var description: String? = null
    var images: RealmList<String> = realmListOf()
    var date: RealmInstant = RealmInstant.from(System.currentTimeMillis(), 0)
}